package com.tsrs.webedi.modular.system.service;

import java.util.List;
import java.util.Map;

import com.tsrs.webedi.common.persistence.model.LabelValueBean;

public interface ICommonService {

    List<LabelValueBean> getDropdownStrVal1(String sysCd, 
			String majorClassCd,
			String midClassCd);
    
    List<LabelValueBean> getJobList();
    
    /**
     * 社会保険計算用情報を取得
     * @param baseDt
     * @return
     */
    Map<String, Object> getInsuranceInfo(String baseDt);
    
}
