package com.tsrs.webedi.modular.system.service;


import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tsrs.webedi.modular.system.persistence.model.Order;

/**
 * <p>
 * 受注HDR情報 服务类
 * </p>
 *
 * @author jin-qk
 * @since 2018-03-26
 */
public interface IOrderService extends IService<Order> {

	public Page<Map<String,Object>> searchForEntry(Page<Map<String, Object>> page,Map<String, Object> searchCond);

	public Page<Map<String,Object>> searchForDetail(Page<Map<String, Object>> page,Map<String, Object> searchCond);
	
	public Map<String,Object> selectForInsert(Integer estHid);
	
	public Map<String,Object> selectForDetail(Integer orderId);
	
	public Integer insertOrderInfo(Map<String, Object> orderInfo) throws Exception;
	
	public Integer updateOrderInfo(Order order);
	
	public boolean deleteOrderInfo(Integer orderId);
	
	public void confirmOrder(List<Map<String, Object>> confirmList);
	
	public void cancelConfirmOrder(List<Map<String, Object>> confirmList);

}
