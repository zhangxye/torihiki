package com.tsrs.webedi.modular.system.service.impl;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tsrs.webedi.modular.system.dao.NoseqmgrMapper;
import com.tsrs.webedi.modular.system.persistence.model.Noseqmgr;
import com.tsrs.webedi.modular.system.service.INoSeqMgrService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


/**
 * 稟議書サーブス
 *
 * @author Tsrs-WebEDI
 * @date 2017-05-05 22:20
 */
@Service
@Transactional
public class NoSeqMgrServiceImpl implements INoSeqMgrService {

	@Resource
	NoseqmgrMapper noseqmgrMapper;

	@Override
    public String getFieldNo(String sysCd, 
			String sysTblId,
			String sysField){
		String fieldNo = null;
		Noseqmgr key = new Noseqmgr();
		key.setSysCd(sysCd);
		key.setSysTblId(sysTblId);
		key.setSysField(sysField);
		QueryWrapper<Noseqmgr> qw = new QueryWrapper<Noseqmgr>(key);
		Noseqmgr noSeqMgr = noseqmgrMapper.selectOne(qw);
		if(noSeqMgr == null){
			return null;
		}
		UpdateWrapper<Noseqmgr> noSeqMrgUw= new UpdateWrapper<Noseqmgr>();
		noSeqMrgUw.eq("sys_cd", sysCd)
				  .eq("sys_tbl_id", sysTblId)
				  .eq("sys_field", sysField);
		//年月が変わるとき、開始番号から採番し直す
		String sysYm = getCurYYMM();
		if(!org.apache.commons.lang.StringUtils.equals(sysYm, noSeqMgr.getSysYm())){
			noSeqMgr.setSysYm(sysYm);
			noSeqMgr.setSysCurrentNo(noSeqMgr.getSysSeqStartNo());
			noSeqMgr.update(noSeqMrgUw);
//			noSeqMgr.update("sys_cd={0} AND sys_tbl_id={1} AND sys_field={2}", sysCd, sysTblId, sysField);
		}
		//番号は上限値（終了番号）を超えると、採番失敗、戻り値をNULLにする
		if(noSeqMgr.getSysCurrentNo() >= noSeqMgr.getSysSeqEndNo()){
			return null;
		}
		fieldNo = noseqmgrMapper.getFieldNo(sysCd, sysTblId, sysField);
		//番号を＋１する
		noSeqMgr.setSysCurrentNo(noSeqMgr.getSysCurrentNo()+1);
		noSeqMgr.update(noSeqMrgUw);
//		noSeqMgr.update("sys_cd={0} AND sys_tbl_id={1} AND sys_field={2}", sysCd, sysTblId, sysField);

		return fieldNo;
	}

	/**
	 * 年月（yyMM）を取得　例：1708
	 * @return
	 */
	private static final String getCurYYMM() {
		LocalDateTime arrivalDate = LocalDateTime.now();
		try {
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyMM");
			String ret = arrivalDate.format(format);
			return ret;
		} catch (DateTimeException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
