package com.tsrs.webedi.modular.system.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.tsrs.webedi.common.JsonResult;
import com.tsrs.webedi.common.constant.Const;
import com.tsrs.webedi.common.controller.BaseController;
import com.tsrs.webedi.modular.system.service.IJobnaviService;
/**
 * JobNavi Controller
 *
 * @author Tsrs-WebEDI
 * @Date 2017年1月11日 下午1:08:17
 */
@RestController
@RequestMapping("/jobnavi")
public class JobnaviController extends BaseController {
	@Resource
	private IJobnaviService jobnaviService;
	/**
	 * SES契約技術者登録 氏名
	 * 
	 * @return
	 */
	@RequestMapping(value = "/candidateList", method = RequestMethod.GET)
	public JsonResult candidateList(@RequestParam String empName) {
		List<Map<String, Object>> obj = jobnaviService.selectJobnaviCandidateList(empName);
		return new JsonResult(RETURN_CODE_200, obj);
	}
	/**
	 * SES契約技術者登録 氏名
	 * 
	 * @return
	 */
	@RequestMapping(value = "/candidate", method = RequestMethod.GET)
	public JsonResult candidate(@RequestParam String empno) {
		Map<String, Object> obj = jobnaviService.selectJobnaviCandidate(empno);
		return new JsonResult(RETURN_CODE_200, obj);
	}

	/**
	 * SES契約技術者登録 案件
	 * 
	 * @return
	 */
	@RequestMapping(value = "/jobList", method = RequestMethod.GET)
	public JsonResult jobList(@RequestParam String pjNm) {
		List<Map<String, Object>> obj = jobnaviService.selectJobnaviJobList(pjNm);
		return new JsonResult(RETURN_CODE_200, obj);
	}
	
	/**
	 * SES契約技術者登録 案件
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getPjAllInfo", method = RequestMethod.GET)
	public JsonResult getPjAllInfo(@RequestParam String pjCd) {
		Map<String, Object> obj = jobnaviService.selectJobnaviJob(pjCd);
		return new JsonResult(RETURN_CODE_200, obj);
	}

	/**
	 * SES契約技術クライアント
	 * 
	 * @return
	 */
	@RequestMapping(value = "/clientList", method = RequestMethod.GET)
	public JsonResult clientList(@RequestParam String categoryId,@RequestParam String clientNm) {
		List<Map<String, Object>> obj = jobnaviService.selectJobnaviCompanyList(categoryId, clientNm);
		return new JsonResult(RETURN_CODE_200, obj);
	}
	
	/**
	 * クライアント情報
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getClientAllInfo", method = RequestMethod.GET)
	public JsonResult clientInfo(@RequestParam("clientNo") String compayId) {
		Map<String, Object> obj = jobnaviService.selectJobnaviClientInfo(compayId,null);
		return new JsonResult(RETURN_CODE_200, obj);
	}
	
	/**
	 * 担当者リスト
	 * 
	 * @return
	 */
	@RequestMapping(value = "/staffList", method = RequestMethod.GET)
	public JsonResult clientList(@RequestParam("compayId") String compayId) {
		List<Map<String, Object>> obj = jobnaviService.selectJobnaviStaffList(compayId);
		return new JsonResult(RETURN_CODE_200, obj);
	}
	
	/**
	 * BP情報
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getBpAllInfo", method = RequestMethod.GET)
	public JsonResult getBpAllInfo(@RequestParam("clientNo") String compayId) {
		Map<String, Object> obj = jobnaviService.selectJobnaviClientInfo(compayId, Const.COMPANY_CATE.BP);
		return new JsonResult(RETURN_CODE_200, obj);
	}
	
}
