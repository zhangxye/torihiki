package com.tsrs.webedi.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 稟議書dao
 *
 * @author Tsrs-WebEDI
 * @date 2017年2月12日 下午8:43:52
 */
@Mapper
public interface TbJobnaviDao {

	List<Map<String, Object>> selectJobnaviJobList(@Param("pjNm") String pjNm);
	
	Map<String, Object> selectJobnaviJob(@Param("pjCd") String pjCd);
	
	List<Map<String, Object>> selectJobnaviCandidateList(@Param("empName") String empName);
	
	Map<String, Object> selectJobnaviCandidate(@Param("empno") String empno);
	
	List<Map<String, Object>> selectJobnaviCompanyList(@Param("categoryId") String categoryId, @Param("clientNm") String clientNm);
	
	Map<String, Object> selectJobnaviClientInfo(@Param("compayId") String compayId, @Param("categoryId") String categoryId);
	
	List<Map<String, Object>> selectJobnaviStaffList(@Param("compayId") String compayId);
}
