package com.tsrs.webedi.modular.system.controller;

import com.alibaba.druid.util.StringUtils;
import com.tsrs.webedi.common.JsonResult;
import com.tsrs.webedi.common.controller.BaseController;
import com.tsrs.webedi.common.page.PageInfo;
import com.tsrs.webedi.core.shiro.ShiroKit;
import com.tsrs.webedi.core.util.Convert;
import com.tsrs.webedi.core.util.ToolUtil;
import com.tsrs.webedi.modular.system.jmodel.ApprDocInfo;
import com.tsrs.webedi.modular.system.jmodel.ApprDocInq;
import com.tsrs.webedi.modular.system.jmodel.ApproveHdr;
import com.tsrs.webedi.modular.system.persistence.model.ApprapplyExt;
import com.tsrs.webedi.modular.system.persistence.model.Apprapplydtl;
import com.tsrs.webedi.modular.system.persistence.model.ApprdocExt;
import com.tsrs.webedi.modular.system.service.IApprDocService;
import com.tsrs.webedi.modular.system.service.IJobnaviService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * 稟議書Controller
 *
 * @author Tsrs-WebEDI
 * &#064;Date  2017年1月11日 下午1:08:17
 */
@RestController
@RequestMapping("/apprDoc")
public class ApprDocController extends BaseController {

	@Resource
	private IApprDocService apprDocService;

	@Resource
	private IJobnaviService jobnaviService;

	@RequestMapping(value = "/getEmpAllInfo", method = RequestMethod.GET)
	public JsonResult getEmpAllInfo(@RequestParam(value = "empno") String empno) {
		Map<String, Object> resData = new HashMap<>();
		// 要員基本情報
		Map<String, Object> empInfo = jobnaviService.selectJobnaviCandidate(empno);
		resData.put("emp_info", empInfo);
		// 要員入社履歴情報
		List<Map<String, Object>> empHisList = apprDocService.selectEmpHisList(empno);
		resData.put("entrance_record_list", empHisList);
		if (empHisList == null || empHisList.size() == 0) {
			resData.put("entrance_record_k", "0");
		} else {
			resData.put("entrance_record_k", "1");
		}
		// 前回稟議書データ
		Map<String, Object> lastApprDocInfo = apprDocService.selectEmpLastInfo(empno);
		resData.put("apprDocInfo_last", lastApprDocInfo);
		return new JsonResult(RETURN_CODE_200, resData);
	}

	@RequestMapping(value = "/getEmpAllInfo2", method = RequestMethod.GET)
	public JsonResult getEmpAllInfo2(@RequestParam(value = "empno") String empno,
									 @RequestParam(value = "apprDocNo") String apprDocNo) {
		Map<String, Object> resData = new HashMap<>();
		// 要員基本情報
		Map<String, Object> empInfo = jobnaviService.selectJobnaviCandidate(empno);
		resData.put("emp_info", empInfo);
		// 要員入社履歴情報
		List<Map<String, Object>> empHisList = apprDocService.selectEmpHisList2(apprDocNo);
		resData.put("entrance_record_list", empHisList);
		if (empHisList == null || empHisList.size() == 0) {
			resData.put("entrance_record_k", "0");
		} else {
			resData.put("entrance_record_k", "1");
		}
		// 前回稟議書データ
		return new JsonResult(RETURN_CODE_200, resData);
	}

	/**
	 * 稟議書登録
	 */
	@RequestMapping(value = "/lastInfo", method = RequestMethod.GET)
	public JsonResult lastInfo(@RequestParam(value = "empno") String empno) {
		try {
			Map<String, Object> obj = apprDocService.selectEmpLastInfo(empno);
			return new JsonResult(RETURN_CODE_200, obj);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	/**
	 * 稟議書登録
	 */
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult regiter(@RequestBody ApprdocExt apprDoc) {
		try {
			String apprDocNo = apprDocService.insertApprDocService(apprDoc);
			return new JsonResult(RETURN_CODE_200, "稟議書が登録されました。", apprDocNo);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	/**
	 * 稟議書更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult update(@RequestBody ApprdocExt apprDoc) {
		try {
			apprDocService.updateApprDocService(apprDoc);
			return new JsonResult(RETURN_CODE_200, "稟議書が更新されました。");
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	/**
	 * 稟議書更新
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public JsonResult delete(@RequestParam(value = "apprDocNo") String apprDocNo) {
		try {
			apprDocService.deleteApprDocService(apprDocNo);
			return new JsonResult(RETURN_CODE_200, "稟議書が削除されました。");
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	/**
	 * 稟議書更新
	 */
	@RequestMapping(value = "/invalidate", method = RequestMethod.GET)
	public JsonResult invalidate(@RequestParam(value = "apprDocId") Integer apprDocId) {
		try {
			apprDocService.invalidateApprDocService(apprDocId);
			return new JsonResult(RETURN_CODE_200, "稟議書が取消されました。");
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	/**
	 * 稟議書申請
	 */
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	public JsonResult apply(@RequestBody ApprapplyExt apprApplyExt) {
		try {
			List<Apprapplydtl> apprApplyDtlList = apprApplyExt.getApprDocApplyDtl();
			List<String> acceptList = new ArrayList<>();
			for (Apprapplydtl apprApplyDtl : apprApplyDtlList) {
				if (Objects.requireNonNull(ShiroKit.getUser()).getAccount().equals(apprApplyDtl.getAcceptUserId())) {
					throw new Exception("承認者はログインと同じですので、再入力してください");
				}
				if (!acceptList.contains(apprApplyDtl.getAcceptUserId())) {
					acceptList.add(apprApplyDtl.getAcceptUserId());
				} else {
					throw new Exception("同じ承認者が存在するので、再入力してください");
				}
				
			}
			apprDocService.applyApprDocService(apprApplyExt);
			return new JsonResult(RETURN_CODE_200, "稟議書が取消されました。");
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	/**
	 * 稟議書検索
	 * 
	 * @param page
	 *            ページ数
	 * @param pageSize
	 *            改頁件数
	 * @param clientNm
	 *            クライアント名称
	 * @param empNm
	 *            氏名
	 * @param apprDocNo
	 *            稟議書NO
	 * @param createDtFr
	 *            作成日fr
	 * @param createDtTo
	 *            作成日to
	 * @param workMonth
	 *            稼動月
	 * @param mainUserId
	 *            営業主担当
	 */
	@RequestMapping(value = "/selectApprDoc", method = RequestMethod.GET)
	public JsonResult selectApprDocList(@RequestParam(value = "page") String page,
										@RequestParam(value = "pageSize") String pageSize,
										@RequestParam(value = "clientNm", required = false) String clientNm,
										@RequestParam(value = "empNm", required = false) String empNm,
										@RequestParam(value = "apprDocNo", required = false) String apprDocNo,
										@RequestParam(value = "deptCd", required = false) String deptCd,
										@RequestParam(value = "createDtFr", required = false) String createDtFr,
										@RequestParam(value = "createDtTo", required = false) String createDtTo,
										@RequestParam(value = "workMonth", required = false) String workMonth,
										@RequestParam(value = "mainUserId", required = false) String mainUserId,
										@RequestParam(value = "arrpDocSt", required = false) String arrpDocSt) {
		try {
			if (StringUtils.isEmpty(page)) {
				page = "1";
			}
			if (StringUtils.isEmpty(pageSize)) {
				pageSize = "10";
			}

			List<Map<String, Object>> listValue = apprDocService.selectApprDocList(clientNm, empNm, apprDocNo, deptCd,
					createDtFr, createDtTo, workMonth, mainUserId, arrpDocSt);
			PageInfo<Map<String, Object>> pageInfo1 = page(Integer.parseInt(page), listValue, Integer.parseInt(pageSize));
			List<ApprDocInq> pagedata = new ArrayList<>();
			for (int i = 0; i < pageInfo1.getPageData().size(); i++) {
				ApprDocInq c = new ApprDocInq();
				c.setAppr_doc_no(Convert.toStr(pageInfo1.getPageData().get(i).get("appr_doc_no")));
				c.setAppr_doc_st(Convert.toStr(pageInfo1.getPageData().get(i).get("appr_doc_st")));
				c.setAppr_doc_create_k_nm(Convert.toStr(pageInfo1.getPageData().get(i).get("appr_doc_create_k_nm")));
				c.setAppr_doc_create_dt(Convert.toStr(pageInfo1.getPageData().get(i).get("appr_doc_create_dt")));
				c.setAppr_doc_dt_from(Convert.toStr(pageInfo1.getPageData().get(i).get("appr_doc_dt_from")));
				c.setAppr_doc_dt_to(Convert.toStr(pageInfo1.getPageData().get(i).get("appr_doc_dt_to")));
				c.setEmp_no(Convert.toStr(pageInfo1.getPageData().get(i).get("emp_no")));
				c.setEmp_nm(Convert.toStr(pageInfo1.getPageData().get(i).get("emp_nm")));
				c.setClient_no(Convert.toStr(pageInfo1.getPageData().get(i).get("client_no")));
				c.setClient_nm(Convert.toStr(pageInfo1.getPageData().get(i).get("client_nm")));
				c.setPj_cd(Convert.toStr(pageInfo1.getPageData().get(i).get("pj_cd")));
				c.setPj_nm(Convert.toStr(pageInfo1.getPageData().get(i).get("pj_nm")));
				c.setDel_flg(Convert.toBool(pageInfo1.getPageData().get(i).get("del_flg")));
				pagedata.add(c);

			}

			PageInfo<ApprDocInq> pageInfo = page(Integer.parseInt(page), pagedata, Integer.parseInt(pageSize),
					listValue.size());

			return new JsonResult(RETURN_CODE_200, pageInfo);

		} catch (Exception e) {

			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}


	@RequestMapping(value = "/selectApprApprove", method = RequestMethod.GET)
	public JsonResult selectApprApproveList(@RequestParam(value = "page") String page,
											@RequestParam(value = "pageSize") String pageSize,
											@RequestParam(value = "apprDocNo", required = false) String apprDocNo,
											@RequestParam(value = "approveDtFr", required = false) String approveDtFr,
											@RequestParam(value = "approveDtTo", required = false) String approveDtTo,
											@RequestParam(value = "approveStatus", required = false) String approveStatus,
											@RequestParam(value = "empNo", required = false) String empNo,
											@RequestParam(value = "empNm", required = false) String empNm) {
		try {
			if (StringUtils.isEmpty(page)) {
				page = "1";
			}
			if (StringUtils.isEmpty(pageSize)) {
				pageSize = "10";
			}

			List<Map<String, Object>> listValue = apprDocService.selectApprApprove(Objects.requireNonNull(ShiroKit.getUser()).getAccount(),
					apprDocNo, approveDtFr, approveDtTo, approveStatus, empNo, empNm);
			PageInfo<Map<String, Object>> pageInfo = page(Integer.parseInt(page), listValue, Integer.parseInt(pageSize));

			return new JsonResult(RETURN_CODE_200, pageInfo);

		} catch (Exception e) {

			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	/**
	 * 稟議書詳細
	 */
	@RequestMapping(value = "/goApprove", method = RequestMethod.GET)
	public JsonResult goApprove(@RequestParam(value = "apprDocId") String apprDocId,
								@RequestParam(value = "apprApplyHid") String apprApplyHid) {
		try {
			ApproveHdr approveHdr = apprDocService.goApprove(Integer.valueOf(apprDocId), Integer.valueOf(apprApplyHid));
			return new JsonResult(RETURN_CODE_200, approveHdr);

		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}

	}

	/**
	 * 稟議書詳細
	 *
	 */
	@RequestMapping(value = "/approve", method = RequestMethod.PUT)
	public JsonResult approve(@RequestBody ApproveHdr arprove) {
		try {
			apprDocService.approve(arprove.getApprDocId(), Objects.requireNonNull(ShiroKit.getUser()).getAccount(),
					arprove.getCon_rej_comment(), arprove.getConfrim_st());
			return new JsonResult(RETURN_CODE_200, "承認する完了しました。");

		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}

	}

	/**
	 * 稟議書詳細
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public JsonResult detail(@RequestParam(value = "apprDocNo") String apprDocNo) {
		try {

			Map<String, Object> obj = apprDocService.selectApprDocInfo(apprDocNo);
			setRoute(apprDocNo, obj);
			return new JsonResult(RETURN_CODE_200, obj);

		} catch (Exception e) {

			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	@RequestMapping(value = "/detail2", method = RequestMethod.GET)
	public JsonResult detail2(@RequestParam(value = "apprDocNo") String apprDocNo) {
		try {

			Map<String, Object> obj = apprDocService.selectApprDocInfo2(apprDocNo);

			setRoute(apprDocNo, obj);
			ApprDocInfo info = new ApprDocInfo();
			info = (ApprDocInfo) ToolUtil.copy(obj, info);
			return new JsonResult(RETURN_CODE_200, info);

		} catch (Exception e) {

			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	private void setRoute(@RequestParam("apprDocNo") String apprDocNo, Map<String, Object> obj) {
		List<Map<String, Object>> routeList = apprDocService.selectApprcommuteroutedtl(apprDocNo);
		if (null == routeList || routeList.size() == 0) {
			routeList = new ArrayList<>();
		}
		obj.put("commute_route_list", routeList);

		List<Map<String, Object>> evidenceList = apprDocService.selectEvidenceList(apprDocNo);
		if (null == evidenceList || evidenceList.size() == 0) {
			evidenceList = new ArrayList<>();
		}
		obj.put("work_evidence_list", evidenceList);

		Object fileId = obj.get("mon_attach_file_id");
		if (null != fileId) {
			List<Map<String, Object>> fileList = apprDocService
					.selectFileList((Integer) fileId);
			if (null != fileList) {
				obj.put("fileList", fileList);
			}
		}
	}

	/**
	 * 稟議書詳細
	 */
	@RequestMapping(value = "/detailLast", method = RequestMethod.GET)
	public JsonResult detailLast(@RequestParam(value = "apprDocNo") String apprDocNo) {
		try {

			Map<String, Object> obj = apprDocService.selectApprDocPrevInfo(apprDocNo);

			return new JsonResult(RETURN_CODE_200, obj);

		} catch (Exception e) {

			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	/**
	 * 稟議書履歴
	 */
	@RequestMapping(value = "/emphis", method = RequestMethod.GET)
	public JsonResult emphis(@RequestParam(value = "apprDocNo") String apprDocNo) {
		try {

			List<Map<String, Object>> obj = apprDocService.selectEmpHisList2(apprDocNo);

			return new JsonResult(RETURN_CODE_200, obj);

		} catch (Exception e) {

			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	/**
	 * 稟議書履歴
	 */
	@RequestMapping(value = "/wflist", method = RequestMethod.GET)
	public JsonResult wflist(@RequestParam(value = "apprDocNo") String apprDocNo) {
		try {

			List<Map<String, Object>> obj = apprDocService.selectApprApplyList(apprDocNo);

			return new JsonResult(RETURN_CODE_200, obj);

		} catch (Exception e) {

			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

}
