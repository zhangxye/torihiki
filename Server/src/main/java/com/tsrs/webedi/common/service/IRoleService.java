package com.tsrs.webedi.common.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.tsrs.webedi.common.persistence.model.RouteConfig;

public interface IRoleService {



	/**
	 * ロールIDにより、アクセスできるメニューデータを取得する
	 * データのフォーマットはVue　Routerに合わせる
	 * 
	 * @param roleId ロールID
	 * @return
	 * @throws IOException
	 */
	public List<RouteConfig> getAsyncRouterMapList(Integer roleId) throws IOException;
	
	/**
	 *利用可能な全てプログラムリストを取得する
	 *
	 * @return
	 * @throws IOException
	 */
	public List<Map<String,Object>> getAllProgramList();
	
	/**
	 * 登録
	 * @param roleInfo
	 * @return
	 */
	public Integer insert(Map<String,Object> roleInfo);
	
	/**
	 * 更新
	 * @param roleInfo
	 * @return
	 */
	public Integer update(Map<String,Object> roleInfo);
	
	
	/**
	 * 削除
	 * @param roleInfo
	 * @return
	 */
	public Integer delete(Integer roleId);
		
	/**
	 *ロールデータのリストを取得する
	 *
	 * @return
	 * @throws IOException
	 */
	public List<Map<String,Object>> getRoleList(Integer roleId, String roleNm);
	
	/**
	 *ロール詳細データを取得する
	 *
	 * @return
	 * @throws IOException
	 */
	public Map<String,Object> getRoleDetail(Integer roleId);
	
	/**
	 *ロール詳細データ(更新と類似登録画面)を取得する
	 *
	 * @return
	 * @throws IOException
	 */
	public Map<String,Object> getRoleDetail2(Integer roleId);
	
}
