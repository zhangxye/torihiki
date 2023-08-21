package com.tsrs.webedi.modular.system.persistence.model;

import java.util.List;


/**
 * <p>
 * 稟議書申請HDR情報
 * </p>
 *
 * @author tsrs
 * @since 2017-09-01
 */
public class ApprapplyExt extends Apprapplyhdr {

    private static final long serialVersionUID = 1L;


    private String apprDocNo;
    
	/**
	 * エビデンスリスト
	 */
	private List<Apprapplydtl> apprDocApplyDtl = null;


	public List<Apprapplydtl> getApprDocApplyDtl() {
		return apprDocApplyDtl;
	}


	public void setApprDocApplyDtl(List<Apprapplydtl> apprDocApplyDtl) {
		this.apprDocApplyDtl = apprDocApplyDtl;
	}


	public String getApprDocNo() {
		return apprDocNo;
	}


	public void setApprDocNo(String apprDocNo) {
		this.apprDocNo = apprDocNo;
	}	

}
