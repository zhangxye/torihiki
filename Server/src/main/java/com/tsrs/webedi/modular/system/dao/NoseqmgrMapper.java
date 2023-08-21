package com.tsrs.webedi.modular.system.dao;

import com.tsrs.webedi.modular.system.persistence.model.Noseqmgr;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
  * 採番管理 Mapper 接口
 * </p>
 *
 * @author tsrs
 * @since 2017-08-03
 */
public interface NoseqmgrMapper extends BaseMapper<Noseqmgr> {
	
	String getFieldNo(@Param("sys_cd") String sysCd, 
			@Param("sys_tbl_id") String sysTblId,
			@Param("sys_field") String sysField
			);

}