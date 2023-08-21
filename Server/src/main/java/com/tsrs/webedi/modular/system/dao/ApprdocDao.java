package com.tsrs.webedi.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.tsrs.webedi.modular.system.jmodel.ApprDocInfo;
//import com.tsrs.webedi.modular.system.persistence.model.ApprdocExt;

/**
 * 稟議書dao
 *
 * @author Tsrs-WebEDI
 * @date 2017年2月12日 下午8:43:52
 */
@Mapper
public interface ApprdocDao {

	List<Map<String, Object>> selectEmpHisList(@Param("empno") String empno);
	
	List<Map<String, Object>> selectEmpHisList2(@Param("apprDocNo") String apprDocNo); 
	
	Map<String, Object> selectEmpLastInfo(@Param("empno") String empno);
	
	List<Map<String, Object>> selectApprDocList(@Param("clientNm") String clientNm,
											@Param("empNm") String empNm,
											@Param("apprDocNo") String apprDocNo,
											@Param("deptCd") String deptCd,
											@Param("createDtFr") String createDtFr,
											@Param("createDtTo") String createDtTo,
											@Param("workMonth") String workMonth,
											@Param("mainUserId") String mainUserId,
											@Param("arrpDocSt") String arrpDocSt);
	Map<String, Object> selectApprDocInfo(@Param("apprDocNo") String apprDocNo);
	
	Map<String, Object> selectApprDocInfo2(@Param("apprDocNo") String apprDocNo);
	
	Map<String, Object> selectApprDocPrevInfo(@Param("apprDocNo") String apprDocNo);
	
	public List<Map<String,Object>> searchForMonReqList(Page<?> page,Map<String, Object> searchCond);
}
