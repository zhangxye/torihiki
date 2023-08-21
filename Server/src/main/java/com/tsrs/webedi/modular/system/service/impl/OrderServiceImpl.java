package com.tsrs.webedi.modular.system.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tsrs.webedi.common.constant.Const;
import com.tsrs.webedi.common.service.ISysFileMgrService;
import com.tsrs.webedi.core.shiro.ShiroKit;
import com.tsrs.webedi.core.util.DateUtil;
import com.tsrs.webedi.modular.system.dao.EstDao;
import com.tsrs.webedi.modular.system.dao.OrderDao;
import com.tsrs.webedi.modular.system.persistence.dao.OrderMapper;
import com.tsrs.webedi.modular.system.persistence.model.Order;
import com.tsrs.webedi.modular.system.persistence.model.Orderconfirm;
import com.tsrs.webedi.modular.system.service.INoSeqMgrService;
import com.tsrs.webedi.modular.system.service.IOrderService;

/**
 * <p>
 * 受注HDR情報 服务实现类
 * </p>
 *
 * @author jin-qk
 * @since 2018-03-26
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, Order> implements IOrderService {

	@Resource
	private EstDao estDao;
	
	@Resource
	private OrderDao orderDao;

	@Resource
	private OrderMapper orderHdrMapper;

	@Resource
	private INoSeqMgrService noSeqMgrService;
	
	@Resource
	private ISysFileMgrService sysFileMgrService;

	

	public Page<Map<String, Object>> searchForEntry(Page<Map<String, Object>> page, Map<String, Object> searchCond) {
		return page.setRecords(estDao.searchForOrderEntry(page, searchCond));
	}

	public Page<Map<String, Object>> searchForDetail(Page<Map<String, Object>> page, Map<String, Object> searchCond) {
		return page.setRecords(orderDao.searchForDetail(page, searchCond));
	}
		
	public Map<String,Object> selectForInsert(Integer estHid){
		Map<String, Object> estInfo = estDao.selectEstHdrInfoForDetail(estHid);
		List<Map<String, Object>> estDlt = estDao.selectEstDtlInfoForDetail(estHid);
		estInfo.put("est_dtl", estDlt);
		return estInfo;
	}

	public Map<String,Object> selectForDetail(Integer orderId){
		Map<String, Object> orderInfo = orderDao.selectOrderInfoForDetail(orderId);
		List<Map<String, Object>> estDlt = estDao.selectEstDtlInfoForDetail((Integer)orderInfo.get("est_hid"));
		
		Object fileId = orderInfo.get("order_doc_file_id");
		if (null != fileId) {
			List<Map<String, Object>> fileList = sysFileMgrService
					.selectFileList((Integer)fileId);
			if (null != fileList) {
				orderInfo.put("order_doc_file_list", fileList);
			}
		}

		orderInfo.put("est_dtl", estDlt);
		
		return orderInfo;
	}
	
	public Integer insertOrderInfo(Map<String, Object> orderInfo) throws Exception {
		String pgId = Const.PG.ORDER + Const.PAGE.ENT;

		//受注NOを採番
		String orderNo = noSeqMgrService.getFieldNo(Const.SYS_CD, "order", "order_no");
		if (orderNo == null) {
			throw new Exception("見積NOの採番が失敗しました。");
		}

		//受注データを登録
		Integer orderHid = insertOrder(orderInfo, orderNo, pgId);
		
		// アップロードファイルがある場合、有効にする
		sysFileMgrService.updateFileToValid((Integer) orderInfo.get("order_doc_file_id"), pgId);
	
		return orderHid;

	}
	
	
	public Integer updateOrderInfo(Order order) {
		String pgId = Const.PG.ORDER + Const.PAGE.UPD;
		order.updateById();
		
		// アップロードファイルがある場合、有効にする
		sysFileMgrService.updateFileToValid(order.getOrderDocFileId(),pgId);

		return order.getOrderId();
	}
	
	public boolean deleteOrderInfo(Integer orderId) {
		String pgId = Const.PG.ORDER + Const.PAGE.DEL;
		Order order = new Order();
		order.setOrderId(orderId);
		order.setSysUpdPgId(pgId);
		return order.deleteById();
	}
	

	private Integer insertOrder(Map<String, Object> orderInfo, String orderNo, String pgId) {

		Order order = new Order();
		// 受注NO
		order.setOrderNo(orderNo);
		// クライアント名
		order.setClientNm((String) orderInfo.get("client_nm"));
		// 作成日
		order.setCreateDt(DateUtil.parseStandardDate((String)orderInfo.get("create_dt")));
		// 見積金額(税抜)
		order.setOrderAmtWithoutTax(new BigDecimal((Integer)orderInfo.get("order_amt_without_tax")));
		// 見積金額(税込)
		order.setOrderAmtWithTax(new BigDecimal((Integer)orderInfo.get("order_amt_with_tax")));
		// 作業開始日
		order.setWorkStartYmd(DateUtil.parse((String) orderInfo.get("work_start_ymd"),"yyyy-MM-dd"));
		// 作業終了日
		order.setWorkEndYmd(DateUtil.parse((String) orderInfo.get("work_end_ymd"),"yyyy-MM-dd"));
		// 案件番号
		order.setPjCd((String) orderInfo.get("pj_cd"));
		// 案件名称
		order.setPjNm((String) orderInfo.get("pj_nm"));
		// 作業内容
		order.setWorkContent((String) orderInfo.get("work_content"));
		// 作業場所
		order.setWorkPlace((String) orderInfo.get("work_place"));
		// 注文内容
		order.setOrderContent((String) orderInfo.get("order_content"));
		// 特記事項
		order.setSpecialAffairs((String) orderInfo.get("special_affairs"));
		// お支払条件
		order.setPaymentCond((String) orderInfo.get("payment_cond"));
		// 備考
		order.setRemark((String) orderInfo.get("remark"));
		//注文書
		Integer p_order_doc_file_id = (Integer)orderInfo.get("order_doc_file_id");
		if(p_order_doc_file_id < 0) {
			order.setOrderDocFileId(null);
		}else {
			order.setOrderDocFileId(p_order_doc_file_id);
		}
		//見積HID
		order.setEstHid((Integer)orderInfo.get("est_hid"));
		order.setSysInsPgId(pgId);

		order.insert();

		return order.getOrderId();
	}
	
	/**
	 * 受注を確定
	 * @param confirmList
	 */
	public void confirmOrder(List<Map<String, Object>> confirmList) {
		
		String pgId = Const.PG.ORDER + Const.PAGE.CONFIRM;
		
		String orderConfirmUserId = ShiroKit.getUser().getAccount();
		Date confirmDt = new Date();
		for (Map<String, Object> map : confirmList) {
			
			Orderconfirm orderConfirm = new Orderconfirm();
			Integer orderId = (Integer)map.get("order_id");
			//受注ID
			orderConfirm.setOrderId(orderId);
			//備考
			orderConfirm.setOrderConfirmRemark((String)map.get("order_confirm_remark"));
			
			//承認者
			orderConfirm.setOrderConfirmUserId(orderConfirmUserId);
			//承認日
			orderConfirm.setOrderConfirmDt(confirmDt);
			
			orderConfirm.insert();
			
			// 稟議書のST　確認済→契約済
			this.updateApproDocSt(orderId, Const.APPR_DOC_ST.CONTRACT_FINISH, pgId);
		}
	}
	
	/**
	 * 確定済みの受注を取消
	 * @param confirmList
	 */
	public void cancelConfirmOrder(List<Map<String, Object>> confirmList) {
		String pgId = Const.PG.ORDER + Const.PAGE.CONFIRM;
		
		for (Map<String, Object> map : confirmList) {
			Orderconfirm orderConfirm = new Orderconfirm();
			//受注ID
			Integer orderId = (Integer)map.get("order_id");
			orderConfirm.setOrderId(orderId);
			orderConfirm.deleteById();
			
			// 稟議書のST 契約済→確認済
			this.updateApproDocSt(orderId, Const.APPR_DOC_ST.CONFIRM_FINISH, pgId);
		}		
	}
	
	private void updateApproDocSt(Integer orderId, String apprDocSt, String pgId) {
		Map<String, Object> map = new HashMap<>();
		map.put("order_id", orderId);
		map.put("appr_doc_st", apprDocSt);
		map.put("user_id", ShiroKit.getUser().getAccount());
		map.put("pg_id", pgId);
		orderDao.updateApprDocSt(map);
	}

}
