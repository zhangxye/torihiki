package com.tsrs.webedi.modular.system.dao;

import com.tsrs.webedi.modular.system.persistence.model.Apprdocclientcontractevidence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
  * クライアント契約契約条件エビデンス Mapper 接口
 * </p>
 *
 * @author tsrs
 * @since 2017-09-03
 */
public interface ApprdocclientcontractevidenceMapper extends BaseMapper<Apprdocclientcontractevidence> {
	
	List<Map<String,Object>> selectEvidenceList(@Param("apprDocNo") String apprDocNo);

}