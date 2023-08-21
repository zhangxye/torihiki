package com.tsrs.webedi.modular.system.service;

import java.util.List;
import java.util.Map;

public interface IJobnaviService {
	 /**
     * 稟議書サービス
     *
     * @author Tsrs-WebEDI
     * @Date 2017/4/27 17:01
     */
    
    List<Map<String, Object>> selectJobnaviCandidateList(String empName);
    
    Map<String, Object> selectJobnaviCandidate(String empno);

    List<Map<String, Object>> selectJobnaviJobList(String pjNm);
    Map<String, Object> selectJobnaviJob(String pjCd);
    
    List<Map<String, Object>> selectJobnaviCompanyList(String categoryId, String clientNm);
    

	Map<String, Object> selectJobnaviClientInfo(String compayId, String categoryId);
	
	List<Map<String, Object>> selectJobnaviStaffList(String compayId);

}
