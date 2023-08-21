package com.tsrs.webedi.modular.system.jmodel;

import java.math.BigDecimal;

public class CommutRoute {
	private String from_home_line_nm; 
	private String from_home_station_nm;
	private String to_comp_line_nm;
	private String to_comp_station_nm;
	private BigDecimal travel_cost;
	
	public String getFrom_home_line_nm() {
		return from_home_line_nm;
	}
	public void setFrom_home_line_nm(String from_home_line_nm) {
		this.from_home_line_nm = from_home_line_nm;
	}
	public String getFrom_home_station_nm() {
		return from_home_station_nm;
	}
	public void setFrom_home_station_nm(String from_home_station_nm) {
		this.from_home_station_nm = from_home_station_nm;
	}
	public String getTo_comp_line_nm() {
		return to_comp_line_nm;
	}
	public void setTo_comp_line_nm(String to_comp_line_nm) {
		this.to_comp_line_nm = to_comp_line_nm;
	}
	public String getTo_comp_station_nm() {
		return to_comp_station_nm;
	}
	public void setTo_comp_station_nm(String to_comp_station_nm) {
		this.to_comp_station_nm = to_comp_station_nm;
	}
	public BigDecimal getTravel_cost() {
		return travel_cost;
	}
	public void setTravel_cost(BigDecimal travel_cost) {
		this.travel_cost = travel_cost;
	}
}
