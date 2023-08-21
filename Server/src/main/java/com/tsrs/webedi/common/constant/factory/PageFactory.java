package com.tsrs.webedi.common.constant.factory;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tsrs.webedi.common.constant.state.Order;
import com.tsrs.webedi.core.support.HttpKit;
import com.tsrs.webedi.core.util.ToolUtil;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * BootStrap Table默认的分页参数创建
 *
 * @author Tsrs-WebEDI
 * @date 2017-04-05 22:25
 */
public class PageFactory<T> {

    public Page<T> defaultPage() {
        HttpServletRequest request = HttpKit.getRequest();
        int limit = Integer.valueOf(request.getParameter("limit"));
        int offset = Integer.valueOf(request.getParameter("offset"));
        String sort = request.getParameter("sort");
        String order = request.getParameter("order");
        Page<T> page = new Page<>((offset / limit + 1), limit);      
        if(ToolUtil.isEmpty(sort)){
            return page;
        }else{
            if(Order.ASC.getDes().equals(order)){
            	
          	  page.setOrders(Arrays.asList(OrderItem.asc(order)));
            }else{
          	  page.setOrders(Arrays.asList(OrderItem.desc(order)));
            }
            return page;
        }
    }
}
