package com.tsrs.webedi.modular.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tsrs.webedi.common.persistence.model.LabelValueBean;
import com.tsrs.webedi.modular.system.persistence.dao.SyssettingMapper;

/**
 * <p>
  * 設定 Mapper 接口
 * </p>
 *
 * @author tsrs
 * @since 2017-09-03
 */
public interface SyssettingDao extends SyssettingMapper {
	List<LabelValueBean> getDropdownStrVal1(@Param("sys_cd") String sysCd, 
			@Param("major_class_cd") String majorClassCd,
			@Param("mid_class_cd") String midClassCd,
			@Param("minor_class_cd") String minorClassCd
			);

}