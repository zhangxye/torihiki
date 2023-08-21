package com.tsrs.webedi.modular.system.controller;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tsrs.webedi.common.JsonResult;
import com.tsrs.webedi.common.controller.BaseController;
import com.tsrs.webedi.modular.system.persistence.model.Order;
import com.tsrs.webedi.modular.system.service.IOrderService;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

/**
 * <p>
 * 受注HDR情報 前端控制器
 * </p>
 *
 * @author jin-qk
 * @since 2018-03-26
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

	@Resource
	private IOrderService orderService;

	//登録検索一覧
	@RequestMapping(value = "/searchForEntry", method = RequestMethod.POST)
	public JsonResult searchForEntry(@RequestBody Map<String, Object> searchCond) {

		Page<Map<String, Object>> pageInfo = new Page<Map<String, Object>>((Integer) searchCond.get("current"),
				(Integer) searchCond.get("size"));
		try {
			pageInfo = orderService.searchForEntry(pageInfo, searchCond);
			return new JsonResult(RETURN_CODE_200, pageInfo);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}
	
	//検索一覧
	@RequestMapping(value = "/searchForDetail", method = RequestMethod.POST)
	public JsonResult searchForDetail(@RequestBody Map<String, Object> searchCond) {
		
		Page<Map<String, Object>> pageInfo = new Page<Map<String, Object>>((Integer) searchCond.get("current"),
				(Integer) searchCond.get("size"));
		try {
			pageInfo = orderService.searchForDetail(pageInfo, searchCond);
			return new JsonResult(RETURN_CODE_200, pageInfo);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}
	
	//登録データ初期値取得
	@RequestMapping(value = "/selectForInsert", method = RequestMethod.GET)
	public JsonResult selectForInsert(@RequestParam(value="est_hid",required=true) Integer estHid) {
		
		try {
			Map<String,Object> ret = orderService.selectForInsert(estHid);
			return new JsonResult(RETURN_CODE_200, ret);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}
	
	//詳細データ取得
	@RequestMapping(value = "/selectForDetail", method = RequestMethod.GET)
	public JsonResult selectForDetail(@RequestParam(value="order_id",required=true) Integer orderId) {
		
		try {
			Map<String,Object> ret = orderService.selectForDetail(orderId);
			return new JsonResult(RETURN_CODE_200, ret);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public JsonResult insert(@RequestBody Map<String, Object> orderInfo) {
		try {
			Integer orderId = orderService.insertOrderInfo(orderInfo);
			return new JsonResult(RETURN_CODE_200, orderId);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult update(@RequestBody Order order) {
		try {
			Integer order_id = orderService.updateOrderInfo(order);
			return new JsonResult(RETURN_CODE_200, order_id);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public JsonResult delete(@RequestParam(value="order_id",required=true) Integer orderId) {
		try {
			orderService.deleteOrderInfo(orderId);
			return new JsonResult(RETURN_CODE_200);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	
	/**
	 * 受注を確定
	 * @param confirmList
	 * @return
	 */
	@RequestMapping(value = "/confirmOrder", method = RequestMethod.POST)
	public JsonResult confirmOrder(@RequestBody  List<Map<String, Object>> confirmList) {

		try {
			orderService.confirmOrder(confirmList);
			return new JsonResult(RETURN_CODE_200);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}
	
	/**
	 * 確定済の受注を取消
	 * @param confirmList
	 * @return
	 */
	@RequestMapping(value = "/cancelConfirmOrder", method = RequestMethod.POST)
	public JsonResult cancelConfirmOrder(@RequestBody List<Map<String, Object>> confirmList) {
		
		try {
			orderService.cancelConfirmOrder(confirmList);
			return new JsonResult(RETURN_CODE_200);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}
}
