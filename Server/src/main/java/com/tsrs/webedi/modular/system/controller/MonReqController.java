package com.tsrs.webedi.modular.system.controller;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tsrs.webedi.common.JsonResult;
import com.tsrs.webedi.common.controller.BaseController;
import com.tsrs.webedi.modular.system.persistence.model.Apprdoc;
import com.tsrs.webedi.modular.system.service.IApprDocService;

/**
 * <p>
 * 月次請求一覧 前端控制器
 * </p>
 *
 * @author jin-qk
 * @since 2018-06-14
 */
@RestController
@RequestMapping("/monReq")
public class MonReqController extends BaseController {

	@Resource
	private IApprDocService apprDocService;

	//検索一覧
	@RequestMapping(value = "/searchForMonReqList", method = RequestMethod.POST)
	public JsonResult searchForMonReqList(@RequestBody Map<String, Object> searchCond) {

		Page<Map<String, Object>> pageInfo = new Page<Map<String, Object>>((Integer) searchCond.get("current"),
				(Integer) searchCond.get("size"));
		try {
			pageInfo = apprDocService.searchForMonReqList(pageInfo, searchCond);
			return new JsonResult(RETURN_CODE_200, pageInfo);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}
	
	/**
	 * 次請求データ
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult update(@RequestBody Apprdoc apprDoc) {
		try {
			apprDocService.updateApprDocMonReqInfo(apprDoc);
			return new JsonResult(RETURN_CODE_200, "月次請求データが更新されました。");
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}
	
}
