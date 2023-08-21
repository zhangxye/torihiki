package com.tsrs.webedi.modular.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tsrs.webedi.common.JsonResult;
import com.tsrs.webedi.common.controller.BaseController;
import com.tsrs.webedi.modular.system.persistence.model.Esthdr;
import com.tsrs.webedi.modular.system.service.IEstService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * <p>
 * 見積HDR情報 前端控制器
 * </p>
 *
 * @author jin-qk
 * @since 2018-03-26
 */
@RestController
@RequestMapping("/est")
public class EstController extends BaseController {

	@Resource
	private IEstService estService;

	@RequestMapping(value = "/searchForEntry", method = RequestMethod.POST)
	public JsonResult searchForEntry(@RequestBody Map<String, Object> searchCond) {

		Page<Map<String, Object>> pageInfo = new Page<>((Integer) searchCond.get("current"),
				(Integer) searchCond.get("size"));
		try {
			pageInfo = estService.searchForEntry(pageInfo, searchCond);
			return new JsonResult(RETURN_CODE_200, pageInfo);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	@RequestMapping(value = "/searchForDetail", method = RequestMethod.POST)
	public JsonResult searchForDetail(@RequestBody Map<String, Object> searchCond) {

		Page<Map<String, Object>> pageInfo = new Page<>((Integer) searchCond.get("current"),
				(Integer) searchCond.get("size"));
		try {
			pageInfo = estService.searchForDetail(pageInfo, searchCond);
			return new JsonResult(RETURN_CODE_200, pageInfo);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	@RequestMapping(value = "/selectForInsert", method = RequestMethod.GET)
	public JsonResult selectForInsert(@RequestParam(value = "appr_doc_id_list") String apprDocIdList) {

		try {
			Map<String, Object> ret = estService.selectForInsert(apprDocIdList);
			return new JsonResult(RETURN_CODE_200, ret);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	@RequestMapping(value = "/selectForDetail", method = RequestMethod.GET)
	public JsonResult selectForDetail(@RequestParam(value = "est_hid") Integer estHid) {

		try {
			Map<String, Object> ret = estService.selectForDetail(estHid);
			return new JsonResult(RETURN_CODE_200, ret);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult insert(@RequestBody Map<String, Object> estInfo) {
		try {
			Integer estHid = estService.insertEstInfo(estInfo);
			return new JsonResult(RETURN_CODE_200, estHid);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult insert(@RequestBody Esthdr estHdr) {
		try {
			Integer estHid = estService.updateEstInfo(estHdr);
			return new JsonResult(RETURN_CODE_200, estHid);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public JsonResult insert(@RequestParam(value = "est_hid") Integer estHid) {
		try {
			estService.deleteEstInfo(estHid);
			return new JsonResult(RETURN_CODE_200);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	@RequestMapping(value = "/issueEstDoc", method = RequestMethod.GET)
	public JsonResult issueEstDoc(@RequestParam(value = "est_hid") Integer estHid,
								  HttpServletResponse res) {
		try {
			String issueDt = estService.issueEstDoc(estHid, res);
			return new JsonResult(RETURN_CODE_200, issueDt);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	@RequestMapping(value = "/deliveryEstDoc", method = RequestMethod.GET)
	public JsonResult deliveryEstDoc(@RequestParam(value = "est_hid") Integer estHid) {
		try {
			String deliveryDt = estService.deliveryEstDoc(estHid);
			return new JsonResult(RETURN_CODE_200, deliveryDt);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

}
