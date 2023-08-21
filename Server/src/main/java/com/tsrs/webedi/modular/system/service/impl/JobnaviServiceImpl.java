package com.tsrs.webedi.modular.system.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.tsrs.webedi.common.annotion.DataSource;
import com.tsrs.webedi.common.constant.DSEnum;
import com.tsrs.webedi.modular.system.dao.TbJobnaviDao;
import com.tsrs.webedi.modular.system.service.IJobnaviService;

/**
 * 稟議書サーブス
 *
 * @author Tsrs-WebEDI
 * @date 2017-05-05 22:20
 */
@Service
public class JobnaviServiceImpl implements IJobnaviService {

	@Resource
	TbJobnaviDao jobnaviDao;
	
	@Override
//	@Cacheable(value="CONSTANT",key="'JobnaviCandidateList'+#empName")
	@DataSource(name = DSEnum.DATA_SOURCE_BIZ)
	public List<Map<String, Object>> selectJobnaviCandidateList(String empName) {
		return jobnaviDao.selectJobnaviCandidateList(empName);
	}
	
	@Override
	@DataSource(name = DSEnum.DATA_SOURCE_BIZ)
	public Map<String, Object> selectJobnaviCandidate(String empno) {
		return jobnaviDao.selectJobnaviCandidate(empno);
	}

	@Override
//	@Cacheable(value="CONSTANT",key="'JobnaviJobList'+#pjNm")
	@DataSource(name = DSEnum.DATA_SOURCE_BIZ)
	public List<Map<String, Object>> selectJobnaviJobList(String pjNm) {
		return jobnaviDao.selectJobnaviJobList(pjNm);
	}
	
	@Override
	@DataSource(name = DSEnum.DATA_SOURCE_BIZ)
	public Map<String, Object> selectJobnaviJob(String pjCd) {
		return jobnaviDao.selectJobnaviJob(pjCd);
	}
	
	@Override
//	@Cacheable(value="CONSTANT",key="'JobnaviCompanyList'+#categoryId+'_'+#clientNm")
	@DataSource(name = DSEnum.DATA_SOURCE_BIZ)
	public List<Map<String, Object>> selectJobnaviCompanyList(String categoryId, String clientNm) {
		return jobnaviDao.selectJobnaviCompanyList(categoryId, clientNm);
	}
	
	@Override
	@DataSource(name = DSEnum.DATA_SOURCE_BIZ)
	public Map<String, Object> selectJobnaviClientInfo(String compayId,String categoryId) {
		return jobnaviDao.selectJobnaviClientInfo(compayId,categoryId);
	}
	
	@Override
	@DataSource(name = DSEnum.DATA_SOURCE_BIZ)
	public List<Map<String, Object>> selectJobnaviStaffList(String compayId) {
		return jobnaviDao.selectJobnaviStaffList(compayId);
	}
}
