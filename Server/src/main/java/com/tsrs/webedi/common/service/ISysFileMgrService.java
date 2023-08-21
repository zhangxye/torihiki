package com.tsrs.webedi.common.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public interface ISysFileMgrService {


	/**
	 * アップロードファイルを登録する
	 * ・ファイル管理テーブルへ登録
	 * ・ファイルをリネームし、管理フォルダーに格納する
	 * 
	 * @param input アップロッドされているファイルの元
	 * @param fileFullName　アップロードファイルの名称
	 * @return
	 * @throws Exception
	 */
	public Integer uploadFile(InputStream input, String fileFullName) throws IOException;
	
	/**
	 * アップロードしたファイルを有効にする
	 * 
	 * @param fileId
	 * @param pgId
	 */
	public void updateFileToValid(Integer fileId, String pgId);
	
	List<Map<String, Object>> selectFileList(Integer fileId);

	public void downloadFile(Integer fileId, HttpServletResponse res);
	
}
