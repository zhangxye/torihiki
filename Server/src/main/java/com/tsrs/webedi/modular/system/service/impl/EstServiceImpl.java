package com.tsrs.webedi.modular.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tsrs.webedi.common.constant.Const;
import com.tsrs.webedi.core.util.DateUtil;
import com.tsrs.webedi.core.util.ExcelUtil;
import com.tsrs.webedi.modular.system.dao.EstDao;
import com.tsrs.webedi.modular.system.persistence.dao.EstdtlMapper;
import com.tsrs.webedi.modular.system.persistence.dao.EsthdrMapper;
import com.tsrs.webedi.modular.system.persistence.model.Estdtl;
import com.tsrs.webedi.modular.system.persistence.model.Esthdr;
import com.tsrs.webedi.modular.system.service.IEstService;
import com.tsrs.webedi.modular.system.service.INoSeqMgrService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 見積HDR情報 服务实现类
 * </p>
 *
 * @author jin-qk
 * @since 2018-03-26
 */
@Service
public class EstServiceImpl extends ServiceImpl<EstDao, Esthdr> implements IEstService {

	@Resource
	private EstDao estDao;

	@Resource
	private EsthdrMapper estHdrMapper;

	@Resource
	private EstdtlMapper estDtlMapper;

	@Resource
	private INoSeqMgrService noSeqMgrService;

	private static final String PAYMENT_CTX = "%s\r\n" + "イ) 各注文の合計時間数を、該当月の作業時間数として精算する。\r\n"
			+ "　　「一ヶ月合計は、0.5h（30分）単位端数切捨て。」\r\n" + "ロ)業務中に上記指定場所以外に発生する移動交通費や宿泊費については別途、実費精算。\r\n" + "   （当月請求分でご請求）";

	/**
	 * 未登録の見積一覧データを検索する
	 * 
	 * @param page
	 * @param searchCond
	 * @return
	 */
	@Override
	public Page<Map<String, Object>> searchForEntry(Page<Map<String, Object>> page, Map<String, Object> searchCond) {
		List<Map<String, Object>> temp = estDao.searchForEntry(page, searchCond);
		return page.setRecords(estDao.searchForEntry(page, searchCond));
	}

	/**
	 * 登録済の見積一覧データを検索する
	 * 
	 * @param page
	 * @param searchCond
	 * @return
	 */
	@Override
	public Page<Map<String, Object>> searchForDetail(Page<Map<String, Object>> page, Map<String, Object> searchCond) {
		return page.setRecords(estDao.searchForDetail(page, searchCond));
	}

	/**
	 * 未登録の見積データを照会する
	 * 
	 * @param apprDocIdList
	 * @return
	 */
	@Override
	public Map<String, Object> selectForInsert(String apprDocIdList) {

		String[] apprDocIdAry = apprDocIdList.split("#");
		Map<String, Object> estInfo = estDao.selectEstHdrInfoForInsert(apprDocIdAry);
		// お支払条件内容を初期化する
		String paymentCond = (String) estInfo.get("payment_cond");
		paymentCond = String.format(PAYMENT_CTX, paymentCond);
		estInfo.put("payment_cond", paymentCond);
		List<Map<String, Object>> estDlt = estDao.selectEstDtlInfoForInsert(apprDocIdAry);
		estInfo.put("est_dtl", estDlt);
		return estInfo;
	}

	/**
	 * 見積詳細データを照会する
	 * 
	 * @param estHid
	 * @return
	 */
	@Override
	public Map<String, Object> selectForDetail(Integer estHid) {
		Map<String, Object> estInfo = estDao.selectEstHdrInfoForDetail(estHid);
		List<Map<String, Object>> estDlt = estDao.selectEstDtlInfoForDetail(estHid);
		estInfo.put("est_dtl", estDlt);
		return estInfo;
	}

	/**
	 * 見積データを登録する
	 * 
	 * @param estInfo
	 * @return
	 * @throws Exception
	 */
	@Override
	public Integer insertEstInfo(Map<String, Object> estInfo) throws Exception {

		String pgId = Const.PG.EST + Const.PAGE.ENT;

		// 見積NOを採番
		String estNo = noSeqMgrService.getFieldNo(Const.SYS_CD, "estHdr", "est_no");
		if (estNo == null) {
			throw new Exception("見積NOの採番が失敗しました。");
		}

		// 見積ヘッダーデータを登録
		Integer estHid = insertEstHdr(estInfo, estNo, pgId);

		// 見積明細データ登録
		insertDtlHdr((List<Map<String, Object>>) estInfo.get("est_dtl"), estHid, pgId);

		return estHid;

	}

	/**
	 * 見積データを更新する
	 * 
	 * @param estHdr
	 * @return
	 */
	@Override
	public Integer updateEstInfo(Esthdr estHdr) {
		String pgId = Const.PG.EST + Const.PAGE.UPD;
		estHdr.setSysUpdPgId(pgId);
		estHdr.updateById();
		return estHdr.getEstHid();
	}

	/**
	 * 見積データを削除する
	 * 
	 * @param estHid
	 * @return
	 */
	@Override
	public boolean deleteEstInfo(Integer estHid) {
		String pgId = Const.PG.EST + Const.PAGE.DEL;
		Esthdr estHdr = new Esthdr();
		estHdr.setEstHid(estHid);
		estHdr.setSysUpdPgId(pgId);
		return estHdr.deleteById();
	}

	/**
	 * 見積書を発行する ※発行日を登録 及び 見積書（EXCEL）をダウンロードする
	 * 
	 * @param estHid
	 *            見積HID
	 * @param res
	 * @return
	 */
	@Override
	public String issueEstDoc(Integer estHid, HttpServletResponse res) {

		String pgId = Const.PG.EST + Const.PAGE.UPD;
		String issueDt = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		Esthdr estHdr = new Esthdr();
		estHdr.setEstHid(estHid);
		estHdr.setIssueDt(new Date());
		estHdr.setSysUpdPgId(pgId);
		estHdr.updateById();

		dlEstDoc(estHid, res);

		return issueDt;
	}

	/**
	 * 見積書を送付する ※送付日を登録するだけ
	 * 
	 * @param estHid
	 *            見積HID
	 * @return
	 */
	@Override
	public String deliveryEstDoc(Integer estHid) {
		String pgId = Const.PG.EST + Const.PAGE.UPD;
		String deliveryDt = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		Esthdr estHdr = new Esthdr();
		estHdr.setEstHid(estHid);
		estHdr.setDeliveryDt(new Date());
		estHdr.setSysUpdPgId(pgId);
		estHdr.updateById();
		return deliveryDt;
	}

	private void dlEstDoc(Integer estHid, HttpServletResponse res) {

		// String dlFileNm = "【TSRS】見積書.xls";
		String dlFileNm = "EstDoc.xls";

		res.setHeader("content-type", "application/octet-stream");
		res.setContentType("application/octet-stream");
		res.setHeader("Content-Disposition", "attachment;filename=" + dlFileNm);

//		FileInputStream inp = null;
		InputStream inp = null;
		OutputStream out = null;
		HSSFWorkbook workbook = null;
		try {
//			inp = new FileInputStream(ResourceUtils.getFile("classpath:EstDoc.xls"));
//			inp = this.getClass().getResourceAsStream("/template/EstDoc.xls");
			org.springframework.core.io.Resource resource = new org.springframework.core.io.ClassPathResource("/template/EstDoc.xls");
			inp = resource.getInputStream();
			workbook = new HSSFWorkbook(inp);
			// シートを取得
			HSSFSheet sheet = workbook.getSheetAt(0);
			// 見積書データを設定
			this.setEstDataToSheet(estHid, sheet);
			out = res.getOutputStream();
			// System.out.println("file size:"+IOUtils.copy(inp, out));
			workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(workbook);
			IOUtils.closeQuietly(inp);
			IOUtils.closeQuietly(out);

		}
	}

	private void setEstDataToSheet(Integer estHid, HSSFSheet sheet) {

		// 見積ヘッダーデータを設定する
		Map<String, Object> estHdr = this.setEstHdrDataToSheet(estHid, sheet);
		if (estHdr == null)
			return;
//		Date workStartYmd = DateUtil.parse((String) estHdr.get("work_start_ymd"), "yyyy-MM-dd");
//		Date workEndYmd = DateUtil.parse((String) estHdr.get("work_end_ymd"), "yyyy-MM-dd");

//		//作業開始日と作業終了日により、担当者の作業期間を計算する
//		double workMon = 1.0;
//		workMon = (double) (workEndYmd.getTime() - workStartYmd.getTime()) / (1000 * 60 * 60) / (24 * 30);
//		workMon = (double)Math.round(workMon * 100) / 100;
		// 見積明細データを設定する
		this.setEstDtlDataToSheet(estHid, sheet);

	}

	private Map<String, Object> setEstHdrDataToSheet(Integer estHid, HSSFSheet sheet) {
		Map<String, Object> estHdr = this.estDao.selectEstHdrInfoForDetail(estHid);
		if (estHdr == null)
			return null;

		HSSFCellStyle cellStyle=sheet.getWorkbook().createCellStyle(); 
		cellStyle.setWrapText(true);
		
		// 御見積番号
		ExcelUtil.setCellValue(sheet, 1, 8, (String) estHdr.get("est_no"));
		// 発注仕様書NO：
		ExcelUtil.setCellValue(sheet, 2, 8, (String) estHdr.get("shipping_order_spec_no"));
		// 提出日
		ExcelUtil.setCellValue(sheet, 3, 8, DateUtil.parse((String) estHdr.get("submit_dt"), "yyyy-MM-dd"));
		// 会社名
		ExcelUtil.setCellValue(sheet, 8, 1, (String) estHdr.get("client_nm"));
		// 御見積金額
//		ExcelUtil.setCellValue(sheet, 19, 2, (BigDecimal) estHdr.get("est_amt_without_tax"));
		ExcelUtil.setCellValue(sheet, 19, 2, (BigDecimal) estHdr.get("est_amt_with_tax"));
		// 案件名称
		ExcelUtil.setCellValue(sheet, 21, 2, (String) estHdr.get("pj_nm"));
		// 作業期間
		Date workStartYmd = DateUtil.parse((String) estHdr.get("work_start_ymd"), "yyyy-MM-dd");
		Date workEndYmd = DateUtil.parse((String) estHdr.get("work_end_ymd"), "yyyy-MM-dd");
		ExcelUtil.setCellValue(sheet, 22, 2, workStartYmd);
		ExcelUtil.setCellValue(sheet, 22, 5, workEndYmd);
		// 作業内容・範囲
		ExcelUtil.setCellValue(sheet, 23, 2, (String) estHdr.get("work_content"));
		// 作業場所
		ExcelUtil.setCellValue(sheet, 24, 2, (String) estHdr.get("work_place"));
		// 注文内容
		ExcelUtil.setCellValue(sheet, 30, 2, (String) estHdr.get("order_content"));
		// お支払条件
		ExcelUtil.setCellValue(sheet, 35, 2, (String) estHdr.get("payment_cond"));
		// 特記事項
		ExcelUtil.setCellValue(sheet, 42, 2, (String) estHdr.get("special_affairs"));
		// 備考
		ExcelUtil.setCellValue(sheet, 43, 2, (String) estHdr.get("remark"));
		
		return estHdr;

	}

	private void setEstDtlDataToSheet(Integer estHid, HSSFSheet sheet) {

		List<Map<String, Object>> estDtlList = this.estDao.selectEstDtlInfoForDetail(estHid);
		if (estDtlList == null || estDtlList.size() == 0)
			return;

		// 明細開始行
		int dltRow = 26;
		for (Map<String, Object> estDtl : estDtlList) {
			// 作業者名(責任者)
			ExcelUtil.setCellValue(sheet, dltRow, 1, (String) estDtl.get("emp_nm"));
			// 基準単価
			ExcelUtil.setCellValue(sheet, dltRow, 2, (BigDecimal) estDtl.get("sell_price"));
			// 基本時間
			String accountingPatK = (String) estDtl.get("accounting_pat_k");
			switch (accountingPatK) {
			// 1：上下割
			case Const.ACC_PAT_K.MAX_MIN:
				ExcelUtil.setCellValue(sheet, dltRow, 3, (BigDecimal) estDtl.get("wh_max"));
				ExcelUtil.setCellValue(sheet, dltRow, 5, (BigDecimal) estDtl.get("wh_min"));
				break;
			// 2：中割
			case Const.ACC_PAT_K.MID:
				ExcelUtil.setCellValue(sheet, dltRow, 3, (BigDecimal) estDtl.get("wh_mid"));
				ExcelUtil.setCellValue(sheet, dltRow, 5, (BigDecimal) estDtl.get("wh_mid"));
				break;
			// 3：固定
			case Const.ACC_PAT_K.FIXED:
				ExcelUtil.setCellValue(sheet, dltRow, 3, "");
				ExcelUtil.setCellValue(sheet, dltRow, 5, "");

			default:
				break;
			}

			// 超過単価
			ExcelUtil.setCellValue(sheet, dltRow, 6, (BigDecimal) estDtl.get("excess_price"));
			// 控除単価
			ExcelUtil.setCellValue(sheet, dltRow, 7, (BigDecimal) estDtl.get("deducation_price"));
			// 工数(人/月)
			ExcelUtil.setCellValue(sheet, dltRow, 8, (BigDecimal) estDtl.get("sum_contract_wm"));
			++dltRow;
		}

	}

	private Integer insertEstHdr(Map<String, Object> estInfo, String estNo, String pgId) {

		Esthdr estHdr = new Esthdr();
		estHdr.setEstNo(estNo);
		// 発注仕様書NO
		estHdr.setShippingOrderSpecNo((String) estInfo.get("shipping_order_spec_no"));
		// クライアント名
		estHdr.setClientNm((String) estInfo.get("client_nm"));
		// 見積区分
		estHdr.setEstK((String) estInfo.get("est_k"));
		// 提出日
		estHdr.setSubmitDt(DateUtil.parseStandardDate((String) estInfo.get("submit_dt")));
		// 発行日
		estHdr.setIssueDt(null);
		// 送付日
		estHdr.setDeliveryDt(null);
		// 見積金額(税抜)
		estHdr.setEstAmtWithoutTax(new BigDecimal((Integer) estInfo.get("est_amt_without_tax")));
		// 見積金額(税込)
		estHdr.setEstAmtWithTax(new BigDecimal((Integer) estInfo.get("est_amt_with_tax")));
		// 作業開始日
		estHdr.setWorkStartYmd(DateUtil.parse((String) estInfo.get("work_start_ymd"), "yyyy-MM-dd"));
		// 作業終了日
		estHdr.setWorkEndYmd(DateUtil.parse((String) estInfo.get("work_end_ymd"), "yyyy-MM-dd"));
		// 案件番号
		estHdr.setPjCd((String) estInfo.get("pj_cd"));
		// 案件名称
		estHdr.setPjNm((String) estInfo.get("pj_nm"));
		// 作業内容
		estHdr.setWorkContent((String) estInfo.get("work_content"));
		// 作業場所
		estHdr.setWorkPlace((String) estInfo.get("work_place"));
		// 注文内容
		estHdr.setOrderContent((String) estInfo.get("order_content"));
		// 特記事項
		estHdr.setSpecialAffairs((String) estInfo.get("special_affairs"));
		// お支払条件
		estHdr.setPaymentCond((String) estInfo.get("payment_cond"));
		// 備考
		estHdr.setRemark((String) estInfo.get("remark"));
		estHdr.setSysInsPgId(pgId);

		estHdr.insert();

		return estHdr.getEstHid();
	}

	private void insertDtlHdr(List<Map<String, Object>> estDtlList, Integer estHid, String pgId) {
		for (Map<String, Object> estDtlEnt : estDtlList) {
			Estdtl estDtl = new Estdtl();
			estDtl.setEstHid(estHid);
			estDtl.setApprDocId((Integer) estDtlEnt.get("appr_doc_id"));
			estDtl.setSysInsPgId(pgId);
			estDtl.insert();
		}
	}

}
