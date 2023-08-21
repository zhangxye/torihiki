package com.tsrs.webedi.modular.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tsrs.webedi.common.constant.Const;
import com.tsrs.webedi.common.persistence.model.LabelValueBean;
import com.tsrs.webedi.modular.system.dao.BasepricemgrMapper;
import com.tsrs.webedi.modular.system.dao.JobtitleDao;
import com.tsrs.webedi.modular.system.dao.RatiomgrMapper;
import com.tsrs.webedi.modular.system.dao.SyssettingDao;
import com.tsrs.webedi.modular.system.persistence.model.Basepricemgr;
import com.tsrs.webedi.modular.system.persistence.model.Ratiomgr;
import com.tsrs.webedi.modular.system.service.ICommonService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * ユーザーサーブス
 *
 * @author Tsrs-WebEDI
 * @date 2017-05-05 22:20
 */
@Service
@Transactional
public class CommonServiceImpl implements ICommonService {

	@Resource
	SyssettingDao syssettingDao;
	
	@Resource
	JobtitleDao jobtitleDao;
	
	@Resource
	BasepricemgrMapper basepricemgrMapper;
	
	@Resource
	RatiomgrMapper ratiomgrMapper;

	@Override
	public List<LabelValueBean> getDropdownStrVal1(String sysCd, String majorClassCd, String midClassCd) {
		return syssettingDao.getDropdownStrVal1(sysCd, majorClassCd, midClassCd, null);
	}

	@Override
	public List<LabelValueBean> getJobList() {
		return jobtitleDao.getJobList();
	}

	@Override
	public Map<String, Object> getInsuranceInfo(String baseDt) {
		
		Map<String, Object> ret = new HashMap<String, Object>();
		//健康保険標準報酬リスト
		List<Map<String, Object>> health_insurance_base_price_list = 
				getBasePriceList(Const.INSUR_TYPE.HEALTH_INSURANCE, baseDt);
		//厚生年金標準報酬リスト
		List<Map<String, Object>> employee_pension_base_price_list = 
				getBasePriceList(Const.INSUR_TYPE.EMPLOYE_EPENSION, baseDt);
		//健康保険料率リスト
		List<Map<String, Object>> health_insurance_ratio_list =
				getBaseRatioList(Const.INSUR_TYPE.HEALTH_INSURANCE, baseDt);
		//厚生年金料率リスト
		List<Map<String, Object>> employee_pension_ratio_list =
				getBaseRatioList(Const.INSUR_TYPE.EMPLOYE_EPENSION, baseDt);
		
        //健康保険標準報酬リスト
        ret.put("health_insurance_base_price_list",health_insurance_base_price_list);
		//厚生年金標準報酬リスト
		ret.put("employee_pension_base_price_list",employee_pension_base_price_list);
		//健康保険料率リスト
		ret.put("health_insurance_ratio_list",health_insurance_ratio_list);
		//厚生年金料率リスト
		ret.put("employee_pension_ratio_list",employee_pension_ratio_list);
		//雇用保険
		ret.put("employee_insurance",Const.EMPLOYEE_INSURANCE);
		
		return ret;
	}
	
	private List<Map<String, Object>> getBasePriceList(String insurType, String baseDt){
		QueryWrapper<Basepricemgr> basePriceEw = new QueryWrapper<Basepricemgr>();
	    basePriceEw.setEntity(new Basepricemgr());
	    basePriceEw.select("salary_from", "salary_to", "base_price");
	    basePriceEw.eq("base_price_type",insurType)
	    		   .le("start_dt", baseDt)
	    		   .ge("end_dt", baseDt);
	    basePriceEw.orderByAsc("salary_from","salary_to");
//	    basePriceEw.where("base_price_type={0}", InsurType)
//	    		.and("{0} BETWEEN start_dt AND end_dt", baseDt)
//	            .orderBy("salary_from ASC, salary_to ASC");
		return  basepricemgrMapper.selectMaps(basePriceEw);		
	}
	
	private List<Map<String, Object>> getBaseRatioList(String insurType, String baseDt){
		QueryWrapper<Ratiomgr> basePriceEw = new QueryWrapper<Ratiomgr>();
		basePriceEw.setEntity(new Ratiomgr());
		basePriceEw.select("age_from", "age_to", "ratio");
		basePriceEw.eq("ratio_type", insurType)
				   .le("start_dt", baseDt)
				   .ge("end_dt", baseDt);
		basePriceEw.orderByAsc("age_from","age_to");
//		basePriceEw.where("ratio_type={0}", insurType)
//		.and("{0} BETWEEN start_dt AND end_dt", baseDt)
//		.orderBy("age_from ASC, age_to ASC");
		return  ratiomgrMapper.selectMaps(basePriceEw);		
	}
	

}
