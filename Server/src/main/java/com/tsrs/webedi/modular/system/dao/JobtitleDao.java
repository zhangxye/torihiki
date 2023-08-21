package com.tsrs.webedi.modular.system.dao;

import java.util.List;

import com.tsrs.webedi.common.persistence.model.LabelValueBean;
import com.tsrs.webedi.modular.system.persistence.dao.JobtitleMapper;

/**
 * <p>
  * 職位 Mapper 接口
 * </p>
 *
 * @author tsrs
 * @since 2017-09-03
 */
public interface JobtitleDao extends JobtitleMapper {
	List<LabelValueBean> getJobList();

}