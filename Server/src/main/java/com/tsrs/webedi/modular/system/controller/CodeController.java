package com.tsrs.webedi.modular.system.controller;

import com.tsrs.webedi.common.annotion.Permission;
import com.tsrs.webedi.common.constant.Const;
import com.tsrs.webedi.common.controller.BaseController;
import com.tsrs.webedi.common.exception.BizExceptionEnum;
import com.tsrs.webedi.common.exception.BussinessException;
import com.tsrs.webedi.core.template.config.ContextConfig;
import com.tsrs.webedi.core.template.engine.SimpleTemplateEngine;
import com.tsrs.webedi.core.template.engine.base.WebEDITemplateEngine;
import com.tsrs.webedi.core.util.ToolUtil;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 代码生成控制器
 *
 * @author Tsrs-WebEDI
 * @Date 2017-05-23 18:52:34
 */
@Controller
@RequestMapping("/code")
public class CodeController extends BaseController {

    private String PREFIX = "/system/code/";

    /**
     * 跳转到代码生成首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "code.html";
    }

    /**
     * 代码生成
     */
//    @ApiOperation("生成代码")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "moduleName", value = "模块名称", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "bizChName", value = "业务名称", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "bizEnName", value = "业务英文名称", required = true, dataType = "String"),
//            @ApiImplicitParam(name = "path", value = "项目生成类路径", required = true, dataType = "String")
//    })
    @RequestMapping(value = "/generate", method = RequestMethod.POST)
    @ResponseBody
    @Permission(Const.ADMIN_NAME)
    public Object add(String moduleName, String bizChName, String bizEnName, String path) {
        if (ToolUtil.isOneEmpty(bizChName, bizEnName)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        ContextConfig contextConfig = new ContextConfig();
        contextConfig.setBizChName(bizChName);
        contextConfig.setBizEnName(bizEnName);
        contextConfig.setModuleName(moduleName);
        if (ToolUtil.isNotEmpty(path)) {
            contextConfig.setProjectPath(path);
        }

        WebEDITemplateEngine webediTemplateEngine = new SimpleTemplateEngine();
        webediTemplateEngine.setContextConfig(contextConfig);
        webediTemplateEngine.start();

        return super.SUCCESS_TIP;
    }
}
