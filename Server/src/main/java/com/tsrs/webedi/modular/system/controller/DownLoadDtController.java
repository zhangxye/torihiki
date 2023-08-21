package com.tsrs.webedi.modular.system.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsrs.webedi.common.JsonResult;
import com.tsrs.webedi.common.controller.BaseController;
import com.tsrs.webedi.modular.system.service.ICommonService;
import com.tsrs.webedi.modular.system.service.IDownLoadDtService;

/**
 * 稟議書データ作成Controller
 *
 * @author Tsrs-WebEDI
 * @Date 2017年10月20日 下午2:50:10
 */
@RestController
@RequestMapping("/ApprDocDownLoad")
public class DownLoadDtController extends BaseController {

	@Resource
	private IDownLoadDtService iDownLoadDtService;

	@Resource
	private ICommonService commonService;

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public JsonResult exportData(@RequestParam(value = "workMonth", required = false) String workMonth,
			@RequestParam(value = "check1", required = false) boolean check1,
			@RequestParam(value = "check2", required = false) boolean check2, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<Map<String, Object>> listValue = iDownLoadDtService.selectApprDocDataList(workMonth);
			if (listValue.size() == 0) {
				return null;
			}
			Date now = new Date();
			SimpleDateFormat format_ymd = new SimpleDateFormat("yyyy-MM-dd");
			String this_year = format_ymd.format(now);
			Map<String, Object> ret = commonService.getInsuranceInfo(this_year);
			boolean flg = false;
			flg = iDownLoadDtService.export(listValue,ret, workMonth, check1, check2, request, response);

			if (flg = false) {
				return null;
			}
			return null;

		} catch (Exception e) {

			return null;
		}
	}
}
