package com.tsrs.webedi.modular.system.warpper;

import com.tsrs.webedi.common.constant.factory.ConstantFactory;
import com.tsrs.webedi.common.warpper.BaseControllerWarpper;

import java.util.Map;

/**
 * 部門列表的包装
 *
 * @author Tsrs-WebEDI
 * @date 2017年4月25日 18:10:31
 */
public class NoticeWrapper extends BaseControllerWarpper {

    public NoticeWrapper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        Integer creater = (Integer) map.get("creater");
        map.put("createrName", ConstantFactory.me().getUserNameById(creater));
    }

}
