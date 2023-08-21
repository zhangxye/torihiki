package com.tsrs.webedi.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tsrs.webedi.modular.system.persistence.model.Apprapplydtl;

/**
 * <p>
  * 稟議書申請DTL情報 Mapper 接口
 * </p>
 *
 * @author tsrs
 * @since 2017-09-03
 */
public interface ApprapplydtlMapper extends BaseMapper<Apprapplydtl> {
	
	List<Map<String,Object>> selectApprApplyList(@Param("apprDocNo") String apprDocNo);
	
	List<Map<String,Object>> selectApplyList(@Param("apprDocId") Integer apprDocId,@Param("apprApplyHid") Integer apprApplyHid);
	
}