package com.tsrs.webedi.modular.system.warpper;

import com.tsrs.webedi.common.constant.factory.ConstantFactory;
import com.tsrs.webedi.common.warpper.BaseControllerWarpper;
import com.tsrs.webedi.core.util.ToolUtil;
import com.tsrs.webedi.common.persistence.model.Dict;

import java.util.List;
import java.util.Map;

/**
 * 字典列表的包装
 *
 * @author Tsrs-WebEDI
 * @date 2017年4月25日 18:10:31
 */
public class DictWarpper extends BaseControllerWarpper {

    public DictWarpper(Object list) {
        super(list);
    }

    @Override
    public void warpTheMap(Map<String, Object> map) {
        StringBuffer detail = new StringBuffer();
        Integer id = (Integer) map.get("id");
        List<Dict> dicts = ConstantFactory.me().findInDict(id);
        if(dicts != null){
            for (Dict dict : dicts) {
                detail.append(dict.getNum() + ":" +dict.getName() + ",");
            }
            map.put("detail", ToolUtil.removeSuffix(detail.toString(),","));
        }
    }

}
