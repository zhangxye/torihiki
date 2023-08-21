package com.tsrs.webedi.modular.system.persistence.model;

import java.math.BigDecimal;
import java.util.List;

public class ApprdocExt extends Apprdoc {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * エビデンスリスト
	 */
	private List<Apprdocclientcontractevidence> workEvidenceList = null;
	
    /**
     * 交通費日額（往復）
     */
	private BigDecimal dayTravelCost;
    /**
     * 1ヶ月の通勤代（定期）
     */
	private BigDecimal monthPassTravelCost;
    /**
     * 支払上限
     */
	private BigDecimal payTravelCostaMax;
    /**
     * 自宅→現場（乗り換え含め交通経路記入）
     */
	private String rountDltContent;
	
	private List<Apprcommuteroutedtl> commuteRouteList = null;

	public List<Apprdocclientcontractevidence> getWorkEvidenceList() {
		return workEvidenceList;
	}

	public void setWorkEvidenceList(List<Apprdocclientcontractevidence> workEvidenceList) {
		this.workEvidenceList = workEvidenceList;
	}

	public BigDecimal getDayTravelCost() {
		return dayTravelCost;
	}

	public void setDayTravelCost(BigDecimal dayTravelCost) {
		this.dayTravelCost = dayTravelCost;
	}

	public BigDecimal getMonthPassTravelCost() {
		return monthPassTravelCost;
	}

	public void setMonthPassTravelCost(BigDecimal monthPassTravelCost) {
		this.monthPassTravelCost = monthPassTravelCost;
	}

	public BigDecimal getPayTravelCostaMax() {
		return payTravelCostaMax;
	}

	public void setPayTravelCostaMax(BigDecimal payTravelCostaMax) {
		this.payTravelCostaMax = payTravelCostaMax;
	}

	public String getRountDltContent() {
		return rountDltContent;
	}

	public void setRountDltContent(String rountDltContent) {
		this.rountDltContent = rountDltContent;
	}

	public List<Apprcommuteroutedtl> getCommuteRouteList() {
		return commuteRouteList;
	}

	public void setCommuteRouteList(List<Apprcommuteroutedtl> commuteRouteList) {
		this.commuteRouteList = commuteRouteList;
	}
	
	

}
