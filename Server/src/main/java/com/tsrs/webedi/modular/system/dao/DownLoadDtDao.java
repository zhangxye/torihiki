package com.tsrs.webedi.modular.system.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 稟議書データ作成dao
 *
 * @author Tsrs-WebEDI
 * @date 2017年10月20日 下午3:20
 */
@Mapper
public interface DownLoadDtDao {

	List<Map<String, Object>> selectApprDocDataList(
			@Param("workMonth") String workMonth);
}
