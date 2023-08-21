package com.tsrs.webedi.modular.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.tsrs.webedi.common.JsonResult;
import com.tsrs.webedi.common.annotion.log.BussinessLog;
import com.tsrs.webedi.common.constant.Const;
import com.tsrs.webedi.common.constant.Dict;
import com.tsrs.webedi.common.controller.BaseController;
import com.tsrs.webedi.common.exception.BizExceptionEnum;
import com.tsrs.webedi.common.exception.BussinessException;
import com.tsrs.webedi.common.page.PageInfo;
import com.tsrs.webedi.common.persistence.model.LabelValueBean;
import com.tsrs.webedi.common.persistence.model.User;
import com.tsrs.webedi.common.persistence.model.UserExt;
import com.tsrs.webedi.common.persistence.model.Userwfconfirmroute;
import com.tsrs.webedi.core.shiro.ShiroKit;
import com.tsrs.webedi.modular.system.service.IUserService;

/**
 * 系统管理员控制器
 *
 * @author Tsrs-WebEDI
 * @Date 2017年1月11日 下午1:08:17
 */
@RestController
@RequestMapping("/user")
public class UserMgrController extends BaseController {

	@Resource
	private IUserService userService;

	/**
	 * 修正当前用户的パスワード
	 */
	@RequestMapping(value = "/changePwd", method = RequestMethod.GET)
	public JsonResult changePwd(@RequestParam String oldPwd, @RequestParam String newPwd, @RequestParam String rePwd) {
		try {
			if (!newPwd.equals(rePwd)) {
				throw new BussinessException(BizExceptionEnum.TWO_PWD_NOT_MATCH);
			}
			String account = ShiroKit.getUser().getAccount();
			User user = userService.getUserInfo(account);
			String oldMd5 = ShiroKit.md5(oldPwd, user.getSalt());
			if (user.getPassword().equals(oldMd5)) {
				String newMd5 = ShiroKit.md5(newPwd, user.getSalt());
				user.setPassword(newMd5);
				userService.updateById(user);
				return new JsonResult(RETURN_CODE_200, "パスワードを修正完了しました。");
			} else {
				throw new BussinessException(BizExceptionEnum.OLD_PWD_NOT_RIGHT);
			}
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}

	}

	/**
	 * パスワードをレセット
	 */
	@RequestMapping(value = "/resetPwd", method = RequestMethod.POST)
	@BussinessLog(value = "リセットパスワード", key = "account", dict = Dict.UserDict)
	public JsonResult resetPwd(@RequestBody User user) {
		try {

			// アカウントが存在
			User theUser = userService.getUserInfo(user.getAccount());
			if (theUser == null) {
				//ユーザー存在しません
				throw new BussinessException(BizExceptionEnum.USER_NOT_EXISTED);
			}

			theUser.setSalt(ShiroKit.getRandomSalt(5));
			theUser.setPassword(ShiroKit.md5(Const.DEFAULT_PWD, theUser.getSalt()));
			userService.updateById(theUser);
			return new JsonResult(RETURN_CODE_200, "パスワードをレセット完了しました。");

		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}

	}

	/**
	 * 新規ユーザー
	 */
	@RequestMapping(value = "/regiter", method = RequestMethod.POST)
	public JsonResult regiter(@RequestBody UserExt user) {
		try {
			validateWf(user.getUserwfconfirmroute());

			// 判断アカウント是否重复
			User theUser = userService.getUserInfo(user.getAccount());
			if (theUser != null) {
				throw new BussinessException(BizExceptionEnum.USER_ALREADY_REG);
			}

			// 完善アカウント信息
			user.setSalt(ShiroKit.getRandomSalt(5));
			user.setPassword(ShiroKit.md5(user.getPassword(), user.getSalt()));
			user.setCreatetime(new Date());

			userService.addUser(user);

			return new JsonResult(RETURN_CODE_200, "ユーザーを登録する完了しました。");
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	/**
	 * ユーザ更新
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public JsonResult edit(@RequestBody UserExt user) {
		try {
			validateWf(user.getUserwfconfirmroute());

			userService.updateUser(user);

			return new JsonResult(RETURN_CODE_200, "ユーザーを更新する完了しました。");
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	/**
	 * ユーザー
	 *
	 * @return
	 */
	@RequestMapping(value = "/userList/{account}", method = RequestMethod.GET)
	public JsonResult userList(@PathVariable("account") String account) {
		List<Map<String, Object>> obj = userService.selectActiveUserList(account);
		return new JsonResult(RETURN_CODE_200, obj);
	}

	/**
	 * ユーザー
	 *
	 * @return
	 */
	@RequestMapping(value = "/userList/", method = RequestMethod.GET)
	public JsonResult userList() {
		List<Map<String, Object>> obj = userService.selectActiveUserList("");
		return new JsonResult(RETURN_CODE_200, obj);
	}


	/**
	 * ユーザー
	 *
	 * @return
	 */
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public JsonResult select(@RequestParam String account) {
		try {
			UserExt theUser = userService.getUserInfo(account);
			return new JsonResult(RETURN_CODE_200, theUser);
		} catch (Exception e) {
			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	// 查询全部用户(分页)
	@RequestMapping(value = "/selectall", method = RequestMethod.GET)
	public JsonResult selectAllUser(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "jobTitleCd", required = false) String jobTitleCd) {
		try {
			if (StringUtils.isEmpty(page)) {
				page = "1";
			}
			List<UserExt> listUserExt = userService.search(username, jobTitleCd);

			PageInfo<UserExt> pageInfo = page(Integer.valueOf(page), listUserExt);

			return new JsonResult(RETURN_CODE_200, pageInfo);

		} catch (Exception e) {

			return new JsonResult(RETURN_CODE_500, e.getMessage());
		}
	}

	/**
    *  営業担当者
    * @return
    */
    @RequestMapping(value = "/jobUserList", method = RequestMethod.GET)
    public JsonResult jobUserList(@RequestParam(value = "jobTitleCd") String jobTitleCd) {
	    List<LabelValueBean> obj = userService.jobUserList(jobTitleCd);
	    return  new  JsonResult(super.RETURN_CODE_200, obj);
    }

	private void validateWf(List<Userwfconfirmroute> wfList) throws Exception{

		if (wfList != null && wfList.size() > 0) {
			List<Integer> ls = new ArrayList<Integer>();
			for (int i = 0; i < wfList.size(); i++) {
				if (wfList.get(i).getDisplay_order() == 0) {
					throw new Exception("表示順が0以上半角数字に入力してください。");
				}
				if(ls.contains(wfList.get(i).getDisplay_order())) {
					throw new Exception("表示順が間違い");
				} else {
					ls.add(wfList.get(i).getDisplay_order());
				}
			}

		}
	}
}
