package com.tsrs.webedi.modular.system.service;

public interface INoSeqMgrService {

    /**
     * No項目の採番結果を戻す
     * @param sysCd　システムC　webedi
     * @param sysTblId テーブルID
     * @param sysField　採番フィールドID
     * @return
     */
    public String getFieldNo(String sysCd, 
			String sysTblId,
			String sysField);
}
