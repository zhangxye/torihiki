package com.tsrs.webedi.modular.system.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface IDownLoadDtService {

	/**
	 * 稟議書データ作成サービス
	 *
	 * @author Tsrs-WebEDI
	 * @Date 2017/10/20 15:09
	 */
	List<Map<String, Object>> selectApprDocDataList(String workMonth);

	List<Map<String, Object>> selectApprcommuteroutedtl(String apprDocNo);

	boolean export(List<Map<String, Object>> listValue, Map<String, Object> ret,String workMonth, boolean check1, boolean check2,
			HttpServletRequest request, HttpServletResponse response);
}
