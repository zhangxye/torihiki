package com.tsrs.webedi.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tsrs.webedi.modular.system.persistence.model.Apprapplyhdr;

/**
 * <p>
  * 稟議書申請HDR情報 Mapper 接口
 * </p>
 *
 * @author tsrs
 * @since 2017-09-03
 */
public interface ApprapplyhdrMapper extends BaseMapper<Apprapplyhdr> {
	
	List<Map<String,Object>> selectApprApproveList(@Param("account") String account,
			@Param("apprDocNo") String apprDocNo,
			@Param("approveDtFr") String approveDtFr,
			@Param("approveDtTo") String approveDtTo,
			@Param("approveStatus") String approveStatus,
			@Param("empNo") String empNo,
			@Param("empNm") String empNm);

}