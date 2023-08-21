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
import com.tsrs.webedi.common.persistence.model.LabelValueBean;
import com.tsrs.webedi.modular.system.service.ICommonService;
/**
 * 共通 Controller
 *
 * @author Tsrs-WebEDI
 * @Date 2017年1月11日 下午1:08:17
 */
@RestController
@RequestMapping("/common")
public class CommonController extends BaseController {
	@Resource
	private ICommonService commonService;
	/**
	 * @return
	 */
	@RequestMapping(value = "/down_str_val1", method = RequestMethod.GET)
	public JsonResult getDropdownStrVal1(@RequestParam String sysCd,@RequestParam String majorClassCd,@RequestParam String midClassCd) {
		List<LabelValueBean> obj = commonService.getDropdownStrVal1(sysCd, majorClassCd, midClassCd);
		return new JsonResult(RETURN_CODE_200, obj);
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/dropDownList", method = RequestMethod.GET)
	public JsonResult getDropDownList(@RequestParam(name="class_cd") String majorClassCd) {
		List<LabelValueBean> obj = commonService.getDropdownStrVal1(Const.SYS_CD, majorClassCd, null);
		return new JsonResult(RETURN_CODE_200, obj);
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/joblist", method = RequestMethod.GET)
	public JsonResult getJobList() {
		List<LabelValueBean> obj = commonService.getJobList();
		return new JsonResult(RETURN_CODE_200, obj);
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/insuranceInfo", method = RequestMethod.GET)
	public JsonResult getInsuranceInfo(@RequestParam String baseDt) {
		Map<String, Object> obj = commonService.getInsuranceInfo(baseDt);
		return new JsonResult(RETURN_CODE_200, obj);
	}
	
	

}
