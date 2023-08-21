package com.tsrs.webedi.modular.system.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tsrs.webedi.modular.system.dao.ApprapplydtlMapper;
import com.tsrs.webedi.modular.system.dao.ApprapplyhdrMapper;
import com.tsrs.webedi.modular.system.dao.ApprcommuteroutedtlMapper;
import com.tsrs.webedi.modular.system.dao.ApprcommuteroutehdrMapper;
import com.tsrs.webedi.modular.system.dao.DownLoadDtDao;
//import com.tsrs.webedi.modular.system.dao.DownLoadDtMapper;
import com.tsrs.webedi.modular.system.persistence.model.Basepricemgr;
import com.tsrs.webedi.modular.system.persistence.model.Ratiomgr;
import com.tsrs.webedi.modular.system.service.IDownLoadDtService;

@Service
@Transactional
public class DownLoadDtServiceImpl implements IDownLoadDtService {

	@Resource
	DownLoadDtDao downLoadDtDao;

//	@Resource
//	DownLoadDtMapper downLoadDtMapper;

	@Resource
	ApprapplyhdrMapper apprapplyhdrMapper;

	@Resource
	ApprapplydtlMapper apprapplydtlMapper;

	@Resource
	ApprcommuteroutedtlMapper commuteroutedtlMapper;

	@Resource
	ApprcommuteroutehdrMapper apprcommuteroutehdrMapper;

	@Override
	public List<Map<String, Object>> selectApprDocDataList(String workMonth) {
		return downLoadDtDao.selectApprDocDataList(workMonth);
	}

	@Override
	public List<Map<String, Object>> selectApprcommuteroutedtl(String apprDocNo) {
		return commuteroutedtlMapper.selectApprcommuteroutedtlList(apprDocNo);
	}

	@Override
	public boolean export(List<Map<String, Object>> listValue, Map<String, Object> ret, String workMonth,
			boolean check1, boolean check2, HttpServletRequest request, HttpServletResponse response) {
		try {
			File newFile = createNewFile("TSRS帳票管理マスタ.xls");

			// 新しいファイルデータ作成
			InputStream is = null;
			HSSFWorkbook workbook = null;
			HSSFSheet sheet = null;
			try {
				is = new FileInputStream(newFile);
				workbook = new HSSFWorkbook(is);
				// シートを取得
				sheet = workbook.getSheetAt(0);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			boolean flg = false;
			List<Map<String, Object>> routeList;
			for (int i = 0; i < listValue.size(); i++) {
				routeList = selectApprcommuteroutedtl(listValue.get(i).get("appr_doc_no").toString());
				flg = writeExcelFile(listValue, ret, routeList, i, workbook, workMonth, check1, check2);
			}
			if (flg = false) {
				return false;
			}
			FileOutputStream fos = new FileOutputStream(newFile);
			workbook.write(fos);
			fos.flush();
			fos.close();

			// ダウンロード
			InputStream fis = new BufferedInputStream(new FileInputStream(newFile));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			response.setContentType("application/vnd.ms-excel");
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/vnd.ms-excel");
			response.setContentType("application/x-msdownload");
			String newName = URLEncoder.encode("TSRS帳票管理マスタ" + ".xls", "Shift-JIS");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + newName + "\"");
			response.addHeader("Content-Length", "" + newFile.length());
			toClient.write(buffer);
			toClient.flush();
			return true;
		} catch (Exception e) {

			return false;
		}
	}

//	@Override
//	public boolean exportClientDetailData(List<Map<String, Object>> listValue, String workMonth,
//			HttpServletRequest request, HttpServletResponse response) {
//		try {
//			File newFile = createNewFile("クライアント請求詳細一覧.xls");
//			//ワークブックを読み込みます。
//	        HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(new FileInputStream(newFile)));
//	        HSSFSheet sheet = wb.getSheetAt(0);
//
//	        boolean flg = false;
//			List<Map<String, Object>> routeList;
//			for (int i = 0; i < listValue.size(); i++) {
//				routeList = selectApprcommuteroutedtl(listValue.get(i).get("appr_doc_no").toString());
//				//flg = writeExceClientDetaillFile(listValue, routeList, i,workbook, workMonth);
//			}
//			if (flg = false) {
//				return false;
//			}
//
//
////			// 新しいファイルデータ作成
////			InputStream is = null;
////			HSSFWorkbook workbook = null;
////			HSSFSheet sheet = null;
////			try {
////				is = new FileInputStream(newFile);
////				workbook = new HSSFWorkbook(is);
////				// シートを取得
////				sheet = workbook.getSheetAt(0);
////			} catch (Exception e1) {
////				e1.printStackTrace();
////			}
////			boolean flg = false;
////			List<Map<String, Object>> routeList;
////			for (int i = 0; i < listValue.size(); i++) {
////				routeList = selectApprcommuteroutedtl(listValue.get(i).get("appr_doc_no").toString());
////				flg = writeExceClientDetaillFile(listValue, routeList, i,workbook, workMonth);
////			}
////			if (flg = false) {
////				return false;
////			}
////			FileOutputStream fos = new FileOutputStream(newFile);
////			workbook.write(fos);
////			fos.flush();
////			fos.close();
////
////			// ダウンロード
////			InputStream fis = new BufferedInputStream(new FileInputStream(newFile));
////			byte[] buffer = new byte[fis.available()];
////			fis.read(buffer);
////			fis.close();
////			response.setContentType("application/vnd.ms-excel");
////			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
////			response.setContentType("application/vnd.ms-excel");
////			response.setContentType("application/x-msdownload");
////			String newName = URLEncoder.encode("クライアント請求詳細一覧" + ".xls", "Shift-JIS");
////			response.setHeader("Content-Disposition", "attachment;filename=\"" + newName + "\"");
////			response.addHeader("Content-Length", "" + newFile.length());
////			toClient.write(buffer);
////			toClient.flush();
//			return true;
//		} catch (Exception e) {
//
//			return false;
//		}
//	}

	/**
	 * ファイル作成
	 *
	 * @author Tsrs-WebEDI
	 * @Date 2017年11月2日
	 */
	public File createNewFile(String strFileName) {
		// フォマードを取得
		//File file = new File("src/main/resources/TSRS帳票管理マスタ.xls");
		File file = new File("src/main/resources/"+strFileName);
		// 保存パス
		String realPath = "logs/daily";
		// ファイル名前
		//String newFileName = "TSRS帳票管理マスタ" + ".xls";
		String newFileName = strFileName;
		// 存在チェック
		File dir = new File(realPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// データ作成作成
		File newFile = new File(realPath, newFileName);
		try {
			newFile.createNewFile();
			// フォマードコピー
			fileChannelCopy(file, newFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newFile;
	}

	/**
	 * ファイルコピー
	 *
	 * @author Tsrs-WebEDI
	 * @Date 2017年11月2日
	 */
	public void fileChannelCopy(File s, File t) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(s), 1024);
				out = new BufferedOutputStream(new FileOutputStream(t), 1024);
				byte[] buffer = new byte[1024];
				int len;
				while ((len = in.read(buffer)) != -1) {
					out.write(buffer, 0, len);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	//支払いサイトにより、日付計算
//    public static String dispCalendar(String strDate, int ipaySiteDay) {
//		SimpleDateFormat formatter = (SimpleDateFormat)DateFormat.getDateInstance();
//		formatter.applyPattern("yyyy-MM");
//
//		Calendar cal = Calendar.getInstance();
//	    // 日付を設定
//		int iYear = Integer.parseInt(strDate.substring(0,4));
//		int iMonth = Integer.parseInt(strDate.substring(5));
//		cal.set(Calendar.YEAR, iYear);
//		cal.set(Calendar.MONTH, iMonth);
//
//		int Modmoth = ipaySiteDay/30;
//		cal.add(Calendar.MONTH, Modmoth);
//
//		int iMod = ipaySiteDay%30;
//		if(iMod == 0) {
//	        cal.add(Calendar.MONTH, -1);
//			cal.set(Calendar.DATE,cal.getActualMaximum(Calendar.DATE));
//		} else {
//			cal.set(Calendar.DATE,iMod);
//		}
//
//        formatter.applyPattern("yyyy/MM/dd");
//        return formatter.format(cal.getTime());
//    }
	//支払いサイトにより、日付計算
	  public static String dispCalendar(String strDate, int ipaySiteDay,String iFinishKbn) {
		  SimpleDateFormat formatter = (SimpleDateFormat)DateFormat.getDateInstance();
		  formatter.applyPattern("yyyy-MM");

		  Calendar cal = Calendar.getInstance();
		  // 日付を設定
		  int iYear = Integer.parseInt(strDate.substring(0,4));
		  int iMonth = Integer.parseInt(strDate.substring(5));
		  cal.set(Calendar.YEAR, iYear);
		  cal.set(Calendar.MONTH, iMonth);

		  //月末の場合
		  if(iFinishKbn.equals("月末")) {
			   int Modmoth = ipaySiteDay/30;
			   cal.set(Calendar.MONTH, iMonth+Modmoth);

			   int iMod = ipaySiteDay%30;

			   if(iMod == 0) {
				   cal.add(Calendar.MONTH, -1);
				   cal.set(Calendar.DATE,cal.getActualMaximum(Calendar.DATE));
				   } else {
				   cal.set(Calendar.DATE,iMod);
			   }
		   }
		  //月末ではない場合
		  else {
		    cal.set(Calendar.DATE, Integer.parseInt(iFinishKbn));

		   //締め切り+支払いサイトは30日以内の場合
		    if((ipaySiteDay+Integer.parseInt(iFinishKbn))<30) {
		    int Modmoth = ipaySiteDay/30;
		    cal.set(Calendar.MONTH, iMonth+Modmoth);

		    int iMod = ipaySiteDay%30;
		    cal.add(Calendar.MONTH, -1);
		    cal.add(Calendar.DATE,iMod);
		    }
		   //締め切り+支払いサイトは30日以上の場合
		    else {
		     int Modmoth = (ipaySiteDay+Integer.parseInt(iFinishKbn))/30;
		     cal.set(Calendar.MONTH, iMonth+Modmoth);
		     int iMod1 = ((ipaySiteDay+Integer.parseInt(iFinishKbn))%30);
		     if(iMod1 == 0) {
		      cal.add(Calendar.MONTH, -1);
		      cal.set(Calendar.DATE,cal.getActualMaximum(Calendar.DATE));
		      }else {
		       cal.add(Calendar.MONTH, -1);
		       cal.set(Calendar.DATE,iMod1);
		      }
		    }
		  }

		  formatter.applyPattern("yyyy/MM/dd");
		  return formatter.format(cal.getTime());
	  }

	  public static String zenkakuHiraganaToZenkakuKatakana(Object strObject) {
		if(null == strObject)
		{
			return "";
		}
		StringBuffer sb = new StringBuffer(strObject.toString());
		for (int i = 0; i < sb.length(); i++) {
		  char c = sb.charAt(i);
		  if (c >= 'ぁ' && c <= 'ん') {
		    sb.setCharAt(i, (char)(c - 'ぁ' + 'ァ'));
		  }
		}
		return sb.toString();
	 }
	  public static String clearSpace(String str) {
		  return str.replace(" ", "").replace("　", "");
	  }

	  public static String dateFormatConvert(Object strObject,String strInpartern, String strOutPartern) {
		String strDate = "";
		if(null == strObject) {
			return strDate;
		}
		strDate = strObject.toString();
		SimpleDateFormat sdFormat = new SimpleDateFormat(strInpartern);
		Date dateTemp;
		try {
			dateTemp = sdFormat.parse(strDate);
			strDate =  new SimpleDateFormat(strOutPartern).format(dateTemp);
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return strDate;
	  }

	  public static String digitalConvert(Object strObject) {
		  	String strDigital = "0";
		  	if(null == strObject) {
		  		return strDigital;
		  	}
		  	strDigital = strObject.toString();
		    BigDecimal bd = new BigDecimal(strDigital);
		    BigDecimal bd1 = bd.setScale(0, BigDecimal.ROUND_HALF_DOWN);
		    return bd1.toString();
	  }

	  /**
		 * データ作成
		 *
		 * @Date 2017年11月2日
		 * @return
		 */
		public static boolean writeExceClientDetaillFile(List<Map<String, Object>> listValue, List<Map<String, Object>> routeList, int i,
				HSSFWorkbook workBook, String workMonth) {
			try {
				HSSFSheet sheet = workBook.getSheetAt(0);
				int rowNo = i+6;
//				HSSFCellStyle cellStyle = workBook.createCellStyle();
//				HSSFDataFormat format = workBook.createDataFormat();
//				cellStyle.setDataFormat(format.getFormat("\\￥###,##0"));
				// cell作る
				HSSFRow row = sheet.createRow(rowNo);
				HSSFCell cell1 = row.createCell(0);
				HSSFCell cell2 = row.createCell(1);
				HSSFCell cell3 = row.createCell(2);
				HSSFCell cell4 = row.createCell(3);
				HSSFCell cell5 = row.createCell(4);
				HSSFCell cell6 = row.createCell(5);
				HSSFCell cell7 = row.createCell(6);
				HSSFCell cell8 = row.createCell(7);
				HSSFCell cell9 = row.createCell(8);
				HSSFCell cell10 = row.createCell(9);
				HSSFCell cell11 = row.createCell(10);
				HSSFCell cell12 = row.createCell(11);
				HSSFCell cell13 = row.createCell(12);
				HSSFCell cell14 = row.createCell(13);
				HSSFCell cell15 = row.createCell(14);
				HSSFCell cell16 = row.createCell(15);
				HSSFCell cell17 = row.createCell(16);
				HSSFCell cell18 = row.createCell(17);
				HSSFCell cell19 = row.createCell(18);

				//No.
				cell1.setCellValue("");
				//担当
				cell2.setCellValue("");
				//締日
				cell3.setCellValue(new HSSFRichTextString(listValue.get(i).get("bp_payment_due_k") == null ? ""
						: listValue.get(i).get("bp_payment_due_k") + ""));
				//ＩＤ
				cell4.setCellValue(new HSSFRichTextString(
						listValue.get(i).get("client_no") == null ? "" : listValue.get(i).get("client_no") + ""));
				//クライアント名
				cell5.setCellValue(new HSSFRichTextString(
						listValue.get(i).get("client_nm") == null ? "" : listValue.get(i).get("client_nm") + ""));
				//担当（部署）
				cell6.setCellValue(new HSSFRichTextString(listValue.get(i).get("client_staff_dept_nm") == null ? ""
						: listValue.get(i).get("client_staff_dept_nm") + ""));
				//担当（役職）
				cell7.setCellValue(new HSSFRichTextString(listValue.get(i).get("client_staff_position") == null ? ""
						: listValue.get(i).get("client_staff_position") + ""));
				//担当（氏名）
				cell8.setCellValue(new HSSFRichTextString(listValue.get(i).get("client_staff_nm") == null ? ""
						: listValue.get(i).get("client_staff_nm") + ""));
				//メルアド１
				cell9.setCellValue(new HSSFRichTextString(listValue.get(i).get("client_mail_addr") == null ? ""
						: listValue.get(i).get("client_mail_addr") + ""));
				//メルアド２（ＣＣ）
				cell10.setCellValue(new HSSFRichTextString(listValue.get(i).get("client_mail_addr_cc") == null ? ""
						: listValue.get(i).get("client_mail_addr_cc") + ""));
				//ＩＤ
				cell11.setCellValue(Double.parseDouble(listValue.get(i).get("emp_no").toString()));
				//エンジニア名
				cell12.setCellValue(new HSSFRichTextString(
						listValue.get(i).get("emp_nm") == null ? "" : listValue.get(i).get("emp_nm") + ""));
				//回収
				cell13.setCellValue("");
				//提出期日
				String strTemp = "";
				if(null != listValue.get(i).get("work_rep_dl_k")) {
					strTemp = listValue.get(i).get("work_rep_dl_k").toString();
					switch(strTemp) {
						case "1":
							strTemp = "指定なし";
						case "2":
							strTemp = "第1営業日";
						case "3":
							strTemp = "第2営業日";
						case "4":
							strTemp = "第3営業日";
						case "5":
							strTemp = "第4-5営業日";
					}
				}

				cell14.setCellValue(new HSSFRichTextString(strTemp));
				//客Ｆor自社F
				strTemp = "";
				if(null != listValue.get(i).get("work_rep_f")) {
					strTemp = listValue.get(i).get("work_rep_f").toString();
					switch(strTemp) {
						case "1":
							strTemp = "指定なし";
						case "2":
							strTemp = "客先F";
					}
				}
				cell15.setCellValue(new HSSFRichTextString(strTemp));
				//提出期日
				strTemp = "";
				if(null != listValue.get(i).get("bill_dl_k")) {
					strTemp = listValue.get(i).get("bill_dl_k").toString();
					switch(strTemp) {
						case "1":
							strTemp = "指定なし";
						case "2":
							strTemp = "第1営業日";
						case "3":
							strTemp = "第2営業日";
						case "4":
							strTemp = "第3営業日";
						case "5":
							strTemp = "第4-5営業日";
					}
				}
				cell16.setCellValue(new HSSFRichTextString(strTemp));
				//客Ｆor自社F
				strTemp = "";
				if(null != listValue.get(i).get("bill_f")) {
					strTemp = listValue.get(i).get("bill_f").toString();
					switch(strTemp) {
						case "1":
							strTemp = "指定なし";
						case "2":
							strTemp = "客先F";
					}
				}
				cell17.setCellValue(new HSSFRichTextString(strTemp));
				//添付書類
				strTemp = "";
				if(null != listValue.get(i).get("mon_attach_file_k")) {
					strTemp = listValue.get(i).get("mon_attach_file_k").toString();
					switch(strTemp) {
						case "1":
							strTemp = "有";
						case "2":
							strTemp = "無";
					}
				}
				cell18.setCellValue(new HSSFRichTextString(strTemp));
				//添付書類詳細
				cell19.setCellValue("");

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}


	/**
	 * データ作成
	 *
	 * @Date 2017年11月2日
	 * @return
	 */
	public static boolean writeExcelFile(List<Map<String, Object>> listValue, Map<String, Object> ret,
			List<Map<String, Object>> routeList, int i, HSSFWorkbook workBook, String workMonth, boolean check1,
			boolean check2) {
		try {
			HSSFSheet sheet = workBook.getSheetAt(0);
			int rowNo = sheet.getLastRowNum() + 1;
			HSSFCellStyle cellStyle = workBook.createCellStyle();
			HSSFDataFormat format = workBook.createDataFormat();
			cellStyle.setDataFormat(format.getFormat("\\￥###,##0"));
			// cell作る
			HSSFRow row = sheet.createRow(rowNo);
			HSSFCell cell1 = row.createCell(0);
			HSSFCell cell2 = row.createCell(1);
			HSSFCell cell3 = row.createCell(2);
			HSSFCell cell4 = row.createCell(3);
			HSSFCell cell5 = row.createCell(4);
			HSSFCell cell6 = row.createCell(5);
			HSSFCell cell7 = row.createCell(6);
			HSSFCell cell8 = row.createCell(7);
			HSSFCell cell9 = row.createCell(8);
			HSSFCell cell10 = row.createCell(9);
			HSSFCell cell11 = row.createCell(10);
			HSSFCell cell12 = row.createCell(11);
			HSSFCell cell13 = row.createCell(12);
			HSSFCell cell14 = row.createCell(13);
			HSSFCell cell15 = row.createCell(14);
			HSSFCell cell16 = row.createCell(15);
			HSSFCell cell17 = row.createCell(16);
			HSSFCell cell18 = row.createCell(17);
			HSSFCell cell19 = row.createCell(18);
			HSSFCell cell20 = row.createCell(19);
			HSSFCell cell21 = row.createCell(20);
			HSSFCell cell22 = row.createCell(21);
			HSSFCell cell23 = row.createCell(22);
			HSSFCell cell24 = row.createCell(23);
			HSSFCell cell25 = row.createCell(24);
			HSSFCell cell26 = row.createCell(25);
			HSSFCell cell27 = row.createCell(26);
			HSSFCell cell28 = row.createCell(27);
			HSSFCell cell29 = row.createCell(28);
			HSSFCell cell30 = row.createCell(29);
			HSSFCell cell31 = row.createCell(30);
			HSSFCell cell32 = row.createCell(31);
			HSSFCell cell33 = row.createCell(32);
			HSSFCell cell34 = row.createCell(33);
			HSSFCell cell35 = row.createCell(34);
			HSSFCell cell36 = row.createCell(35);
			HSSFCell cell37 = row.createCell(36);
			HSSFCell cell38 = row.createCell(37);
			HSSFCell cell39 = row.createCell(38);
			HSSFCell cell40 = row.createCell(39);
			HSSFCell cell41 = row.createCell(40);
			HSSFCell cell42 = row.createCell(41);
			HSSFCell cell43 = row.createCell(42);
			HSSFCell cell44 = row.createCell(43);
			HSSFCell cell45 = row.createCell(44);
			HSSFCell cell46 = row.createCell(45);
			HSSFCell cell47 = row.createCell(46);
			HSSFCell cell48 = row.createCell(47);
			HSSFCell cell49 = row.createCell(48);
			HSSFCell cell50 = row.createCell(49);
			HSSFCell cell51 = row.createCell(50);
			HSSFCell cell52 = row.createCell(51);
			HSSFCell cell53 = row.createCell(52);
			HSSFCell cell54 = row.createCell(53);
			HSSFCell cell55 = row.createCell(54);
			HSSFCell cell56 = row.createCell(55);
			HSSFCell cell57 = row.createCell(56);
			HSSFCell cell58 = row.createCell(57);
			HSSFCell cell59 = row.createCell(58);
			HSSFCell cell60 = row.createCell(59);
			HSSFCell cell61 = row.createCell(60);
			HSSFCell cell62 = row.createCell(61);
			HSSFCell cell63 = row.createCell(62);
			HSSFCell cell64 = row.createCell(63);
			HSSFCell cell65 = row.createCell(64);
			HSSFCell cell66 = row.createCell(65);
			HSSFCell cell67 = row.createCell(66);
			HSSFCell cell68 = row.createCell(67);
			HSSFCell cell69 = row.createCell(68);
			HSSFCell cell70 = row.createCell(69);
			HSSFCell cell71 = row.createCell(70);
			HSSFCell cell72 = row.createCell(71);
			HSSFCell cell73 = row.createCell(72);
			HSSFCell cell74 = row.createCell(73);
			HSSFCell cell75 = row.createCell(74);
			HSSFCell cell76 = row.createCell(75);
			HSSFCell cell77 = row.createCell(76);
			HSSFCell cell78 = row.createCell(77);
			HSSFCell cell79 = row.createCell(78);
			HSSFCell cell80 = row.createCell(79);
			HSSFCell cell81 = row.createCell(80);
			HSSFCell cell82 = row.createCell(81);
			HSSFCell cell83 = row.createCell(82);
			HSSFCell cell84 = row.createCell(83);
			HSSFCell cell85 = row.createCell(84);
			HSSFCell cell86 = row.createCell(85);
			HSSFCell cell87 = row.createCell(86);
			HSSFCell cell88 = row.createCell(87);
			HSSFCell cell89 = row.createCell(88);
			HSSFCell cell90 = row.createCell(89);
			HSSFCell cell91 = row.createCell(90);
			HSSFCell cell92 = row.createCell(91);
			HSSFCell cell93 = row.createCell(92);
			HSSFCell cell94 = row.createCell(93);
			HSSFCell cell95 = row.createCell(94);
			HSSFCell cell96 = row.createCell(95);
			HSSFCell cell97 = row.createCell(96);
			HSSFCell cell98 = row.createCell(97);
			HSSFCell cell99 = row.createCell(98);
			HSSFCell cell100 = row.createCell(99);
			HSSFCell cell101 = row.createCell(100);
			HSSFCell cell102 = row.createCell(101);
			HSSFCell cell103 = row.createCell(102);
			HSSFCell cell104 = row.createCell(103);
			HSSFCell cell105 = row.createCell(104);
			HSSFCell cell106 = row.createCell(105);
			HSSFCell cell107 = row.createCell(106);
			HSSFCell cell108 = row.createCell(107);
			HSSFCell cell109 = row.createCell(108);
			HSSFCell cell110 = row.createCell(109);
			HSSFCell cell111 = row.createCell(110);
			HSSFCell cell112 = row.createCell(111);
			HSSFCell cell113 = row.createCell(112);
			HSSFCell cell114 = row.createCell(113);
			HSSFCell cell115 = row.createCell(114);
			HSSFCell cell116 = row.createCell(115);
			HSSFCell cell117 = row.createCell(116);
			HSSFCell cell118 = row.createCell(117);
			HSSFCell cell119 = row.createCell(118);
			HSSFCell cell120 = row.createCell(119);
			HSSFCell cell121 = row.createCell(120);
			HSSFCell cell122 = row.createCell(121);
			HSSFCell cell123 = row.createCell(122);
			HSSFCell cell124 = row.createCell(123);
			HSSFCell cell125 = row.createCell(124);
			HSSFCell cell126 = row.createCell(125);
			HSSFCell cell127 = row.createCell(126);
			HSSFCell cell128 = row.createCell(127);
			HSSFCell cell129 = row.createCell(128);
			HSSFCell cell130 = row.createCell(129);
			HSSFCell cell131 = row.createCell(130);
			HSSFCell cell132 = row.createCell(131);
			HSSFCell cell133 = row.createCell(132);
			HSSFCell cell134 = row.createCell(133);
			HSSFCell cell135 = row.createCell(134);
			HSSFCell cell136 = row.createCell(135);
			HSSFCell cell137 = row.createCell(136);
			HSSFCell cell138 = row.createCell(137);
			HSSFCell cell139 = row.createCell(138);
			HSSFCell cell140 = row.createCell(139);
			HSSFCell cell141 = row.createCell(140);
			HSSFCell cell142 = row.createCell(141);
			HSSFCell cell143 = row.createCell(142);
			HSSFCell cell144 = row.createCell(143);
			HSSFCell cell145 = row.createCell(144);
			HSSFCell cell146 = row.createCell(145);
			HSSFCell cell147 = row.createCell(146);
			HSSFCell cell148 = row.createCell(147);
			HSSFCell cell149 = row.createCell(148);
			HSSFCell cell150 = row.createCell(149);
			HSSFCell cell151 = row.createCell(150);
			HSSFCell cell152 = row.createCell(151);
			// データバインド
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM");
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateStr = workMonth + "-01";
			Date date = formatter.parse(dateStr);
			date = new java.sql.Date(date.getTime());
			format1.applyPattern("yyyy/MM");
			String yyMM = format1.format(date);
			// 社員番号
			cell1.setCellValue(Double.parseDouble(listValue.get(i).get("emp_no").toString()));
			// 社員名
			cell2.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("emp_nm") == null ? "" : listValue.get(i).get("emp_nm") + ""));
			// 所属会社ID
			if(null != listValue.get(i).get("bp_comp_id") && !"".equals(listValue.get(i).get("bp_comp_id").toString())) {
				cell3.setCellValue(Double.parseDouble(listValue.get(i).get("bp_comp_id").toString()));
			}
			// 所属会社名
			cell4.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("bp_comp_emp_nm") == null ? "" : listValue.get(i).get("bp_comp_emp_nm") + ""));
			// 希望雇用形態
			cell5.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("emp_pat_k") == null ? "" : listValue.get(i).get("emp_pat_k") + ""));
			// 稼働中案件番号
			cell6.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("pj_cd") == null ? "" : listValue.get(i).get("pj_cd") + ""));

			//精算条件が固定の場合
			if("3".equals(listValue.get(i).get("accounting_pat_k").toString()))	{
				// 作業時間（上限・H）
				cell7.setCellValue("固定");
				// 作業時間(下限）
				cell8.setCellValue("固定");
			}else{
				if(null != listValue.get(i).get("wh_max") && null != listValue.get(i).get("wh_min")) {
					// 作業時間（上限・H）
					cell7.setCellValue(new HSSFRichTextString(listValue.get(i).get("wh_max") + ""));
					// 作業時間(下限）
					cell8.setCellValue(new HSSFRichTextString(listValue.get(i).get("wh_min") + ""));
				}else if(null != listValue.get(i).get("wh_mid")) {
					// 作業時間（上限・H）
					cell7.setCellValue(new HSSFRichTextString(listValue.get(i).get("wh_mid") + ""));
					// 作業時間(下限）
					cell8.setCellValue(new HSSFRichTextString(listValue.get(i).get("wh_mid") + ""));
				}
			}

			// 単価
			cell9.setCellValue(Integer.parseInt(listValue.get(i).get("sell_price").toString().replace(",", "")));
			cell9.setCellStyle(cellStyle);
			// 原価
			cell10.setCellValue(Integer.parseInt(listValue.get(i).get("cost_price").toString().replace(",", "")));
			cell10.setCellStyle(cellStyle);
			// 超過単価（円）
			cell11.setCellValue(Integer.parseInt(listValue.get(i).get("excess_price").toString().replace(",", "")));
			cell11.setCellStyle(cellStyle);
			// 控除単価（円）
			cell12.setCellValue(Integer.parseInt(listValue.get(i).get("deducation_price").toString().replace(",", "")));
			cell12.setCellStyle(cellStyle);
			// 交通費（円）
			cell13.setCellValue(Integer.parseInt(listValue.get(i).get("travel_cost").toString().replace(",", "")));
			cell13.setCellStyle(cellStyle);
			// 経費
			cell14.setCellValue(Integer.parseInt(listValue.get(i).get("other_cost").toString().replace(",", "")));
			cell14.setCellStyle(cellStyle);
			// 携帯番号
			cell15.setCellValue(new HSSFRichTextString(listValue.get(i).get("staff_mobile_no") == null ? ""
					: listValue.get(i).get("staff_mobile_no") + ""));
			// 契約開始日
			cell16.setCellValue(new HSSFRichTextString(listValue.get(i).get("appr_doc_dt_from") == null ? ""
					: listValue.get(i).get("appr_doc_dt_from") + ""));
			// 契約終了日
			cell17.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("appr_doc_dt_to") == null ? "" : listValue.get(i).get("appr_doc_dt_to") + ""));
			// E-mail（自宅）
			cell18.setCellValue(new HSSFRichTextString(listValue.get(i).get("staff_mail_addr") == null ? ""
					: listValue.get(i).get("staff_mail_addr") + ""));
			// 支払サイト
			cell19.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("bp_pay_month_k") == null ? "" : listValue.get(i).get("bp_pay_month_k") + ""));
			// 締日(本人)
			cell20.setCellValue(new HSSFRichTextString(listValue.get(i).get("bp_payment_due_k") == null ? ""
					: listValue.get(i).get("bp_payment_due_k") + ""));
			// 入社日
			cell21.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("entrance_dt") == null ? "" : listValue.get(i).get("entrance_dt") + ""));
			// 出金予定日
//			String yyMM1 = yyMM.substring(0, 5) + String.format("%02d", (Integer.parseInt(yyMM.substring(5, 7)) + 1));
//			String yyMM2 = yyMM.substring(0, 5) + String.format("%02d", (Integer.parseInt(yyMM.substring(5, 7)) + 2));
			Calendar cl = Calendar.getInstance();
			cl.setTime(date);
			cl.add(Calendar.MONTH, 1);
//			Date tempDate = cl.getTime();
//			if (listValue.get(i).get("bp_pay_plan_d").toString().compareTo("末") == 0) {
//				cell22.setCellValue(new HSSFRichTextString(yyMM1 + "/" + getDaysOfMonth(tempDate) + ""));
//			} else {
//				cell22.setCellValue(new HSSFRichTextString(yyMM2 + "/"
//						+ String.format("%02d", Integer.parseInt(listValue.get(i).get("bp_pay_plan_d").toString()))
//						+ ""));
//			}
			// 出金予定日
			if(null != listValue.get(i).get("bp_payment_plan_d"))
			{
				String strDate = dispCalendar(yyMM, Integer.parseInt(listValue.get(i).get("bp_payment_plan_d").toString()),listValue.get(i).get("bp_payment_due_k").toString());
				cell22.setCellValue(strDate);
			}

//			// 社保加算後
//			double tempCost = Math.floor((Integer.parseInt(listValue.get(i).get("cost_price").toString().replace(",", ""))
//					+ Integer.parseInt(listValue.get(i).get("travel_cost").toString().replace(",", ""))
//					+ Integer.parseInt(listValue.get(i).get("insurance_cost").toString().replace(",", ""))) * 1.0085)
//					+ Integer.parseInt(listValue.get(i).get("other_cost").toString().replace(",", ""));
//			double tempCostB = Integer.parseInt(listValue.get(i).get("cost_price").toString().replace(",", ""))
//					+ Integer.parseInt(listValue.get(i).get("travel_cost").toString().replace(",", ""));
//			if (listValue.get(i).get("emp_pat_k").toString().compareTo("委託契約") == 0) {
//				cell23.setCellValue((int) tempCostB);
//			} else {
//				cell23.setCellValue((int) tempCost);
//			}
			cell23.setCellValue(Double.parseDouble(listValue.get(i).get("gross_cost_price").toString()));
			cell23.setCellStyle(cellStyle);
			// 工数（月次用）
			Calendar from = Calendar.getInstance();
			Calendar to = Calendar.getInstance();
			if (listValue.get(i).get("appr_doc_dt_from") != null) {
				from.setTime(new java.sql.Date(formatter
						.parse(listValue.get(i).get("appr_doc_dt_from").toString().replace("/", "-")).getTime()));
			}
			if (listValue.get(i).get("appr_doc_dt_to") != null) {
				to.setTime(new java.sql.Date(formatter
						.parse(listValue.get(i).get("appr_doc_dt_to").toString().replace("/", "-")).getTime()));
			}
			int n = 0;
			while (!from.after(to)) {
				n++;
				from.add(Calendar.MONTH, 1);
			}
//			if (listValue.get(i).get("billing_month").toString().substring(0, 7)
//					.compareTo(listValue.get(i).get("site_entrance_dt").toString().substring(0, 7)) == 0) {
//				if (n > 0) {
//					cell24.setCellValue(Double.parseDouble(listValue.get(i).get("kou_su1").toString()));
//				} else {
//					cell24.setCellValue(1);
//				}
//			} else {
//				cell24.setCellValue(1);
//			}

			Date dateWorkMonth = format1.parse(yyMM);
			Date dateDtFrom = format1.parse(listValue.get(i).get("appr_doc_dt_from").toString());
			if (dateWorkMonth.compareTo(dateDtFrom) == 0) {
				cell24.setCellValue(Double.parseDouble(listValue.get(i).get("kou_su1").toString()));
			} else {
				cell24.setCellValue(1);
			}
			// 要員マイナンバー
			cell25.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("emp_my_number") == null ? "" : listValue.get(i).get("emp_my_number") + ""));
			// 所属会社法人マイナンバー
			cell26.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("bp_my_number") == null ? "" : listValue.get(i).get("bp_my_number") + ""));
			// 出勤時刻
			cell27.setCellValue(new HSSFRichTextString(listValue.get(i).get("work_start_time") == null ? ""
					: listValue.get(i).get("work_start_time") + ""));
			// 退勤時刻
			cell28.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("work_end_time") == null ? "" : listValue.get(i).get("work_end_time") + ""));
			// 作業報告書提出締日
			cell29.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("billing_month") == null ? "" : yyMM + "/" + getDaysOfMonth(date) + ""));
			// 欠勤などの備考
			cell30.setCellValue("");
			// 作業案件名称
			cell31.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("pj_nm") == null ? "" : listValue.get(i).get("pj_nm") + ""));
			// 作業内容・範囲
			cell32.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("work_content") == null ? "" : listValue.get(i).get("work_content") + ""));
			// 勤務地
			cell33.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("work_place") == null ? "" : listValue.get(i).get("work_place") + ""));
			// 締日（売上）時間
			if (listValue.get(i).get("payment_due_k").toString().compareTo("月末") == 0) {
				cell34.setCellValue(new HSSFRichTextString(
						listValue.get(i).get("billing_month") == null ? "" : yyMM + "/" + getDaysOfMonth(date) + ""));
			} else {
				cell34.setCellValue(new HSSFRichTextString(yyMM + "/"
						+ String.format("%02d", Integer.parseInt(listValue.get(i).get("payment_due_k").toString()))
						+ ""));
			}
			// 補助（売上）該当案件のステータス（新規更新）
			cell35.setCellValue(new HSSFRichTextString(listValue.get(i).get("appr_doc_create_k") == null ? ""
					: listValue.get(i).get("appr_doc_create_k") + ""));
			// 次のPJ（売上）選択し：既存、経験
			cell36.setCellValue(new HSSFRichTextString(listValue.get(i).get("appr_doc_create_k") == null ? ""
					: listValue.get(i).get("appr_doc_create_k") + ""));
			// 次のPJ（仕入）選択し：既存、経験
			cell37.setCellValue(new HSSFRichTextString(listValue.get(i).get("appr_doc_create_k") == null ? ""
					: listValue.get(i).get("appr_doc_create_k") + ""));
			// 入金予定日
//			if (listValue.get(i).get("pay_plan_d").toString().compareTo("月末") == 0) {
//				cell38.setCellValue(new HSSFRichTextString(listValue.get(i).get("billing_month") == null ? ""
//						: yyMM1 + "/" + getDaysOfMonth(tempDate) + ""));
//			} else {
//				cell38.setCellValue(new HSSFRichTextString(yyMM2 + "/"
//						+ String.format("%02d", Integer.parseInt(listValue.get(i).get("pay_plan_d").toString())) + ""));
//			}
			// 入金予定日
			if(null != listValue.get(i).get("payment_plan_d"))
			{
				String strDate = dispCalendar(yyMM, Integer.parseInt(listValue.get(i).get("payment_plan_d").toString()),listValue.get(i).get("payment_due_k").toString());
				cell38.setCellValue(strDate);
			}
			// 締日（仕入）
			if (listValue.get(i).get("bp_payment_due_k").toString().compareTo("月末") == 0) {
				cell39.setCellValue(new HSSFRichTextString(
						listValue.get(i).get("billing_month") == null ? "" : yyMM + "/" + getDaysOfMonth(date) + ""));
			} else {
				cell39.setCellValue(new HSSFRichTextString(yyMM + "/"
						+ String.format("%02d", Integer.parseInt(listValue.get(i).get("bp_payment_due_k").toString()))
						+ ""));
			}
			// 補助（仕入）該当案件のステータス（新規更新）
			cell40.setCellValue(new HSSFRichTextString(listValue.get(i).get("appr_doc_create_k") == null ? ""
					: listValue.get(i).get("appr_doc_create_k") + ""));
			// 営業主担当
			cell41.setCellValue(new HSSFRichTextString(listValue.get(i).get("business_main_user_id") == null ? ""
					: listValue.get(i).get("business_main_user_id") + ""));
			// 副担当
			cell42.setCellValue(new HSSFRichTextString(listValue.get(i).get("business_sub_user_id") == null ? ""
					: listValue.get(i).get("business_sub_user_id") + ""));
			// 注文書受領日
			cell43.setCellValue(new HSSFRichTextString(listValue.get(i).get("order_recv_hope_dt") == null ? ""
					: listValue.get(i).get("order_recv_hope_dt") + ""));
			// 入社スタートの時間
			cell44.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("entrance_dt") == null ? "" : listValue.get(i).get("entrance_dt") + ""));
			// 案件更新/延長の時間
			cell45.setCellValue("");
			// 業務案件今月分の開始日
			cell46.setCellValue(new HSSFRichTextString(listValue.get(i).get("pj_start_dt") == null ? ""
					: listValue.get(i).get("pj_start_dt").toString().replace("-", "/") + ""));
			// 業務案件今月分の終了日
			cell47.setCellValue(new HSSFRichTextString(listValue.get(i).get("pj_end_dt") == null ? ""
					: listValue.get(i).get("pj_end_dt").toString().replace("-", "/") + ""));
			// 作業時間単位
			cell48.setCellValue(new HSSFRichTextString(listValue.get(i).get("work_time_unit_k") == null ? "0"
					: listValue.get(i).get("work_time_unit_k") + ""));
			if(clearSpace(listValue.get(i).get("client_staff_nm").toString()).equals(
					clearSpace(listValue.get(i).get("client_req_staff_nm").toString())) )
			{
				// お客様側の担当者名/ID
				cell49.setCellValue(new HSSFRichTextString(listValue.get(i).get("client_staff_nm") == null ? ""
						: listValue.get(i).get("client_staff_nm") + ""));
				// 担当者部署
				cell50.setCellValue(new HSSFRichTextString(listValue.get(i).get("client_staff_dept_nm") == null ? ""
						: listValue.get(i).get("client_staff_dept_nm") + ""));
				// 担当者役職
				cell51.setCellValue(new HSSFRichTextString(listValue.get(i).get("client_staff_position") == null ? ""
						: listValue.get(i).get("client_staff_position") + ""));
				// 担当者読み方
				cell52.setCellValue(clearSpace(zenkakuHiraganaToZenkakuKatakana(listValue.get(i).get("client_staff_nm_fuli"))));
				// 担当者携帯
				cell53.setCellValue(new HSSFRichTextString(listValue.get(i).get("client_staff_mobile_no") == null ? ""
						: listValue.get(i).get("client_staff_mobile_no") + ""));
				// 担当者電話
				cell54.setCellValue(new HSSFRichTextString(
						listValue.get(i).get("client_tel_no") == null ? "" : listValue.get(i).get("client_tel_no") + ""));
				// 担当者FAX
				cell55.setCellValue(new HSSFRichTextString(
						listValue.get(i).get("client_staff_fax_no") == null ? "" : listValue.get(i).get("client_staff_fax_no") + ""));
				// 担当者メール
				cell56.setCellValue(new HSSFRichTextString(listValue.get(i).get("client_mail_addr_cc") == null ? ""
						: listValue.get(i).get("client_mail_addr_cc") + ""));
				// 担当者メール(社用)
				cell57.setCellValue(new HSSFRichTextString(listValue.get(i).get("client_mail_addr") == null ? ""
						: listValue.get(i).get("client_mail_addr") + ""));
			} else {
				// お客様側の担当者名/ID
				cell49.setCellValue(new HSSFRichTextString(listValue.get(i).get("client_req_staff_nm") == null ? ""
						: listValue.get(i).get("client_req_staff_nm") + ""));
				// 担当者部署
				cell50.setCellValue(new HSSFRichTextString(listValue.get(i).get("client_req_staff_dept_nm") == null ? ""
						: listValue.get(i).get("client_req_staff_dept_nm") + ""));
				// 担当者役職
				cell51.setCellValue(new HSSFRichTextString(listValue.get(i).get("client_req_staff_position") == null ? ""
						: listValue.get(i).get("client_req_staff_position") + ""));
				// 担当者読み方
				cell52.setCellValue(clearSpace(zenkakuHiraganaToZenkakuKatakana(listValue.get(i).get("client_req_staff_nm_fuli"))));
				// 担当者携帯
				cell53.setCellValue(new HSSFRichTextString(listValue.get(i).get("client_req_staff_mobile_no") == null ? ""
						: listValue.get(i).get("client_req_staff_mobile_no") + ""));
				// 担当者電話
				cell54.setCellValue(new HSSFRichTextString(
						listValue.get(i).get("client_req_tel_no") == null ? "" : listValue.get(i).get("client_req_tel_no") + ""));
				// 担当者FAX
				cell55.setCellValue(new HSSFRichTextString(
						listValue.get(i).get("client_req_fax_no") == null ? "" : listValue.get(i).get("client_req_fax_no") + ""));
				// 担当者メール
				cell56.setCellValue(new HSSFRichTextString(listValue.get(i).get("client_req_mail_addr_cc") == null ? ""
						: listValue.get(i).get("client_req_mail_addr_cc") + ""));
				// 担当者メール(社用)
				cell57.setCellValue(new HSSFRichTextString(listValue.get(i).get("client_req_mail_addr") == null ? ""
						: listValue.get(i).get("client_req_mail_addr") + ""));
				}
			// 注文内容
			cell58.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("work_content") == null ? "" : listValue.get(i).get("work_content") + ""));
			// （締日(上位)）
			cell59.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("payment_due_k") == null ? "" : listValue.get(i).get("payment_due_k") + ""));
			// 本人社保料加算額（工数考慮）
			// 社保計算
			int tempInCost = calculation(i, n, listValue, ret);
			cell60.setCellValue(tempInCost);
			cell60.setCellStyle(cellStyle);
			// 入社処理（契約用）
//			cell61.setCellValue(new HSSFRichTextString(
//					listValue.get(i).get("procedure_dt") == null ? "" : listValue.get(i).get("procedure_dt") + ""));
			cell61.setCellValue(dateFormatConvert(listValue.get(i).get("procedure_dt"),"yyyy/mm/dd","m/dd/yyyy"));
			// 補助
			cell62.setCellValue(listValue.get(i).get("appr_doc_help").toString());
			// 売上計上月
			cell63.setCellValue(new HSSFRichTextString(listValue.get(i).get("sales_addup_month") == null ? ""
					: listValue.get(i).get("sales_addup_month").toString().replace("0", "") + ""));
			// お客様の会社番号
			cell64.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("client_no") == null ? "" : listValue.get(i).get("client_no") + ""));
			// お客様の会社名
			cell65.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("client_nm") == null ? "" : listValue.get(i).get("client_nm") + ""));
			// お客様の電話番号
			cell66.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("client_tel_no") == null ? "" : listValue.get(i).get("client_tel_no") + ""));
			// お客様のFAX番号
			cell67.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("client_staff_fax_no") == null ? "" : listValue.get(i).get("client_staff_fax_no") + ""));
			// 郵便番号
			cell68.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("client_post_no") == null ? "" : listValue.get(i).get("client_post_no") + ""));
			// 連絡先住所
			cell69.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("client_addr") == null ? "" : listValue.get(i).get("client_addr") + ""));
			// 送付備考
			cell70.setCellValue("");
			// お支払期日
//			if (listValue.get(i).get("pay_plan_d").toString().compareTo("月末") == 0) {
//				cell71.setCellValue(new HSSFRichTextString(listValue.get(i).get("billing_month") == null ? ""
//						: yyMM1 + "/" + getDaysOfMonth(tempDate) + ""));
//			} else {
//				cell71.setCellValue(new HSSFRichTextString(yyMM2 + "/"
//						+ String.format("%02d", Integer.parseInt(listValue.get(i).get("pay_plan_d").toString())) + ""));
//			}
			// お支払期日
			if(null != listValue.get(i).get("payment_plan_d"))
			{
				String strDate = dispCalendar(yyMM, Integer.parseInt(listValue.get(i).get("payment_plan_d").toString()),listValue.get(i).get("payment_due_k").toString());
				cell71.setCellValue(strDate);
			}
			// フリガナ(住所)
			cell72.setCellValue("");
			// 住所(下位)
			cell73.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("staff_addr") == null ? "" : listValue.get(i).get("staff_addr") + ""));
			// 〒郵便番号(下位)
			cell74.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("staff_post_no") == null ? "" : listValue.get(i).get("staff_post_no") + ""));
			// TEL(下位)
			if(null != listValue.get(i).get("staff_tel_no") && !"".equals(listValue.get(i).get("staff_tel_no").toString())) {
				cell75.setCellValue(new HSSFRichTextString(listValue.get(i).get("staff_tel_no").toString()));
			}
			else {
				cell75.setCellValue(new HSSFRichTextString(listValue.get(i).get("staff_mobile_no").toString()));
			}
			cell75.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("staff_tel_no") == null ? "" : listValue.get(i).get("staff_tel_no") + ""));
			// 法人マイナンバー
			cell76.setCellValue(new HSSFRichTextString(listValue.get(i).get("client_my_number") == null ? ""
					: listValue.get(i).get("client_my_number") + ""));
			// 工数（契約用）
			if (n > 0) {
				cell77.setCellValue(n + Double.parseDouble(listValue.get(i).get("kou_su1").toString()) - 1);
			} else {
				cell77.setCellValue(Double.parseDouble(listValue.get(i).get("kou_su1").toString()));
			}
			// 支払サイト（上位）
			cell78.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("payment_plan_d") == null ? "" : listValue.get(i).get("payment_plan_d") + ""));
			// 希望職種
			cell79.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("pj_class_nm") == null ? "" : listValue.get(i).get("pj_class_nm") + ""));
			// 性別
			cell80.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("emp_sex") == null ? "" : listValue.get(i).get("emp_sex") + ""));
			// 生年月日
			cell81.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("emp_birthday") == null ? "" : listValue.get(i).get("emp_birthday") + ""));
			// フリガナ
//			cell82.setCellValue(new HSSFRichTextString(
//					listValue.get(i).get("emp_nm_fuli") == null ? "" : listValue.get(i).get("emp_nm_fuli") + ""));
			cell82.setCellValue(clearSpace(zenkakuHiraganaToZenkakuKatakana(listValue.get(i).get("emp_nm_fuli"))));
			// 休憩時間
			cell83.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("break_time_min") == null ? "" : listValue.get(i).get("break_time_min") + ""));
			// 基本給
//			cell84.setCellValue(
//					Integer.parseInt(listValue.get(i).get("sell_price_other_cost").toString().replace(",", "")));
			int iSum = Integer.parseInt(digitalConvert(listValue.get(i).get("appoint_allow_amt")))
					+ Integer.parseInt(digitalConvert(listValue.get(i).get("house_allow_amt")))
					+ Integer.parseInt(digitalConvert(listValue.get(i).get("communication_allow_amt")))
					+ Integer.parseInt(digitalConvert(listValue.get(i).get("site_allow_amt")))
					+ Integer.parseInt(digitalConvert(listValue.get(i).get("overwork_allow_amt")))
					+ Integer.parseInt(digitalConvert(listValue.get(i).get("other_allow1_amt")))
					+ Integer.parseInt(digitalConvert(listValue.get(i).get("other_allow2_amt")));

			int iCost =Integer.parseInt(listValue.get(i).get("cost_price").toString().replace(",", ""));

			cell84.setCellValue(iCost - iSum);
			cell84.setCellStyle(cellStyle);
			// 住宅手当
//			cell85.setCellValue(
//					Integer.parseInt(listValue.get(i).get("house_allow_ratio").toString().replace(",", "")));
			cell85.setCellValue(Integer.parseInt(digitalConvert(listValue.get(i).get("house_allow_amt"))));
			cell85.setCellStyle(cellStyle);
			// 通信手当
//			cell86.setCellValue(
//					Integer.parseInt(listValue.get(i).get("communication_allow_ratio").toString().replace(",", "")));
			cell86.setCellValue(Integer.parseInt(digitalConvert(listValue.get(i).get("communication_allow_amt"))));
			cell86.setCellStyle(cellStyle);
			// 資格手当
//			cell87.setCellValue(
//					Integer.parseInt(listValue.get(i).get("other_allow1_ratio").toString().replace(",", "")));
			cell87.setCellValue(Integer.parseInt(digitalConvert(listValue.get(i).get("other_allow1_amt"))));
			cell87.setCellStyle(cellStyle);
			// 残業手当
//			cell88.setCellValue(
//					Integer.parseInt(listValue.get(i).get("overwork_allow_ratio").toString().replace(",", "")));
			cell88.setCellValue(Integer.parseInt(digitalConvert(listValue.get(i).get("overwork_allow_amt"))));
			cell88.setCellStyle(cellStyle);
			// 賃金締切日
			cell89.setCellValue(new HSSFRichTextString(listValue.get(i).get("bp_payment_due_k") == null ? ""
					: listValue.get(i).get("bp_payment_due_k") + ""));
			// 賃金支払日
			cell90.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("bp_pay_plan_d") == null ? "" : listValue.get(i).get("bp_pay_plan_d") + ""));
			if (null != routeList && routeList.size() > 0 && routeList.get(0) != null) {
				if (routeList.size() > 0) {
					// 自宅最寄駅の線①
					cell91.setCellValue(new HSSFRichTextString(routeList.get(0).get("from_home_line_nm") == null ? ""
							: routeList.get(0).get("from_home_line_nm") + ""));
					// 自宅最寄駅の駅①
					cell92.setCellValue(new HSSFRichTextString(routeList.get(0).get("from_home_station_nm") == null ? ""
							: routeList.get(0).get("from_home_station_nm") + ""));
					// 現場最寄駅の線①
					cell93.setCellValue(new HSSFRichTextString(routeList.get(0).get("to_comp_line_nm") == null ? ""
							: routeList.get(0).get("to_comp_line_nm") + ""));
					// 現場最寄駅の駅①
					cell94.setCellValue(new HSSFRichTextString(routeList.get(0).get("to_comp_station_nm") == null ? ""
							: routeList.get(0).get("to_comp_station_nm") + ""));
					// 通勤経路①の交通費
					cell95.setCellValue(
							(int) Double.parseDouble(routeList.get(0).get("travel_cost").toString().replace(",", "")));
					cell95.setCellStyle(cellStyle);
				}
				if (routeList.size() > 1) {
					// 自宅最寄駅の線②
					cell96.setCellValue(new HSSFRichTextString(routeList.get(1).get("from_home_line_nm") == null ? ""
							: routeList.get(1).get("from_home_line_nm") + ""));
					// 自宅最寄駅の駅②
					cell97.setCellValue(new HSSFRichTextString(routeList.get(1).get("from_home_station_nm") == null ? ""
							: routeList.get(1).get("from_home_station_nm") + ""));
					// 現場最寄駅の線②
					cell98.setCellValue(new HSSFRichTextString(routeList.get(1).get("to_comp_line_nm") == null ? ""
							: routeList.get(1).get("to_comp_line_nm") + ""));
					// 現場最寄駅の駅②
					cell99.setCellValue(new HSSFRichTextString(routeList.get(1).get("to_comp_station_nm") == null ? ""
							: routeList.get(1).get("to_comp_station_nm") + ""));
					// 通勤経路②の交通費
					cell100.setCellValue(
							(int) Double.parseDouble(routeList.get(1).get("travel_cost").toString().replace(",", "")));
					cell100.setCellStyle(cellStyle);
				}
				if (routeList.size() > 2) {
					// 自宅最寄駅の線③
					cell114.setCellValue(new HSSFRichTextString(routeList.get(2).get("from_home_line_nm") == null ? ""
							: routeList.get(2).get("from_home_line_nm") + ""));
					// 自宅最寄駅の駅③
					cell115.setCellValue(
							new HSSFRichTextString(routeList.get(2).get("from_home_station_nm") == null ? ""
									: routeList.get(2).get("from_home_station_nm") + ""));
					// 現場最寄駅の線③
					cell116.setCellValue(new HSSFRichTextString(routeList.get(2).get("to_comp_line_nm") == null ? ""
							: routeList.get(2).get("to_comp_line_nm") + ""));
					// 現場最寄駅の駅③
					cell117.setCellValue(new HSSFRichTextString(routeList.get(2).get("to_comp_station_nm") == null ? ""
							: routeList.get(2).get("to_comp_station_nm") + ""));
					// 通勤経路③の交通費
					cell118.setCellValue(
							(int) Double.parseDouble(routeList.get(2).get("travel_cost").toString().replace(",", "")));
					cell118.setCellStyle(cellStyle);
				}
				if (routeList.size() > 3) {
					// 自宅最寄駅の線④
					cell119.setCellValue(new HSSFRichTextString(routeList.get(3).get("from_home_line_nm") == null ? ""
							: routeList.get(3).get("from_home_line_nm") + ""));
					// 自宅最寄駅の駅④
					cell120.setCellValue(
							new HSSFRichTextString(routeList.get(3).get("from_home_station_nm") == null ? ""
									: routeList.get(3).get("from_home_station_nm") + ""));
					// 現場最寄駅の線④
					cell121.setCellValue(new HSSFRichTextString(routeList.get(3).get("to_comp_line_nm") == null ? ""
							: routeList.get(3).get("to_comp_line_nm") + ""));
					// 現場最寄駅の駅④
					cell122.setCellValue(new HSSFRichTextString(routeList.get(3).get("to_comp_station_nm") == null ? ""
							: routeList.get(3).get("to_comp_station_nm") + ""));
					// 通勤経路④の交通費
					cell123.setCellValue(
							(int) Double.parseDouble(routeList.get(3).get("travel_cost").toString().replace(",", "")));
					cell123.setCellStyle(cellStyle);
				}
			}
			// 始業時刻
			cell101.setCellValue(new HSSFRichTextString(listValue.get(i).get("bp_work_start_time") == null ? ""
					: listValue.get(i).get("bp_work_start_time") + ""));
			// 終業時刻
			cell102.setCellValue(new HSSFRichTextString(listValue.get(i).get("bp_work_end_time") == null ? ""
					: listValue.get(i).get("bp_work_end_time") + ""));
			// 役職手当
//			cell103.setCellValue(
//					Integer.parseInt(listValue.get(i).get("appoint_allow_ratio").toString().replace(",", "")));
			cell103.setCellValue(Integer.parseInt(digitalConvert(listValue.get(i).get("appoint_allow_amt"))));
			cell103.setCellStyle(cellStyle);
			// 扶養手当
			cell104.setCellValue(0);
			cell104.setCellStyle(cellStyle);
			// 現場手当
//			cell105.setCellValue(
//					Integer.parseInt(listValue.get(i).get("site_allow_ratio").toString().replace(",", "")));
			cell105.setCellValue(
					Integer.parseInt(digitalConvert(listValue.get(i).get("site_allow_amt").toString())));
			cell105.setCellStyle(cellStyle);
			// 当番手当
			cell106.setCellValue(0);
			cell106.setCellStyle(cellStyle);
			// そのた手当２
//			cell107.setCellValue(
//					Integer.parseInt(listValue.get(i).get("other_allow2_ratio").toString().replace(",", "")));
			cell107.setCellValue(Integer.parseInt(digitalConvert(listValue.get(i).get("other_allow2_amt"))));
			cell107.setCellStyle(cellStyle);
//			// 本人上限時間
//			cell108.setCellValue(new HSSFRichTextString(
//					listValue.get(i).get("bp_wh_max") == null ? "固定" : listValue.get(i).get("bp_wh_max") + ""));
//			// 本人下限時間
//			cell109.setCellValue(new HSSFRichTextString(
//					listValue.get(i).get("bp_wh_min") == null ? "固定" : listValue.get(i).get("bp_wh_min") + ""));

			if("3".equals(listValue.get(i).get("bp_accounting_pat_k").toString()))	{
				//精算条件が固定の場合
				// 本人上限時間
				cell108.setCellValue("固定");
				// 本人下限時間
				cell109.setCellValue("固定");
			}else{
				//精算条件が固定以外の場合
				if(null != listValue.get(i).get("bp_wh_max") && null != listValue.get(i).get("bp_wh_min")) {
					// 本人上限時間
					cell108.setCellValue(new HSSFRichTextString(listValue.get(i).get("bp_wh_max") + ""));
					// 本人下限時間
					cell109.setCellValue(new HSSFRichTextString(listValue.get(i).get("bp_wh_min") + ""));
				}else if(null != listValue.get(i).get("bp_wh_mid")) {
					// 本人上限時間
					cell108.setCellValue(new HSSFRichTextString(listValue.get(i).get("bp_wh_mid") + ""));
					// 本人下限時間
					cell109.setCellValue(new HSSFRichTextString(listValue.get(i).get("bp_wh_mid") + ""));
				}
			}
			// 本人超過単価
			cell110.setCellValue(Integer.parseInt(listValue.get(i).get("bp_excess_price").toString().replace(",", "")));
			cell110.setCellStyle(cellStyle);
			// 本人控除単価
			cell111.setCellValue(
					Integer.parseInt(listValue.get(i).get("bp_deducation_price").toString().replace(",", "")));
			cell111.setCellStyle(cellStyle);
			// 本人調整単位
			cell112.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("bp_work_unit_k") == null ? "" : listValue.get(i).get("bp_work_unit_k") + ""));
			// 本人支払いサイト
			cell113.setCellValue(new HSSFRichTextString(listValue.get(i).get("bp_payment_plan_d") == null ? ""
					: listValue.get(i).get("bp_payment_plan_d") + ""));
			// 往復交通費日額合計
			if(listValue.get(i).get("day_travel_cost") != null )
			{
				cell124.setCellValue(Integer.parseInt(listValue.get(i).get("day_travel_cost").toString().replace(",", "")));
			}
			cell124.setCellStyle(cellStyle);
			// 社会保険加入
			if (listValue.get(i).get("insurance_k") == null
					|| listValue.get(i).get("insurance_k").toString().compareTo("保険無し") == 0) {
				cell125.setCellValue("無");
			} else {
				cell125.setCellValue("有");
			}
			// 退社日
			cell126.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("withdrawal_dt") == null ? "" : listValue.get(i).get("withdrawal_dt") + ""));
			// 退社後郵便番号
			cell127.setCellValue(new HSSFRichTextString(listValue.get(i).get("after_withdrawal_post_no") == null ? ""
					: listValue.get(i).get("after_withdrawal_post_no") + ""));
			// 退社後住所
			cell128.setCellValue(new HSSFRichTextString(listValue.get(i).get("after_withdrawal_addr") == null ? ""
					: listValue.get(i).get("after_withdrawal_addr") + ""));

			ArrayList<String> result = new ArrayList<String>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");// 格式化为年月
			Calendar min = Calendar.getInstance();
			Calendar max = Calendar.getInstance();
			if (listValue.get(i).get("pj_start_dt") != null) {
				min.setTime(sdf.parse(listValue.get(i).get("pj_start_dt").toString()));
				min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
			}
			if (listValue.get(i).get("pj_end_dt") != null) {
				max.setTime(sdf.parse(listValue.get(i).get("pj_end_dt").toString()));
				max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
			}
			Calendar curr = min;
			while (curr.before(max)) {
				result.add(sdf.format(curr.getTime()));
				curr.add(Calendar.MONTH, 1);
			}
			if (listValue.get(i).get("pj_start_dt").toString().substring(0, 4)
					.compareTo(listValue.get(i).get("pj_end_dt").toString().substring(0, 4)) == 0) {
				result.add(sdf.format(curr.getTime()));
			}
			// 3月
			if (result.contains(listValue.get(i).get("pj_start_dt").toString().substring(0, 4) + "-03")) {
				cell129.setCellValue(new HSSFRichTextString("●" + ""));
			}
			// 4月
			if (result.contains(listValue.get(i).get("pj_start_dt").toString().substring(0, 4) + "-04")
					|| result.contains(listValue.get(i).get("pj_end_dt").toString().substring(0, 4) + "-04")) {
				cell130.setCellValue(new HSSFRichTextString("●" + ""));
			}
			// 5月
			if (result.contains(listValue.get(i).get("pj_start_dt").toString().substring(0, 4) + "-05")
					|| result.contains(listValue.get(i).get("pj_end_dt").toString().substring(0, 4) + "-05")) {
				cell131.setCellValue(new HSSFRichTextString("●" + ""));
			}
			// 6月
			if (result.contains(listValue.get(i).get("pj_start_dt").toString().substring(0, 4) + "-06")
					|| result.contains(listValue.get(i).get("pj_end_dt").toString().substring(0, 4) + "-06")) {
				cell132.setCellValue(new HSSFRichTextString("●" + ""));
			}
			// 7月
			if (result.contains(listValue.get(i).get("pj_start_dt").toString().substring(0, 4) + "-07")
					|| result.contains(listValue.get(i).get("pj_end_dt").toString().substring(0, 4) + "-07")) {
				cell133.setCellValue(new HSSFRichTextString("●" + ""));
			}
			// 8月
			if (result.contains(listValue.get(i).get("pj_start_dt").toString().substring(0, 4) + "-08")
					|| result.contains(listValue.get(i).get("pj_end_dt").toString().substring(0, 4) + "-08")) {
				cell134.setCellValue(new HSSFRichTextString("●" + ""));
			}
			// 9月
			if (result.contains(listValue.get(i).get("pj_start_dt").toString().substring(0, 4) + "-09")
					|| result.contains(listValue.get(i).get("pj_end_dt").toString().substring(0, 4) + "-09")) {
				cell135.setCellValue(new HSSFRichTextString("●" + ""));
			}
			// 10月
			if (result.contains(listValue.get(i).get("pj_start_dt").toString().substring(0, 4) + "-10")
					|| result.contains(listValue.get(i).get("pj_end_dt").toString().substring(0, 4) + "-10")) {
				cell136.setCellValue(new HSSFRichTextString("●" + ""));
			}
			// 11月
			if (result.contains(listValue.get(i).get("pj_start_dt").toString().substring(0, 4) + "-11")
					|| result.contains(listValue.get(i).get("pj_end_dt").toString().substring(0, 4) + "-11")) {
				cell137.setCellValue(new HSSFRichTextString("●" + ""));
			}
			// 12月
			if (result.contains(listValue.get(i).get("pj_start_dt").toString().substring(0, 4) + "-12")
					|| result.contains(listValue.get(i).get("pj_end_dt").toString().substring(0, 4) + "-12")) {
				cell138.setCellValue(new HSSFRichTextString("●" + ""));
			}
			// 1月
			if (result.contains(listValue.get(i).get("pj_start_dt").toString().substring(0, 4) + "-01")
					|| result.contains(listValue.get(i).get("pj_end_dt").toString().substring(0, 4) + "-01")) {
				cell139.setCellValue(new HSSFRichTextString("●" + ""));
			}
			// 2月
			if (result.contains(listValue.get(i).get("pj_start_dt").toString().substring(0, 4) + "-02")
					|| result.contains(listValue.get(i).get("pj_end_dt").toString().substring(0, 4) + "-02")) {
				cell140.setCellValue(new HSSFRichTextString("●" + ""));
			}
			// 3月
			if (result.contains(listValue.get(i).get("pj_end_dt").toString().substring(0, 4) + "-03")) {
				cell141.setCellValue(new HSSFRichTextString("●" + ""));
			}

			// BPのFAX番号
			cell142.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("bp_fax_no") == null ? "" : listValue.get(i).get("bp_fax_no") + ""));
			// 電話番号
			cell143.setCellValue(new HSSFRichTextString(
					listValue.get(i).get("staff_tel_no") == null ? "" : listValue.get(i).get("staff_tel_no") + ""));
			// 担当者（フリガナ）
			cell144.setCellValue(new HSSFRichTextString(listValue.get(i).get("bp_staff_nm_fuli") == null ? ""
					: listValue.get(i).get("bp_staff_nm_fuli") + ""));
			// 所属部署
			cell145.setCellValue(new HSSFRichTextString(listValue.get(i).get("bp_staff_dept_nm") == null ? ""
					: listValue.get(i).get("bp_staff_dept_nm") + ""));
			// 役職
			cell146.setCellValue(new HSSFRichTextString(listValue.get(i).get("bp_staff_position") == null ? ""
					: listValue.get(i).get("bp_staff_position") + ""));
			// 担当者メール
			cell147.setCellValue(new HSSFRichTextString(listValue.get(i).get("bp_staff_mail_addr") == null ? ""
					: listValue.get(i).get("bp_staff_mail_addr") + ""));
			// 交通費（円）
			cell148.setCellValue(Integer.parseInt(listValue.get(i).get("travel_cost").toString().replace(",", "")));
			cell148.setCellStyle(cellStyle);
			if (check1 == true) {
				cell149.setCellValue("○");
			} else {
				cell149.setCellValue("");
			}
			// 契約帳票
			if (check2 == true) {
				cell150.setCellValue("○");
			} else {
				cell150.setCellValue("");
			}
			//HSSFRow rowNo2 = sheet.getRow(2);
			//HSSFCell ce11 = rowNo2.getCell(151);
			//if (ce11.getStringCellValue().compareTo("人事側状態") == 0) {
			if (listValue.get(i).get("emp_pat_k").toString().equals("正社員")
					|| listValue.get(i).get("emp_pat_k").toString().equals("契約社員")) {
				// 営業側状態
				cell151.setCellValue("×");
				// 人事側状態
				if ("1".equals(listValue.get(i).get("procedure_k").toString())) {
					cell152.setCellValue("入社");
				} else if ("2".equals(listValue.get(i).get("procedure_k").toString())) {
					cell152.setCellValue("変更");
				} else if ("3".equals(listValue.get(i).get("procedure_k").toString())) {
					cell152.setCellValue("退社");
				}
			} else {
				// 人事側状態
				cell152.setCellValue("×");
				// 営業側状態
				if (listValue.get(i).get("site_entrance_dt").toString().compareTo(yyMM) == 0) {
					cell151.setCellValue("新規");
				} else {

					if(null != listValue.get(i).get("emp_info_chg_flg")) {
						if ("Y".equals(listValue.get(i).get("emp_info_chg_flg").toString())) {
							cell151.setCellValue("変更");
						} else {
							cell151.setCellValue("既存");
						}
					}
				}
		//	}
		/*} else {
			if (listValue.get(i).get("emp_pat_k").toString() == "正社員"
					|| listValue.get(i).get("emp_pat_k").toString() == "契約社員") {
				// 営業側状態
				cell151.setCellValue("×");
				// 人事側状態セット
				if (listValue.get(i).get("procedure_dt") != null) {
					cell152.setCellValue("入社");
				} else if (listValue.get(i).get("withdrawal_dt") != null) {
					cell152.setCellValue("退社");
				} else {
					cell152.setCellValue("変更");
				}
			} else {
				// 人事側状態
				cell152.setCellValue("×");
				// 営業側状態
				if (listValue.get(i).get("site_entrance_dt").toString().compareTo(yyMM) == 0) {
					cell151.setCellValue("新規");
				} else {
					cell151.setCellValue("変更");
				}
			}*/
		}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static int calculation(int i, int n, List<Map<String, Object>> listValue, Map<String, Object> ret) {
		Date now = new Date();
		SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
		String birth_year = listValue.get(i).get("emp_birthday").toString().substring(0, 4);
		String this_year = format_y.format(now);
		int emp_age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);
		double kosu = (double) 0;
		if (n > 0) {
			kosu = n + Double.parseDouble(listValue.get(i).get("kou_su1").toString()) - 1;
		} else {
			kosu = Double.parseDouble(listValue.get(i).get("kou_su1").toString());
		}

		double cost_travel_cost = Integer.parseInt(listValue.get(i).get("cost_price").toString().replace(",", ""))
				* kosu + Integer.parseInt(listValue.get(i).get("travel_cost_first_mon").toString().replace(",", ""));

		ArrayList<Basepricemgr> loadHealthInsuranceBasePriceList = new ArrayList<Basepricemgr>();
		ArrayList<Map<String, BigDecimal>> healthPrice = (ArrayList<Map<String, BigDecimal>>) (ret
				.get("health_insurance_base_price_list"));
		for (Map<String, BigDecimal> map : healthPrice) {
			Basepricemgr basepricemgr = new Basepricemgr();
			basepricemgr.setSalaryFrom(map.get("salary_from"));
			basepricemgr.setSalaryTo(map.get("salary_to"));
			basepricemgr.setBasePrice(map.get("base_price"));
			loadHealthInsuranceBasePriceList.add(basepricemgr);
		}
		// 健康保険標準報酬
		BigDecimal health_insurance_base_price = new BigDecimal(0);
		for (Basepricemgr basepricemgr : loadHealthInsuranceBasePriceList) {
			BigDecimal val = new BigDecimal(cost_travel_cost);
			if (val.compareTo(basepricemgr.getSalaryFrom()) >= 0 && val.compareTo(basepricemgr.getSalaryTo()) < 0) {
				health_insurance_base_price = basepricemgr.getBasePrice();
			}
		}

		BigDecimal health_insurance_ratio = new BigDecimal(0);
		ArrayList<Ratiomgr> loadHealthInsuranceRatioList = new ArrayList<Ratiomgr>();
		ArrayList<Map<String, Object>> healthPriceRatio = (ArrayList<Map<String, Object>>) (ret
				.get("health_insurance_ratio_list"));
		for (Map<String, Object> map : healthPriceRatio) {
			Ratiomgr ratiomgr = new Ratiomgr();
			ratiomgr.setAgeFrom((Integer) map.get("age_from"));
			ratiomgr.setAgeTo((Integer) map.get("age_to"));
			ratiomgr.setRatio((BigDecimal) map.get("ratio"));
			loadHealthInsuranceRatioList.add(ratiomgr);
		}
		// 健康保険料率
		for (Ratiomgr ratiomgr : loadHealthInsuranceRatioList) {
			if (emp_age >= ratiomgr.getAgeFrom() && emp_age < ratiomgr.getAgeTo()) {
				health_insurance_ratio = ratiomgr.getRatio();
			}
		}
		ArrayList<Basepricemgr> loadEmployeePensionBasePriceList = new ArrayList<Basepricemgr>();
		ArrayList<Map<String, BigDecimal>> employeePrice = (ArrayList<Map<String, BigDecimal>>) (ret
				.get("employee_pension_base_price_list"));
		for (Map<String, BigDecimal> map : employeePrice) {
			Basepricemgr basepricemgr = new Basepricemgr();
			basepricemgr.setSalaryFrom(map.get("salary_from"));
			basepricemgr.setSalaryTo(map.get("salary_to"));
			basepricemgr.setBasePrice(map.get("base_price"));
			loadEmployeePensionBasePriceList.add(basepricemgr);
		}
		// 厚生年金標準報酬
		BigDecimal employee_pension_base_price = new BigDecimal(0);
		for (Basepricemgr basepricemgr : loadEmployeePensionBasePriceList) {
			BigDecimal val = new BigDecimal(cost_travel_cost);
			if (val.compareTo(basepricemgr.getSalaryFrom()) >= 0 && val.compareTo(basepricemgr.getSalaryTo()) < 0) {
				employee_pension_base_price = basepricemgr.getBasePrice();
			}
		}
		// 厚生年金料率
		ArrayList<Ratiomgr> loadEmployeePensionRatioList = new ArrayList<Ratiomgr>();
		ArrayList<Map<String, Object>> pensionPriceRatio = (ArrayList<Map<String, Object>>) (ret
				.get("employee_pension_ratio_list"));
		BigDecimal employee_pension_ratio = new BigDecimal(0);
		for (Map<String, Object> map : pensionPriceRatio) {
			Ratiomgr ratiomgr = new Ratiomgr();
			ratiomgr.setAgeFrom((Integer) map.get("age_from"));
			ratiomgr.setAgeTo((Integer) map.get("age_to"));
			ratiomgr.setRatio((BigDecimal) map.get("ratio"));
			loadEmployeePensionRatioList.add(ratiomgr);
		}
		for (Ratiomgr ratiomgr : loadEmployeePensionRatioList) {
			if (emp_age >= ratiomgr.getAgeFrom() && emp_age < ratiomgr.getAgeTo()) {
				employee_pension_ratio = ratiomgr.getRatio();
			}
		}
		// 200=/100/2
		BigDecimal base = new BigDecimal(200);
		// 健康保険
		double health_insurance = health_insurance_base_price.multiply(health_insurance_ratio).divide(base)
				.doubleValue();
		// 厚生年金
		double employee_pension = employee_pension_base_price.multiply(employee_pension_ratio).divide(base)
				.doubleValue();
		// 社会保険料金=健康保険+厚生年金
		cost_travel_cost = Double.parseDouble(String.format("%.1f", cost_travel_cost));
		int insurance_cost = (int) Math.round(cost_travel_cost + health_insurance + employee_pension);
		return insurance_cost;
	}

	/**
	 * 日付変化処理
	 *
	 * @author Tsrs-WebEDI
	 * @Date 2017年11月2日
	 */
	public static int getDaysOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
}
