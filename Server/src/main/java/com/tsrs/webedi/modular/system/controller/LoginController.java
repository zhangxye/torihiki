package com.tsrs.webedi.modular.system.controller;

import com.google.code.kaptcha.Constants;
import com.tsrs.webedi.common.JsonResult;
import com.tsrs.webedi.common.controller.BaseController;
import com.tsrs.webedi.common.exception.InvalidKaptchaException;
import com.tsrs.webedi.common.persistence.model.User;
import com.tsrs.webedi.core.jwt.JWTToken;
import com.tsrs.webedi.core.log.LogManager;
import com.tsrs.webedi.core.log.factory.LogTaskFactory;
import com.tsrs.webedi.core.shiro.ShiroKit;
import com.tsrs.webedi.core.shiro.ShiroUser;
import com.tsrs.webedi.core.util.JWTUtils;
import com.tsrs.webedi.core.util.ToolUtil;
import com.tsrs.webedi.core.util.UserUtil;
import com.tsrs.webedi.modular.system.service.IUserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

import static com.tsrs.webedi.core.support.HttpKit.getIp;


/**
 * 登录控制器
 *
 * @author Tsrs-WebEDI
 * @Date 2017年1月10日 下午8:25:24
 */
@RestController
public class LoginController extends BaseController {

	@Resource
	IUserService userService;

	/**
	 * 点击登录执行的动作
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonResult login(@RequestBody User user) {
		try {
			String username = user.getAccount();
			String password = user.getPassword();

			// 验证验证码是否正确
			if (ToolUtil.getKaptchaOnOff()) {
				String kaptcha = super.getPara("kaptcha").trim();
				String code = (String) super.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
				if (ToolUtil.isEmpty(kaptcha) || !kaptcha.equals(code)) {
					throw new InvalidKaptchaException();
				}
			}
			String jwt = JWTUtils.getToken(user);
			JWTToken token = new JWTToken(jwt);
//			Subject currentUser = ShiroKit.getSubject();
//			UsernamePasswordToken authToken = new UsernamePasswordToken(username, password.toCharArray());
//			token.setRememberMe(true);
//			LoginDataAutoToken token = new LoginDataAutoToken(username, password);
//			currentUser.login(token);
			UserUtil.login(user);
//			IShiro shiroFactory = ShiroFactroy.me();
////        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
//			user = shiroFactory.user(username);
//			ShiroUser shiroUser = shiroFactory.shiroUser(user);
			ShiroUser shiroUser = ShiroKit.getUser();
			Objects.requireNonNull(shiroUser).setToken(jwt);
			super.getSession().setAttribute("shiroUser", shiroUser);
			super.getSession().setAttribute("username", Objects.requireNonNull(shiroUser).getAccount());
			User loginUser = userService.getUserInfo(shiroUser.getAccount());
			User lastLoginUser = new User();
			lastLoginUser.setId(loginUser.getId());
			lastLoginUser.setLastLoginDt(new Date());
			userService.updateById(lastLoginUser);
			LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), getIp()));

            ShiroKit.getSession().setAttribute("sessionFlag", true);
//			shiroUser.setToken(jwt);
            return new JsonResult(RETURN_CODE_200, shiroUser.name + "はログインしました。", shiroUser);
        } catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, "ユーザーやパスワードは間違いです!!!");
		}
	}

	/**
	 * 退出登录
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public JsonResult logOut() {
//        LogManager.me().executeLog(LogTaskFactory.exitLog(Objects.requireNonNull(ShiroKit.getUser()).getId(), getIp()));
        ShiroKit.getSubject().logout();
		return new JsonResult(RETURN_CODE_200, "ログアウトしました!");
	}

	
}
