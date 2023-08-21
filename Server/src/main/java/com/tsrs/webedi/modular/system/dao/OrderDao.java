package com.tsrs.webedi.modular.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tsrs.webedi.modular.system.persistence.model.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 受注HDR情報 Mapper 接口
 * </p>
 *
 * @author jin-qk
 * @since 2018-03-26
 */
public interface OrderDao extends BaseMapper<Order> {

    List<Map<String, Object>> searchForDetail(Page page, @Param("searchCond") Map<String, Object> searchCond);

    Map<String, Object> selectOrderInfoForDetail(@Param("order_id") Integer orderId);

    int updateApprDocSt(@Param("map") Map<String, Object> map);

}