package com.tsrs.webedi.modular.system.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tsrs.webedi.modular.system.jmodel.ApproveHdr;
import com.tsrs.webedi.modular.system.persistence.model.ApprapplyExt;
import com.tsrs.webedi.modular.system.persistence.model.Apprdoc;
import com.tsrs.webedi.modular.system.persistence.model.ApprdocExt;


public interface IApprDocService {
	/**
	 * 稟議書サービス
	 *
	 * @author Tsrs-WebEDI
	 * @Date 2017/4/27 17:01
	 */

	List<Map<String, Object>> selectEmpHisList(String empno);
	
	List<Map<String, Object>> selectEmpHisList2(String apprDocNo);

	/**
	 * 登録
	 * @param apprDoc
	 * @return
	 * @throws Exception
	 */
	public String insertApprDocService(ApprdocExt apprDoc) throws Exception;
	
	/**
	 * 更新
	 * @param apprDoc
	 * @return
	 * @throws Exception
	 */
	public int updateApprDocService(ApprdocExt apprDoc) throws Exception;
	
	/**
	 * 削除
	 * 
	 * @param apprDocNo
	 * @return
	 * @throws Exception
	 */
	public int deleteApprDocService(String apprDocNo) throws Exception;
	
	/**
	 * 取消
	 * @param apprDocId
	 * @return
	 * @throws Exception
	 */
	public int invalidateApprDocService(Integer apprDocId) throws Exception;
	
	public int applyApprDocService(ApprapplyExt apprApplyExt) throws Exception;

	Map<String, Object> selectEmpLastInfo(String empno);

	List<Map<String, Object>> selectApprDocList(String clientNm, String empNm, String apprDocNo, String deptCd,
			String createDtFr, String createDtTo, String workMonth, String mainUserId,String arrpDocSt);
	
	Map<String, Object> selectApprDocInfo(String apprDocNo);
	
	Map<String, Object> selectApprDocInfo2(String apprDocNo);
	
	Map<String, Object> selectApprDocPrevInfo(String apprDocNo);
	
	List<Map<String, Object>> selectApprcommuteroutedtl(String apprDocNo);
	
	List<Map<String,Object>> selectApprApplyList(String apprDocNo);
	
	List<Map<String,Object>> selectApprApprove(String account,String apprDocNo,String approveDtFr,String approveDtTo,String approveStatus,String empNo,String empNm);
	
	ApproveHdr goApprove(Integer apprDocId,Integer apprApplyHid) throws Exception;
	
	int approve(Integer apprDocId,String account, String conRejComment, String confirmSt);
	
	List<Map<String, Object>> selectEvidenceList(String apprDocNo);
	
	List<Map<String, Object>> selectFileList(Integer mon_attach_file_id);

	/**
	 * 該当要員の受注時間と重なっている稟議書データを取得
	 * @param empNo
	 * @param apprDocDtFrom
	 * @param apprDocDtTo
	 * @return
	 */
	List<Apprdoc> getOverlapApprDocInfo(String empNo, Date apprDocDtFrom, Date apprDocDtTo);
	
	/**
	 * 月次請求一覧データ検索
	 * 
	 * @param page 改頁情報
	 * @param searchCond 検索条件
	 * @return
	 */
	public Page<Map<String,Object>> searchForMonReqList(Page<Map<String, Object>> page,Map<String, Object> searchCond);


	/**
	 * 月次請求データ更新
	 * @param apprDoc
	 * @return
	 * @throws Exception
	 */
	public boolean updateApprDocMonReqInfo(Apprdoc apprDoc) throws Exception;
}
