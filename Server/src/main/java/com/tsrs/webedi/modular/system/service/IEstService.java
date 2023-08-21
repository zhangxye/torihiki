package com.tsrs.webedi.modular.system.service;

import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tsrs.webedi.modular.system.persistence.model.Esthdr;

/**
 * <p>
 * 見積HDR情報 服务类
 * </p>
 *
 * @author jin-qk
 * @since 2018-03-26
 */
public interface IEstService extends IService<Esthdr> {
	
	/**
	 * 未登録の見積一覧データを検索する
	 * 
	 * @param page
	 * @param searchCond
	 * @return
	 */
	public Page<Map<String,Object>> searchForEntry(Page<Map<String, Object>> page,Map<String, Object> searchCond);

	/**
	 * 登録済の見積一覧データを検索する
	 * 
	 * @param page
	 * @param searchCond
	 * @return
	 */
	public Page<Map<String,Object>> searchForDetail(Page<Map<String, Object>> page,Map<String, Object> searchCond);
	
	/**
	 * 未登録の見積データを照会する
	 * 
	 * @param apprDocIdList
	 * @return
	 */
	public Map<String,Object> selectForInsert(String apprDocIdList);
	
	/**
	 * 見積詳細データを照会する
	 * 
	 * @param estHid
	 * @return
	 */
	public Map<String,Object> selectForDetail(Integer estHid);
	
	/**
	 * 見積データを登録する
	 * 
	 * @param estInfo
	 * @return
	 * @throws Exception
	 */
	public Integer insertEstInfo(Map<String, Object> estInfo) throws Exception;
	
	/**
	 * 見積データを更新する
	 * 
	 * @param estHdr
	 * @return
	 */
	public Integer updateEstInfo(Esthdr estHdr);
	
	/**
	 * 見積データを削除する
	 * 
	 * @param estHid
	 * @return
	 */
	public boolean deleteEstInfo(Integer estHid);
	
	/**
	 * 見積書を発行する
	 * ※発行日を登録　及び　見積書（EXCEL）をダウンロードする
	 * 
	 * @param estHid 見積HID
	 * @param res
	 * @return
	 */
	public String issueEstDoc(Integer estHid, HttpServletResponse res);
	
	/**
	 * 見積書を送付する
	 * ※送付日を登録するだけ
	 * 
	 * @param estHid　見積HID
	 * @return
	 */
	public String deliveryEstDoc(Integer estHid);
	
	
}
