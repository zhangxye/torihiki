package com.tsrs.webedi.modular.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tsrs.webedi.modular.system.persistence.model.Esthdr;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 見積HDR情報 Mapper 接口
 * </p>
 *
 * @author jin-qk
 * @since 2018-03-26
 */
public interface EstDao extends BaseMapper<Esthdr> {

    List<Map<String, Object>> searchForEntry(Page<?> page, @Param("searchCond") Map<String, Object> searchCond);

    List<Map<String, Object>> searchForDetail(Page<?> page, @Param("searchCond") Map<String, Object> searchCond);

    Map<String, Object> selectEstHdrInfoForInsert(@Param("apprDocIdAry") String[] apprDocIdAry);

    List<Map<String, Object>> selectEstDtlInfoForInsert(@Param("apprDocIdAry") String[] apprDocIdAry);

    Map<String, Object> selectEstHdrInfoForDetail(@Param("est_hid") Integer estHid);

    List<Map<String, Object>> selectEstDtlInfoForDetail(@Param("est_hid") Integer estHid);

    List<Map<String, Object>> searchForOrderEntry(Page<?> page, @Param("searchCond") Map<String, Object> searchCond);

}