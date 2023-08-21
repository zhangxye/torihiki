package com.tsrs.webedi.common.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.tsrs.webedi.common.JsonResult;
import com.tsrs.webedi.common.annotion.Permission;
import com.tsrs.webedi.common.annotion.log.BussinessLog;
import com.tsrs.webedi.common.constant.Const;
import com.tsrs.webedi.common.constant.Dict;
import com.tsrs.webedi.common.constant.cache.Cache;
import com.tsrs.webedi.common.constant.factory.ConstantFactory;
import com.tsrs.webedi.common.constant.tips.Tip;
import com.tsrs.webedi.common.exception.BizExceptionEnum;
import com.tsrs.webedi.common.exception.BussinessException;
import com.tsrs.webedi.common.node.ZTreeNode;
import com.tsrs.webedi.common.page.PageInfo;
import com.tsrs.webedi.common.persistence.dao.RoleMapper;
import com.tsrs.webedi.common.persistence.dao.UserMapper;
import com.tsrs.webedi.common.persistence.model.LabelValueBean;
import com.tsrs.webedi.common.persistence.model.Role;
import com.tsrs.webedi.common.service.IRoleService;
import com.tsrs.webedi.core.cache.CacheKit;
import com.tsrs.webedi.core.log.LogObjectHolder;
import com.tsrs.webedi.core.util.ToolUtil;
import com.tsrs.webedi.modular.system.dao.RoleDao;

/**
 * 役割控制器
 *
 * @author Tsrs-WebEDI
 * @Date 2017年2月12日21:59:14
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    private static String PREFIX = "/system/role";

    @Resource
    UserMapper userMapper;

    @Resource
    RoleMapper roleMapper;

//    @Resource
    RoleDao roleDao;

    @Resource
    IRoleService roleService;
    
    /**
     *  リスト
     * @return
     */
     @RequestMapping(value = "/roleList", method = RequestMethod.GET)
     public JsonResult roleList() {
    	 List<LabelValueBean> obj = roleMapper.selectAllRoles();
 	    return  new  JsonResult(RETURN_CODE_200, obj);
     }   
    
    
    /**
     * 利用可能な全てのプログラムリストを取得する
     * 
     * @return
     */
    @RequestMapping(value="/getAllProgramList", method = RequestMethod.GET)
    public JsonResult getAllProgramList() {
    	List<Map<String,Object>> res = roleService.getAllProgramList();
    	return new JsonResult(RETURN_CODE_200, res);
    }
    
	@RequestMapping(value = "select", method = RequestMethod.GET)
	public JsonResult select(@RequestParam(value = "page", required = true) String page,
			@RequestParam(value = "page_size", required = true) String pageSize,
			@RequestParam(value = "role_id", required = false) Integer roleId,
			@RequestParam(value = "role_nm", required = false) String roleNm) {
		try {
			List<Map<String, Object>> res = roleService.getRoleList(roleId, roleNm);
			
			PageInfo<Map<String, Object>> pageInfo = page(Integer.valueOf(page), res, Integer.valueOf(pageSize),
					res.size());
			
			return new JsonResult(RETURN_CODE_200, pageInfo);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
		
    }
    
    @RequestMapping(value="detail", method = RequestMethod.GET)
    public JsonResult select(@RequestParam("role_id") Integer roleId) {
		try {
	    	Map<String,Object> res = roleService.getRoleDetail(roleId);
	    	return new JsonResult(RETURN_CODE_200, res);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
		
    }
    
    @RequestMapping(value="detail2", method = RequestMethod.GET)
    public JsonResult select2(@RequestParam("role_id") Integer roleId) {
    	try {
    		Map<String,Object> res = roleService.getRoleDetail2(roleId);
    		return new JsonResult(RETURN_CODE_200, res);
    	} catch (Exception e) {
    		return new JsonResult(RETURN_CODE_500, e.getMessage());
    	}
    	
    }
    
    @RequestMapping(value="insert", method = RequestMethod.POST)
    public JsonResult insert(@RequestBody Map<String,Object> roleInfo) {
		try {
	    	Integer roleId = roleService.insert(roleInfo);
			return new JsonResult(RETURN_CODE_200, "ロールデータが登録されました。", roleId);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}

    }
    
    @RequestMapping(value="update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody Map<String,Object> roleInfo) {
    	try {
    		Integer roleId = roleService.update(roleInfo);
    		return new JsonResult(RETURN_CODE_200, "ロールデータが更新されました。",roleId);
    	} catch (Exception e) {
    		return new JsonResult(RETURN_CODE_500, e.getMessage());
    	} 	
    }
    
    @RequestMapping(value="delete", method = RequestMethod.DELETE)
    public JsonResult delete(@RequestParam("role_id") Integer roleId) {
    	try {
    		roleService.delete(roleId);
    		return new JsonResult(RETURN_CODE_200, "ロールデータが削除されました。");
    	} catch (Exception e) {
    		return new JsonResult(RETURN_CODE_500, e.getMessage());
    	} 	
    }
    

    
     /**既存**/
     
     
    /**
     * 跳转到役割列表页面
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "/role.html";
    }

    /**
     * 跳转到新規役割
     */
    @RequestMapping(value = "/role_add")
    public String roleAdd() {
        return PREFIX + "/role_add.html";
    }

    /**
     * 跳转到修正役割
     */
    @Permission
    @RequestMapping(value = "/role_edit/{roleId}")
    public String roleEdit(@PathVariable Integer roleId, Model model) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        Role role = this.roleMapper.selectById(roleId);
        model.addAttribute(role);
        model.addAttribute("pName", ConstantFactory.me().getSingleRoleName(role.getPid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(role.getDeptid()));
        LogObjectHolder.me().set(role);
        return PREFIX + "/role_edit.html";
    }

    /**
     * 跳转到役割分配
     */
    @Permission
    @RequestMapping(value = "/role_assign/{roleId}")
    public String roleAssign(@PathVariable("roleId") Integer roleId, Model model) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        model.addAttribute("roleId", roleId);
        model.addAttribute("roleName", ConstantFactory.me().getSingleRoleName(roleId));
        return PREFIX + "/role_assign.html";
    }

//    /**
//     * 获取役割列表
//     */
//    @Permission
//    @RequestMapping(value = "/list")
//    @ResponseBody
//    public Object list(@RequestParam(required = false) String roleName) {
//        List<Map<String, Object>> roles = this.roleDao.selectRoles(super.getPara("roleName"));
//        return super.warpObject(new RoleWarpper(roles));
//    }

    /**
     * 役割新增
     */
    @RequestMapping(value = "/add")
    @BussinessLog(value = "新規役割", key = "name", dict = Dict.RoleDict)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip add(@Valid Role role, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        role.setId(null);
        this.roleMapper.insert(role);
        return SUCCESS_TIP;
    }

    /**
     * 役割修正
     */
    @RequestMapping(value = "/edit")
    @BussinessLog(value = "修正役割", key = "name", dict = Dict.RoleDict)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip edit(@Valid Role role, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        this.roleMapper.updateById(role);

        //削除缓存
        CacheKit.removeAll(Cache.CONSTANT);
        return SUCCESS_TIP;
    }

    /**
     * 削除役割
     */
    @RequestMapping(value = "/remove")
    @BussinessLog(value = "削除役割", key = "roleId", dict = Dict.DeleteDict)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip remove(@RequestParam Integer roleId) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }

        //不能削除超级管理员役割
        if(roleId.equals(Const.ADMIN_ROLE_ID)){
            throw new BussinessException(BizExceptionEnum.CANT_DELETE_ADMIN);
        }

        //缓存被削除的役割名称
        LogObjectHolder.me().set(ConstantFactory.me().getSingleRoleName(roleId));

//      this.roleService.delRoleById(roleId);

        //削除缓存
        CacheKit.removeAll(Cache.CONSTANT);
        return SUCCESS_TIP;
    }

    /**
     * 查看役割
     */
    @RequestMapping(value = "/view/{roleId}")
    @ResponseBody
    public Tip view(@PathVariable Integer roleId) {
        if (ToolUtil.isEmpty(roleId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        this.roleMapper.selectById(roleId);
        return SUCCESS_TIP;
    }

    /**
     * 配置权限
     */
    @RequestMapping("/setAuthority")
    @BussinessLog(value = "配置权限", key = "roleId,ids", dict = Dict.RoleDict)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip setAuthority(@RequestParam("roleId") Integer roleId, @RequestParam("ids") String ids) {
        if (ToolUtil.isOneEmpty(roleId)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
//        this.roleService.setAuthority(roleId, ids);
        return SUCCESS_TIP;
    }

    /**
     * 获取役割列表
     */
    @RequestMapping(value = "/roleTreeList")
    @ResponseBody
    public List<ZTreeNode> roleTreeList() {
        List<ZTreeNode> roleTreeList = this.roleDao.roleTreeList();
        roleTreeList.add(ZTreeNode.createParent());
        return roleTreeList;
    }

   /* *//**
     * 获取役割列表
     *//*
    @RequestMapping(value = "/roleTreeListByUserId/{userId}")
    @ResponseBody
    public List<ZTreeNode> roleTreeListByUserId(@PathVariable Integer userId) {
        User theUser = this.userMapper.selectById(userId);
        String roleid = theUser.getRoleid();
        if (ToolUtil.isEmpty(roleid)) {
            List<ZTreeNode> roleTreeList = this.roleDao.roleTreeList();
            return roleTreeList;
        } else {
            String[] strArray = Convert.toStrArray(",", roleid);
            List<ZTreeNode> roleTreeListByUserId = this.roleDao.roleTreeListByRoleId(strArray);
            return roleTreeListByUserId;
        }
    }*/

}
