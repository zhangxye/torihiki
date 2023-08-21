package com.tsrs.webedi.modular.system.jmodel;

import com.alibaba.fastjson.annotation.JSONField;

public class FileInfo {
	@JSONField (name="name") 
	private String fileNm;
	@JSONField (name="url") 
	private String filePath;
	public String getFileNm() {
		return fileNm;
	}
	public void setFileNm(String fileNm) {
		this.fileNm = fileNm;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	
}
