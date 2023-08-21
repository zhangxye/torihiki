package com.tsrs.webedi.modular.system.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tsrs.webedi.common.JsonResult;
import com.tsrs.webedi.common.annotion.Permission;
import com.tsrs.webedi.common.annotion.log.BussinessLog;
import com.tsrs.webedi.common.constant.Dict;
import com.tsrs.webedi.common.constant.factory.ConstantFactory;
import com.tsrs.webedi.common.controller.BaseController;
import com.tsrs.webedi.common.exception.BizExceptionEnum;
import com.tsrs.webedi.common.exception.BussinessException;
import com.tsrs.webedi.common.node.ZTreeNode;
import com.tsrs.webedi.common.persistence.dao.DeptMapper;
import com.tsrs.webedi.common.persistence.model.Dept;
import com.tsrs.webedi.common.persistence.model.LabelValueBean;
import com.tsrs.webedi.core.log.LogObjectHolder;
import com.tsrs.webedi.core.util.ToolUtil;
import com.tsrs.webedi.modular.system.dao.DeptDao;
import com.tsrs.webedi.modular.system.warpper.DeptWarpper;

/**
 * 部門控制器
 *
 * @author Tsrs-WebEDI
 * @Date 2017年2月17日20:27:22
 */
@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController {


    @Resource
    DeptDao deptDao;

    @Resource
    DeptMapper deptMapper;

   /**
    *  部門リスト
    * @return
    */
    @RequestMapping(value = "/dcitList", method = RequestMethod.GET)
    public JsonResult dcitList() {
	    List<LabelValueBean> obj = deptDao.list2();
	    return  new  JsonResult(super.RETURN_CODE_200, obj);
    }
    
    /**既存**/
    
    /**
     * 获取部門的tree列表
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = this.deptDao.tree();
        tree.add(ZTreeNode.createParent());
        return tree;
    }

    /**
     * 新增部門
     */
    @BussinessLog(value = "新規部門", key = "simplename", dict = Dict.DeptDict)
    @RequestMapping(value = "/add")
    @Permission
    @ResponseBody
    public Object add(Dept dept) {
        if (ToolUtil.isOneEmpty(dept, dept.getSimplename())) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        return this.deptMapper.insert(dept);
    }

    /**
     * 获取所有部門列表
     */
    @RequestMapping(value = "/list")
    @Permission
    @ResponseBody
    public Object list(String condition) {
        List<Map<String, Object>> list = this.deptDao.list(condition);
        return super.warpObject(new DeptWarpper(list));
    }

    /**
     * 部門详情
     */
    @RequestMapping(value = "/detail/{deptId}")
    @Permission
    @ResponseBody
    public Object detail(@PathVariable("deptId") Integer deptId) {
        return deptMapper.selectById(deptId);
    }

    /**
     * 修正部門
     */
    @BussinessLog(value = "修正部門", key = "simplename", dict = Dict.DeptDict)
    @RequestMapping(value = "/update")
    @Permission
    @ResponseBody
    public Object update(Dept dept) {
        if (ToolUtil.isEmpty(dept) || dept.getId() == null) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        deptMapper.updateById(dept);
        return super.SUCCESS_TIP;
    }

    /**
     * 削除部門
     */
    @BussinessLog(value = "削除部門", key = "deptId", dict = Dict.DeleteDict)
    @RequestMapping(value = "/delete")
    @Permission
    @ResponseBody
    public Object delete(@RequestParam Integer deptId) {

        //缓存被削除的部門名称
        LogObjectHolder.me().set(ConstantFactory.me().getDeptName(deptId));

        deptMapper.deleteById(deptId);

        return SUCCESS_TIP;
    }
    

}
