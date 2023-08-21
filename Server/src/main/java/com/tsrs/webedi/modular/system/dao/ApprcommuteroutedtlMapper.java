package com.tsrs.webedi.modular.system.dao;

import com.tsrs.webedi.modular.system.persistence.model.Apprcommuteroutedtl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
  * 通勤経路Dtl Mapper 接口
 * </p>
 *
 * @author tsrs
 * @since 2017-09-03
 */
public interface ApprcommuteroutedtlMapper extends BaseMapper<Apprcommuteroutedtl> {
	
	List<Map<String, Object>> selectApprcommuteroutedtlList(@Param("apprDocNo") String apprDocNo);

}