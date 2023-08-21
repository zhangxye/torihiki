package com.tsrs.webedi.core.util;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * EXCELツールクラス
 *
 * @author Jin-qk
 * @date 2018-05-02
 */
public class ExcelUtil {
	
	public static void setCellValue(HSSFSheet sheet, int row, int col, String value, HSSFCellStyle cellStyle) {
		
		if(value == null) return;
		
		HSSFCell cell = sheet.getRow(row).getCell(col);
		if(cellStyle != null) {
			cell.setCellStyle(cellStyle);
		}
		cell.setCellValue(new HSSFRichTextString(value));
	}
	public static void setCellValue(HSSFSheet sheet, int row, int col, String value) {
		setCellValue(sheet, row, col, value, null);
	}
	
	public static void setCellValue(HSSFSheet sheet, int row, int col, Date value) {
		if(value == null) return;
		HSSFCell cell = sheet.getRow(row).getCell(col);
		cell.setCellValue(value);
	}
	
	public static void setCellValue(HSSFSheet sheet, int row, int col, double value) {	
		HSSFCell cell = sheet.getRow(row).getCell(col);
		cell.setCellValue(value);
	}
	
	public static void setCellValue(HSSFSheet sheet, int row, int col, BigDecimal value) {	
		if(value == null) return;
		HSSFCell cell = sheet.getRow(row).getCell(col);
		cell.setCellValue(value.doubleValue());
	}


}
