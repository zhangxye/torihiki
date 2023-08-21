package com.tsrs.webedi.common.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tsrs.webedi.common.constant.Const;
import com.tsrs.webedi.common.persistence.dao.MenuMapper;
import com.tsrs.webedi.common.persistence.dao.MenuprogramMapper;
import com.tsrs.webedi.common.persistence.dao.ProgramMapper;
import com.tsrs.webedi.common.persistence.dao.RoleMapper;
import com.tsrs.webedi.common.service.IRoleService;
import com.tsrs.webedi.common.persistence.model.Menu;
import com.tsrs.webedi.common.persistence.model.MenuTree;
import com.tsrs.webedi.common.persistence.model.Menuprogram;
import com.tsrs.webedi.common.persistence.model.Program;
import com.tsrs.webedi.common.persistence.model.Role;
import com.tsrs.webedi.common.persistence.model.Rolemenurel;
import com.tsrs.webedi.common.persistence.model.RouteConfig;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

	@Resource
	RoleMapper roleMapper;

	@Resource
	ProgramMapper programMapper;

	@Resource
	MenuMapper menuMapper;

	@Resource
	MenuprogramMapper menuProgramMapper;

	/**
	 * ロールIDにより、アクセスできるメニューデータを取得する データのフォーマットはVue Routerに合わせる
	 * 
	 * @param roleId ロールID
	 * @return
	 * @throws IOException
	 */
	public List<RouteConfig> getAsyncRouterMapList(Integer roleId) throws IOException {

		return roleMapper.getAsyncRouterMapList(roleId);
	}

	/**
	 * 利用可能な全てプログラムリストを取得する
	 *
	 * @return
	 * @throws IOException
	 */
	public List<Map<String, Object>> getAllProgramList() {

		QueryWrapper<Program> ew = new QueryWrapper<Program>();

		ew.select("pg_id", "pg_nm", "CASE menu_display_flg WHEN 'N' THEN TRUE ELSE FALSE END AS display_flg")
				.orderByAsc("pg_seq");
		return programMapper.selectMaps(ew);
	}

	/**
	 * 登録
	 * 
	 * @param roleInfo
	 * @return
	 */
	public Integer insert(Map<String, Object> roleInfo) {

		String pgId = Const.PG.ROLE + Const.PAGE.ENT;

		// ロール情報を登録
		Role role = new Role();
		role.setName((String) roleInfo.get("role_nm"));
		role.insert();

		Integer roleId = role.getId();
		List<Map<String, Object>> menuList = (List<Map<String, Object>>) roleInfo.get("menu_list");
		this.insertMenuInfo(menuList, roleId, pgId);

		return roleId;
	}

	/**
	 * 更新
	 * 
	 * @param roleInfo
	 * @return
	 */
	public Integer update(Map<String, Object> roleInfo) {
		String pgId = Const.PG.ROLE + Const.PAGE.UPD;

		// ロール情報を登録
		Role role = new Role();
		role.setId((Integer) roleInfo.get("role_id"));
		role.setName((String) roleInfo.get("role_nm"));
		roleMapper.updRoleData(role);

		Integer roleId = role.getId();
		List<Map<String, Object>> menuList = (List<Map<String, Object>>) roleInfo.get("menu_list");
		// メニューに関連する情報については、全て削除してから、登録し直すようにする
		this.deleteMenuInfo(roleId);
		this.insertMenuInfo(menuList, roleId, pgId);

		return roleId;
	}

	/**
	 * 削除
	 * 
	 * @param roleInfo
	 * @return
	 */
	public Integer delete(Integer roleId) {

		// メニューに関連する情報を全て削除する
		this.deleteMenuInfo(roleId);

		// ロール情報を登録
		Role role = new Role();
		role.setId(roleId);
		role.deleteById();

		return roleId;
	}

	/**
	 * ロールデータのリストを取得する
	 *
	 * @return
	 * @throws IOException
	 */
	public List<Map<String, Object>> getRoleList(Integer roleId, String roleNm) {

		return roleMapper.getRoleList(roleId, roleNm);
	}

	/**
	 * ロール詳細データを取得する
	 *
	 * @return
	 * @throws IOException
	 */
	public Map<String, Object> getRoleDetail(Integer roleId) {

		Map<String, Object> res = new HashMap<String, Object>();

		Role roleEnt = roleMapper.selectById(roleId);
		List<MenuTree> menuList = roleMapper.getMenuTreeList(roleId);

		res.put("role_id", roleEnt.getId());
		res.put("role_nm", roleEnt.getName());
		res.put("menu_list", menuList);

		return res;
	}

	/**
	 * ロール詳細データを取得する
	 *
	 * @return
	 * @throws IOException
	 */
	public Map<String, Object> getRoleDetail2(Integer roleId) {
		Map<String, Object> res = roleMapper.getRoleInfo(roleId);
		List<Map<String, Object>> menuList = roleMapper.selectMenuList(roleId);
		for (Map<String, Object> menu : menuList) {
			List<String> pgList = roleMapper.selectPgList2((Integer) menu.get("menu_id"));
			menu.put("program_list", pgList);
		}
		res.put("menu_list", menuList);

		return res;
	}

	/**
	 * メニューの関連情報を登録する
	 * 
	 * @param menuList
	 * @param roleId
	 * @param pgId
	 */
	private void insertMenuInfo(List<Map<String, Object>> menuList, Integer roleId, String pgId) {
		Integer menuDispOrder = 0;
		// ロールに属するメニュー情報を登録
		for (Map<String, Object> menuEnt : menuList) {
			Menu menu = new Menu();
			Rolemenurel roleMenuRel = new Rolemenurel();
			menu.setMenuNm((String) menuEnt.get("menu_nm"));
			menu.setMenuPath((String) menuEnt.get("menu_path"));
			menu.setMenuComponent(Const.MENU_COMPONENT);
			menu.setMenuIcon((String) menuEnt.get("menu_icon"));
			menu.setSysInsPgId(pgId);
			menu.insert();

			roleMenuRel.setRoleId(roleId);
			roleMenuRel.setMenuId(menu.getMenuId());
			roleMenuRel.setDisplayOrder(++menuDispOrder);
			roleMenuRel.setSysInsPgId(pgId);
			roleMenuRel.insert();

			// メニューに属するプログラム情報を登録
			Integer pgDispOrder = 0;
			List<String> programList = (List<String>) menuEnt.get("program_list");
			for (String programId : programList) {
				Menuprogram menuPg = new Menuprogram();
				menuPg.setMenuId(menu.getMenuId());
				menuPg.setPgId(programId);
				menuPg.setDisplayOrder(++pgDispOrder);
				menuPg.setSysInsPgId(pgId);
				menuPg.insert();
			}
		}
	}

	private void deleteMenuInfo(Integer roleId) {
		Rolemenurel roleMenuRel = new Rolemenurel();
		Menu menu = new Menu();
		Menuprogram menuProgram = new Menuprogram();
		QueryWrapper<Rolemenurel> rmQw = new QueryWrapper<Rolemenurel>();
		rmQw.eq("role_id", roleId);
		List<Rolemenurel> menuList = roleMenuRel.selectList(rmQw);
		for (Rolemenurel menuEnt : menuList) {
			Integer menuId = menuEnt.getMenuId();

			// メニューに属するプログラム情報を削除
//			QueryWrapper<Menuprogram> mpQw = new QueryWrapper<Menuprogram>();
//			rmQw.eq("menu_id", menuId);
			menuProgram.delete(Wrappers.<Menuprogram>query().lambda().eq(Menuprogram::getMenuId, menuId));
			// メニュー情報を削除
//			QueryWrapper<Menu> mQw = new QueryWrapper<Menu>();
//			rmQw.eq("menu_id", menuId);
			menu.delete(Wrappers.<Menu>query().lambda().eq(Menu::getMenuId, menuId));
		}
		roleMenuRel.delete(Wrappers.<Rolemenurel>query().lambda().eq(Rolemenurel::getRoleId, roleId));

	}

}
