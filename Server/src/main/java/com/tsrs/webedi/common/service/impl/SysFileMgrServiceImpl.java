package com.tsrs.webedi.common.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.tsrs.webedi.common.constant.Const;
import com.tsrs.webedi.common.persistence.dao.SysfilemgrMapper;
import com.tsrs.webedi.common.persistence.model.Sysfilemgr;
import com.tsrs.webedi.common.service.ISysFileMgrService;
import com.tsrs.webedi.core.shiro.ShiroKit;
@Service
@Transactional
public class SysFileMgrServiceImpl implements ISysFileMgrService {
	
	@Resource
	SysfilemgrMapper sysfilemgrMapper;
	
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
	public Integer uploadFile(InputStream input, String fileFullName) throws IOException{
		
		//格納フォルダを作成
        File destFolder = new File(Const.UL_FILE_FOLD);
        FileUtils.forceMkdir(destFolder);
        
        //ファイルを管理情報を保存
        String fileName = FilenameUtils.getName(fileFullName);
        String fileExt = FilenameUtils.getExtension(fileFullName);

        Sysfilemgr fileMgr = new Sysfilemgr();
        fileMgr.setFileNm(fileName);
        fileMgr.setFileExt(fileExt);
        fileMgr.setFileUserId(ShiroKit.getUser().getAccount());
        fileMgr.setFileYmd(new Timestamp(System.currentTimeMillis()));
        fileMgr.setUseFlg(Const.USE_FLG.INVALID);
        sysfilemgrMapper.insert(fileMgr);
  
        //アップロードファイルを格納
        String realFileNm = String.valueOf(fileMgr.getFileId()) + FilenameUtils.EXTENSION_SEPARATOR + fileExt;
        File destFile = new File(destFolder, realFileNm);
        FileUtils.copyToFile(input, destFile);

        fileMgr.setFilePath(destFile.getAbsolutePath());
        fileMgr.setFileSize(Integer.valueOf((int)FileUtils.sizeOf(destFile)));
        
        UpdateWrapper<Sysfilemgr> ew = new UpdateWrapper<Sysfilemgr>();
//        ew.setEntity(new Sysfilemgr());
        ew.eq("file_id", fileMgr.getFileId());
        sysfilemgrMapper.update(fileMgr,ew);
        return fileMgr.getFileId();
	}
	
	/**
	 * アップロードしたファイルを有効にする
	 * 
	 * @param fileId
	 * @param pgId
	 */
	public void updateFileToValid(Integer fileId, String pgId) {
		if (fileId == null) {
			return;
		}
		// アップロードファイルを有効にする
		Sysfilemgr fileMgr = new Sysfilemgr();
		fileMgr.setFileId(fileId);
		fileMgr.setUseFlg(Const.USE_FLG.VALID);
		fileMgr.setSysInsPgId(pgId);
		fileMgr.setSysUpdPgId(pgId);
		fileMgr.setSysUpdUserId(ShiroKit.getUser().getAccount());
		sysfilemgrMapper.updateById(fileMgr);
	}
	
	@Override
	public List<Map<String, Object>> selectFileList(Integer fileId) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		Sysfilemgr fileMgr = new Sysfilemgr();
		fileMgr.setFileId(fileId);
		fileMgr.setUseFlg(Const.USE_FLG.VALID);
		QueryWrapper<Sysfilemgr> wrapper = new QueryWrapper<Sysfilemgr>(fileMgr);
		List<Sysfilemgr> listSysFile = sysfilemgrMapper.selectList(wrapper);
		String webContext = "/api/file/download?file_id=%d";
		if (null != listSysFile) {
			Map<String, Object> map;
			for (int i = 0; i < listSysFile.size(); i++) {
				map = new HashMap<String, Object>();
				map.put("name", listSysFile.get(i).getFileNm());
				map.put("url", String.format(webContext, listSysFile.get(i).getFileId()));
				listMap.add(map);
			}
		}
		return listMap;
	}
	
	public void downloadFile(Integer fileId, HttpServletResponse res) {

		// ファイル情報を取得
		Sysfilemgr fileMgr = sysfilemgrMapper.selectById(fileId);

		String dlFileNm = fileMgr.getFileNm() + FilenameUtils.EXTENSION_SEPARATOR + fileMgr.getFileExt();
		String realFileNm = String.valueOf(fileMgr.getFileId()) + FilenameUtils.EXTENSION_SEPARATOR
				+ fileMgr.getFileExt();
		File fileFolder = new File(Const.UL_FILE_FOLD);
		File fullFilePath = new File(fileFolder, realFileNm);

		res.setHeader("content-type", "application/octet-stream");
		res.setContentType("application/octet-stream");
		res.setHeader("Content-Disposition", "attachment;filename=" + dlFileNm);

		OutputStream out = null;

		try(BufferedInputStream inp = new BufferedInputStream(new FileInputStream(fullFilePath));) {
			out = res.getOutputStream();
			IOUtils.copy(inp, out);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	

}
