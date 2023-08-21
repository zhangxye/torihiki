package com.tsrs.webedi.modular.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tsrs.webedi.common.constant.Const;
import com.tsrs.webedi.common.constant.Const.APPR_DOC_ST;
import com.tsrs.webedi.common.constant.Const.CONFIRM_ST;
import com.tsrs.webedi.common.constant.Const.USE_FLG;
import com.tsrs.webedi.common.persistence.dao.SysfilemgrMapper;
import com.tsrs.webedi.common.persistence.model.Sysfilemgr;
import com.tsrs.webedi.common.service.ISysFileMgrService;
import com.tsrs.webedi.core.shiro.ShiroKit;
import com.tsrs.webedi.core.util.ToolUtil;
import com.tsrs.webedi.modular.system.dao.*;
import com.tsrs.webedi.modular.system.jmodel.ApproveDtl;
import com.tsrs.webedi.modular.system.jmodel.ApproveHdr;
import com.tsrs.webedi.modular.system.persistence.dao.ApprdocacceptedMapper;
import com.tsrs.webedi.modular.system.persistence.model.*;
import com.tsrs.webedi.modular.system.service.IApprDocService;
import com.tsrs.webedi.modular.system.service.INoSeqMgrService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 稟議書サーブス
 *
 * @author Tsrs-WebEDI
 * {@code @date} 2017-05-05 22:20
 */
@Service
@Transactional
public class ApprDocServiceImpl implements IApprDocService {

	@Resource
	ApprdocDao apprDocDao;

	@Resource
	ApprdocMapper apprdocMapper;

	@Resource
	ApprcommuteroutedtlMapper commuteroutedtlMapper;

	@Resource
	ApprcommuteroutehdrMapper apprcommuteroutehdrMapper;

	@Resource
	ApprdocclientcontractevidenceMapper clientcontractevidenceMapper;

	@Resource
	ApprapplyhdrMapper apprapplyhdrMapper;

	@Resource
	ApprapplydtlMapper apprapplydtlMapper;

	@Resource
	private INoSeqMgrService noSeqMgrService;

	@Resource
	private ApprdocacceptedMapper apprdocacceptedMapper;

	@Resource
	SysfilemgrMapper sysfilemgrMapper;
	
	@Resource
	ISysFileMgrService sysFileMgrService;
	

	@Override
	public List<Map<String, Object>> selectEmpHisList(String empno) {
		return apprDocDao.selectEmpHisList(empno);
	}

	@Override
	public String insertApprDocService(ApprdocExt apprDoc) throws Exception {
		
		//チェック
		List<Apprdoc> apprDocList = getOverlapApprDocInfo(apprDoc.getEmpNo(),
				apprDoc.getApprDocDtFrom(), 
				apprDoc.getApprDocDtTo());
		if(apprDocList != null && apprDocList.size() > 0){
			throw new Exception("該当要員の受注期間は既存の稟議書データと重なっています。");
		}

		//登録
		String pgId = Const.PG.APPR_DOC + Const.PAGE.ENT;

		String apprDocNo = noSeqMgrService.getFieldNo(Const.SYS_CD, "apprDoc", "appr_doc_no");
		if (apprDocNo == null) {
			throw new Exception("稟議書NOの採番が失敗しました。");
		}
		Apprdoc apprDocEnt = new Apprdoc();
		BeanUtils.copyProperties(apprDocEnt, apprDoc);
		apprDocEnt.setApprDocId(null);
		apprDocEnt.setApprDocNo(apprDocNo);
		apprDocEnt.setUseFlg(Const.USE_FLG.VALID);
		apprDocEnt.setApprDocSt(Const.APPR_DOC_ST.APPLY_WAIT);
		// apprDocEnt.setSysInsUserId(ShiroKit.getUser().getAccount());
		// apprDocEnt.setSysInsDt(new Date());
		apprDocEnt.setSysInsPgId(pgId);

		this.apprdocMapper.insert(apprDocEnt);

		// アップロードファイルを有効にする
		sysFileMgrService.updateFileToValid(apprDocEnt.getMonAttachFileId(), pgId);

		int displayOrder = 0;
		for (Apprdocclientcontractevidence evidence : apprDoc.getWorkEvidenceList()) {

			evidence.setApprDocId(apprDocEnt.getApprDocId());
			evidence.setDisplayOrder(++displayOrder);
			// evidence.setSysInsUserId(ShiroKit.getUser().getAccount());
			// evidence.setSysInsDt(new Date());
			evidence.setSysInsPgId(pgId);
			this.clientcontractevidenceMapper.insert(evidence);
		}

		Apprcommuteroutehdr apprcommuteroutehdr = new Apprcommuteroutehdr();
		apprcommuteroutehdr.setApprDocId(apprDocEnt.getApprDocId());
		apprcommuteroutehdr.setDayTravelCost(apprDoc.getDayTravelCost());
		apprcommuteroutehdr.setMonthPassTravelCost(apprDoc.getMonthPassTravelCost());
		apprcommuteroutehdr.setPayTravelCostaMax(apprDoc.getPayTravelCostaMax());
		apprcommuteroutehdr.setRountDltContent(apprDoc.getRountDltContent());
		// apprcommuteroutehdr.setSysInsUserId(ShiroKit.getUser().getAccount());
		// apprcommuteroutehdr.setSysInsDt(new Date());
		apprcommuteroutehdr.setSysInsPgId(pgId);

		this.apprcommuteroutehdrMapper.insert(apprcommuteroutehdr);

		for (Apprcommuteroutedtl apprcommuteroutedtl : apprDoc.getCommuteRouteList()) {
			apprcommuteroutedtl.setApprDocId(apprDocEnt.getApprDocId());
			// apprcommuteroutedtl.setSysInsUserId(ShiroKit.getUser().getAccount());
			// apprcommuteroutedtl.setSysInsDt(new Date());
			apprcommuteroutedtl.setSysInsPgId(pgId);
			this.commuteroutedtlMapper.insert(apprcommuteroutedtl);
		}

		return apprDocNo;
	}

	// 更新
	public int updateApprDocService(ApprdocExt apprDoc) throws Exception {
		String pgId = Const.PG.APPR_DOC + Const.PAGE.UPD;

		// 稟議書
		Apprdoc apprDocEnt = new Apprdoc();
		BeanUtils.copyProperties(apprDocEnt, apprDoc);
		apprDocEnt.setSysUpdPgId(pgId);

		this.apprdocMapper.updateById(apprDocEnt);
		
		// アップロードファイルを有効にする
		sysFileMgrService.updateFileToValid(apprDocEnt.getMonAttachFileId(), pgId);


		// クライアント契約契約条件エビデンス
		// Delete -> Insert
		Map<String, Object> eviDelKey = new HashMap<String, Object>();
		eviDelKey.put("appr_doc_id", apprDocEnt.getApprDocId());
		this.clientcontractevidenceMapper.deleteByMap(eviDelKey);

		int displayOrder = 0;
		for (Apprdocclientcontractevidence evidence : apprDoc.getWorkEvidenceList()) {

			evidence.setApprDocId(apprDocEnt.getApprDocId());
			evidence.setDisplayOrder(++displayOrder);
			evidence.setSysInsPgId(pgId);
			this.clientcontractevidenceMapper.insert(evidence);
		}

		// 通勤経路Hdr
		Apprcommuteroutehdr apprcommuteroutehdr = new Apprcommuteroutehdr();
		apprcommuteroutehdr.setApprDocId(apprDocEnt.getApprDocId());
		apprcommuteroutehdr.setDayTravelCost(apprDoc.getDayTravelCost());
		apprcommuteroutehdr.setMonthPassTravelCost(apprDoc.getMonthPassTravelCost());
		apprcommuteroutehdr.setPayTravelCostaMax(apprDoc.getPayTravelCostaMax());
		apprcommuteroutehdr.setRountDltContent(apprDoc.getRountDltContent());
		apprcommuteroutehdr.setSysUpdPgId(pgId);

		this.apprcommuteroutehdrMapper.updateById(apprcommuteroutehdr);

		// 通勤経路Dtl
		// Delete -> Insert
		Map<String, Object> routeDtlDelKey = new HashMap<String, Object>();
		routeDtlDelKey.put("appr_doc_id", apprDocEnt.getApprDocId());
		this.commuteroutedtlMapper.deleteByMap(routeDtlDelKey);
		for (Apprcommuteroutedtl apprcommuteroutedtl : apprDoc.getCommuteRouteList()) {
			apprcommuteroutedtl.setApprDocId(apprDocEnt.getApprDocId());
			apprcommuteroutedtl.setSysInsPgId(pgId);
			this.commuteroutedtlMapper.insert(apprcommuteroutedtl);
		}

		return 1;
	}

	// 削除
	public int deleteApprDocService(String apprDocNo) throws Exception {
		// String pgId = Const.PG.APPR_DOC + Const.PAGE.DEL;
		Map<String, Object> delKey = new HashMap<String, Object>();
		delKey.put("appr_doc_no", apprDocNo);
		return this.apprdocMapper.deleteByMap(delKey);
	}

	// 取消
	public int invalidateApprDocService(Integer apprDocId) throws Exception {
		String pgId = Const.PG.APPR_DOC + Const.PAGE.UPD;
		Apprdoc apprDocEnt = new Apprdoc();
		apprDocEnt.setApprDocId(apprDocId);
		apprDocEnt.setApprDocSt(Const.APPR_DOC_ST.CANCEL);
		apprDocEnt.setSysUpdPgId(pgId);

		return this.apprdocMapper.updateById(apprDocEnt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsrs.webedi.modular.system.service.IApprDocService#
	 * applyApprDocService(com.tsrs.webedi.modular.system.persistence.model.
	 * ApprapplyExt)
	 */
	public int applyApprDocService(ApprapplyExt apprApplyExt) throws Exception {
		String pgId = Const.PG.APPR_DOC + Const.PAGE.APPLY;

		Apprdoc apprDocEnt = new Apprdoc();
		apprDocEnt.setApprDocId(apprApplyExt.getApprDocId());
		apprDocEnt.setApprDocSt(Const.APPR_DOC_ST.CONFIRM_WAIT);
		apprDocEnt.setSysUpdPgId(pgId);

		this.apprdocMapper.updateById(apprDocEnt);
		
		//Delete the apply information firstly.
		Map<String,Object> apprApplyDeleteCond = new HashMap<String, Object>();
		apprApplyDeleteCond.put("appr_doc_id", apprApplyExt.getApprDocId());
		this.apprapplyhdrMapper.deleteByMap(apprApplyDeleteCond);
		//TODO：一時対策、同一稟議書データ承認済みの場合、承認データを削除する
		this.apprdocacceptedMapper.deleteByMap(apprApplyDeleteCond);

		Apprapplyhdr apprApplyHdr = new Apprapplyhdr();
		BeanUtils.copyProperties(apprApplyHdr, apprApplyExt);
		apprApplyHdr.setUseFlg(Const.USE_FLG.VALID);
		Timestamp dt = new Timestamp(System.currentTimeMillis());
		apprApplyHdr.setApplyDt(dt);
		apprApplyHdr.setApplyUserId(ShiroKit.getUser().getAccount());
		apprApplyHdr.setConfirmWaitDisplayOrder(Integer.valueOf(1));
		apprApplyHdr.setSysInsPgId(pgId);

		this.apprapplyhdrMapper.insert(apprApplyHdr);

		List<Apprapplydtl> apprApplyDtlList = apprApplyExt.getApprDocApplyDtl().stream()
				.sorted((d1, d2) -> d1.getDisplayOrder().compareTo(d2.getDisplayOrder())).collect(Collectors.toList());
		;

		Integer displayOrder = 0;
		for (Apprapplydtl apprApplyDtl : apprApplyDtlList) {

			apprApplyDtl.setApprApplyHid(apprApplyHdr.getApprApplyHid());
			apprApplyDtl.setDisplayOrder(++displayOrder);
			apprApplyDtl.setConfirmSt(Const.CONFIRM_ST.CONFIRM_WAIT);
			apprApplyDtl.setConRejComment("");
			apprApplyDtl.setSysInsPgId(pgId);
			this.apprapplydtlMapper.insert(apprApplyDtl);
		}

		return 1;

	}

	@Override
	public Map<String, Object> selectEmpLastInfo(String empno) {
		return apprDocDao.selectEmpLastInfo(empno);
	}

	@Override
	public List<Map<String, Object>> selectApprDocList(String clientNm, String empNm, String apprDocNo, String deptCd,
			String createDtFr, String createDtTo, String workMonth, String mainUserId,String arrpDocSt) {
		return apprDocDao.selectApprDocList(clientNm, empNm, apprDocNo, deptCd, createDtFr, createDtTo, workMonth,
				mainUserId,arrpDocSt);
	}

	@Override
	public Map<String, Object> selectApprDocInfo(String apprDocNo) {
		return apprDocDao.selectApprDocInfo(apprDocNo);
	}

	@Override
	public Map<String, Object> selectApprDocInfo2(String apprDocNo) {
		return apprDocDao.selectApprDocInfo2(apprDocNo);
	}

	@Override
	public Map<String, Object> selectApprDocPrevInfo(String apprDocNo) {
		return apprDocDao.selectApprDocPrevInfo(apprDocNo);
	}

	@Override
	public List<Map<String, Object>> selectApprcommuteroutedtl(String apprDocNo) {
		return commuteroutedtlMapper.selectApprcommuteroutedtlList(apprDocNo);
	}

	@Override
	public List<Map<String, Object>> selectEmpHisList2(String apprDocNo) {
		return apprDocDao.selectEmpHisList2(apprDocNo);
	}

	@Override
	public List<Map<String, Object>> selectApprApplyList(String apprDocNo) {
		return apprapplydtlMapper.selectApprApplyList(apprDocNo);
	}

	@Override
	public List<Map<String, Object>> selectApprApprove(String account, String apprDocNo, String approveDtFr,
			String approveDtTo, String approveStatus, String empNo, String empNm) {
		return apprapplyhdrMapper.selectApprApproveList(account, apprDocNo, approveDtFr, approveDtTo, approveStatus,
				empNo, empNm);
	}
	@Override
	public List<Map<String, Object>> selectEvidenceList(String apprDocNo) {
		return clientcontractevidenceMapper.selectEvidenceList(apprDocNo);
	}

	@Override
	public List<Map<String, Object>> selectFileList(Integer mon_attach_file_id) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		Sysfilemgr fileMgr = new Sysfilemgr();
		fileMgr.setFileId(mon_attach_file_id);
		fileMgr.setUseFlg(Const.USE_FLG.VALID);
		QueryWrapper<Sysfilemgr> wrapper = new QueryWrapper<Sysfilemgr>(fileMgr);
		List<Sysfilemgr> listSysFile = sysfilemgrMapper.selectList(wrapper);
		if (null != listSysFile) {
			Map<String, Object> map;
			for (int i = 0; i < listSysFile.size(); i++) {
				map = new HashMap<String, Object>();
				map.put("name", listSysFile.get(i).getFileNm());
				map.put("url", listSysFile.get(i).getFilePath());
				listMap.add(map);
			}
		}
		return listMap;
	}

	@Override
	public ApproveHdr goApprove(Integer apprDocId, Integer apprApplyHid) throws Exception {
		Apprapplyhdr entity = new Apprapplyhdr();
		entity.setApprDocId(apprDocId);
		entity.setApprApplyHid(apprApplyHid);
		entity.setUseFlg(Const.USE_FLG.VALID);
		QueryWrapper<Apprapplyhdr> qw = new QueryWrapper<>(entity);
		entity = apprapplyhdrMapper.selectOne(qw);

		List<Map<String, Object>> obj = apprapplydtlMapper.selectApplyList(apprDocId, apprApplyHid);
		ApproveHdr approveHdr = new ApproveHdr();
		approveHdr.setApprDocId(entity.getApprDocId());
		approveHdr.setApply_reason(entity.getApplyReason());
		approveHdr.setSave_btn_valid_flg(!Objects.requireNonNull(ShiroKit.getUser()).getAccount().equals(entity.getApplyUserId()));
		if (obj != null && obj.size() > 0) {
			ApproveDtl dtl = new ApproveDtl();
			List<ApproveDtl> approveDtlList = new ArrayList<>();
			for (Map<String, Object> stringObjectMap : obj) {
				approveDtlList.add((ApproveDtl) ToolUtil.copy(stringObjectMap, dtl));
			}

			approveHdr.setApprApplyDtl(approveDtlList);
		}
		approveHdr.setConfrim_st("2");
		return approveHdr;
	}

	@Override
	public int approve(Integer apprDocId, String account, String conRejComment, String confirmSt) {
		Apprapplyhdr conApprHdr = new Apprapplyhdr();
		conApprHdr.setApprDocId(apprDocId);
		conApprHdr.setUseFlg(USE_FLG.VALID);
		QueryWrapper<Apprapplyhdr> qw = new QueryWrapper<Apprapplyhdr>(conApprHdr);
		Apprapplyhdr entity = apprapplyhdrMapper.selectOne(qw);

		Apprapplydtl conApprDtl = new Apprapplydtl();
		conApprDtl.setApprApplyHid(entity.getApprApplyHid());
		Long count = apprapplydtlMapper.selectCount(new QueryWrapper<Apprapplydtl>(conApprDtl));
		conApprDtl.setAcceptUserId(account);
		Apprapplydtl apprapplydtl = apprapplydtlMapper.selectOne(new QueryWrapper<Apprapplydtl>(conApprDtl));
		apprapplydtl.setConRejDt(new Date());
		apprapplydtl.setConRejComment(conRejComment);
		apprapplydtl.setConfirmSt(confirmSt);
//		Wrapper<Apprapplydtl> ew = new EntityWrapper<Apprapplydtl>(conApprDtl);
		apprapplydtlMapper.update(apprapplydtl, new UpdateWrapper<Apprapplydtl>(conApprDtl));
		if (CONFIRM_ST.APPROVAL.equals(confirmSt)) {
			if (entity.getConfirmWaitDisplayOrder() == count.intValue()) {
				Apprdoc apprDoc = new Apprdoc();
				apprDoc.setApprDocId(apprDocId);
				apprDoc.setUseFlg(USE_FLG.VALID);

				Apprdoc entityDoc = apprdocMapper.selectOne(new QueryWrapper<Apprdoc>(apprDoc));
				entityDoc.setApprDocSt(APPR_DOC_ST.CONFIRM_FINISH);
				UpdateWrapper<Apprdoc> ape = new UpdateWrapper<Apprdoc>(apprDoc);
				apprdocMapper.update(entityDoc, ape);

				Apprdocaccepted accepted = new Apprdocaccepted();
				accepted.setApprDocId(apprDocId);
				accepted.setApprovalApplyHid(entity.getApprApplyHid());
				accepted.setLastAcceptDate(new Date());
				accepted.setLastAcceptUserId(account);
				accepted.setSysInsUserId(account);
				accepted.setSysInsPgId("APP");
				accepted.setSysInsDt(new Date());
				accepted.setSysUpdUserId(account);
				accepted.setSysUpdPgId("APP");
				accepted.setSysUpdDt(new Date());
				apprdocacceptedMapper.insert(accepted);

			} else {
				if (entity.getConfirmWaitDisplayOrder() == 1) {
					Apprdoc apprDoc = new Apprdoc();
					apprDoc.setApprDocId(apprDocId);
					apprDoc.setUseFlg(USE_FLG.VALID);
					Apprdoc entityDoc = apprdocMapper.selectOne(new QueryWrapper<Apprdoc>(apprDoc));
					entityDoc.setApprDocSt(APPR_DOC_ST.CONFIRM_RUNNING);
					UpdateWrapper<Apprdoc> ape = new UpdateWrapper<Apprdoc>(apprDoc);

					apprdocMapper.update(entityDoc, ape);
				}
				entity.setConfirmWaitDisplayOrder(entity.getConfirmWaitDisplayOrder() + 1);
			}

		} else if (CONFIRM_ST.REJECT.equals(confirmSt)) {
			Apprdoc apprDoc = new Apprdoc();
			apprDoc.setApprDocId(apprDocId);
			apprDoc.setUseFlg(USE_FLG.VALID);

			Apprdoc entityDoc = apprdocMapper.selectOne(new QueryWrapper<Apprdoc>(apprDoc));
			entityDoc.setApprDocSt(APPR_DOC_ST.APPLY_WAIT);
			UpdateWrapper<Apprdoc> ape = new UpdateWrapper<Apprdoc>(apprDoc);

			apprdocMapper.update(entityDoc, ape);

		}
		UpdateWrapper<Apprapplyhdr> ewh = new UpdateWrapper<Apprapplyhdr>(conApprHdr);
		apprapplyhdrMapper.update(entity, ewh);

		return 0;
	}
	
	/**
	 * 該当要員の受注時間と重なっている稟議書データを取得
	 * @param empNo
	 * @param apprDocDtFrom
	 * @param apprDocDtTo
	 * @return
	 */
	public List<Apprdoc> getOverlapApprDocInfo(String empNo, Date apprDocDtFrom, Date apprDocDtTo) {

	    QueryWrapper<Apprdoc> ew = new QueryWrapper<Apprdoc>();
	    ew.setEntity(new Apprdoc());
	    ew.eq("emp_no", empNo)
	      .and(i -> i.and( j -> j.le("appr_doc_dt_from", apprDocDtFrom).ge("appr_doc_dt_to", apprDocDtFrom)).or().and( j -> j.le("appr_doc_dt_from", apprDocDtTo).ge("appr_doc_dt_to", apprDocDtTo)))
	      .ne("appr_doc_st", "A")
	      .orderByAsc("appr_doc_dt_from");
//	            .andNew("{0} BETWEEN appr_doc_dt_from AND appr_doc_dt_to", apprDocDtFrom)
//	            .or("{0} BETWEEN appr_doc_dt_from AND appr_doc_dt_to", apprDocDtTo)
//	            .andNew("appr_doc_st != 'A'")
//	            .orderBy("appr_doc_dt_from ASC");  
		return apprdocMapper.selectList(ew);
	}
	
	
	public Page<Map<String, Object>> searchForMonReqList(Page<Map<String, Object>> page, Map<String, Object> searchCond) {
		return page.setRecords(apprDocDao.searchForMonReqList(page, searchCond));
	}
	
	/**
	 * 月次請求データ更新
	 * @param apprDoc
	 * @return
	 * @throws Exception
	 */
	public boolean updateApprDocMonReqInfo(Apprdoc apprDoc) throws Exception{
		String pgId = Const.PG.MON_REQ + Const.PAGE.UPD;

		// 稟議書
		apprDoc.setSysUpdPgId(pgId);
		return apprDoc.updateById();
	
	}

}
