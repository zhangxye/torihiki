package com.tsrs.webedi.modular.system.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tsrs.webedi.common.persistence.dao.UserMapper;
import com.tsrs.webedi.common.persistence.dao.UserwfconfirmrouteMapper;
import com.tsrs.webedi.common.persistence.model.LabelValueBean;
import com.tsrs.webedi.common.persistence.model.User;
import com.tsrs.webedi.common.persistence.model.UserExt;
import com.tsrs.webedi.common.persistence.model.Userwfconfirmroute;
import com.tsrs.webedi.core.shiro.ShiroKit;
import com.tsrs.webedi.modular.system.dao.UserMgrDao;
import com.tsrs.webedi.modular.system.dao.UserWfConfirmRouteDao;
import com.tsrs.webedi.modular.system.service.IUserService;

/**
 * ユーザーサーブス
 *
 * @author Tsrs-WebEDI
 * @date 2017-05-05 22:20
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Resource
	UserMgrDao userMgrDao;

	@Resource
	UserMapper userMapper;

	@Resource
	UserWfConfirmRouteDao userWfConfirmRouteDao;

	@Resource
	UserwfconfirmrouteMapper userWfConfirmRouteMapper;

	@Override
	public void addUser(UserExt userEx) throws IllegalAccessException, InvocationTargetException {

		// 新規ユーザ
		User user = new User();
		BeanUtils.copyProperties(user, userEx);

		user.insert();
//		this.userMapper.insert( user);

		List<Userwfconfirmroute> usrWfList = userEx.getUserwfconfirmroute();

		if (usrWfList != null && usrWfList.size() > 0) {
			for (int i = 0; i < usrWfList.size(); i++) {
				Userwfconfirmroute userWf = usrWfList.get(i);
				userWf.setUser_id(user.getAccount());
				userWf.setSys_ins_pg_id("US001");
				userWf.setSys_ins_user_id(ShiroKit.getUser().getAccount());
				userWf.setSys_upd_user_id(ShiroKit.getUser().getAccount());
				userWf.setSys_upd_pg_id("US001");

			}
			this.userWfConfirmRouteDao.addUserWfBatch(usrWfList);
		}
	}

	@Override
	public List<UserExt> search(String username, String jobTitleCd) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", username);
		params.put("jobTitleCd", jobTitleCd);
		return userMgrDao.selectAllUsers(params);
		
	}
	
	@Override
	public UserExt getUserInfo(String account) {
		
		UserExt user = userMgrDao.getByAccount(account);
		
		List<Userwfconfirmroute> usrWfList = userWfConfirmRouteDao.getUserWf(account);
		if (user != null) {
			user.setUserwfconfirmroute(usrWfList);
		}
		
		return user;
		
	}

	@Override
	public void updateUser(UserExt user) {
		// 更新ユーザー情報
		User conUser = new User();
		conUser.setAccount(user.getAccount());
		UpdateWrapper<User> ew=new UpdateWrapper<User>(conUser);
		this.userMapper.update((User)user, ew);
		
		// 削除承認者一覧
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user_id", user.getAccount());
		userWfConfirmRouteMapper.deleteByMap(map);
		
		List<Userwfconfirmroute> usrWfList = user.getUserwfconfirmroute();

		if (usrWfList != null && usrWfList.size() > 0) {
			for (int i = 0; i < usrWfList.size(); i++) {
				Userwfconfirmroute userWf = usrWfList.get(i);
				userWf.setUser_id(user.getAccount());
				userWf.setSys_ins_pg_id("US001");
				userWf.setSys_ins_user_id(ShiroKit.getUser().getAccount());
				userWf.setSys_upd_user_id(ShiroKit.getUser().getAccount());
				userWf.setSys_upd_pg_id("US001");

			}
			this.userWfConfirmRouteDao.addUserWfBatch(usrWfList);
		}
		
	}

	@Override
	public List<Map<String, Object>> selectActiveUserList(String account) {
		return userMgrDao.selectActiveUserList(account);
	}

	@Override
	public Integer updateById(User user) {
		return userMapper.updateById(user);
	}

	@Override
	public List<LabelValueBean> jobUserList(String jobTitleCd) {
		return userMgrDao.jobUserList(jobTitleCd);
	}

}
