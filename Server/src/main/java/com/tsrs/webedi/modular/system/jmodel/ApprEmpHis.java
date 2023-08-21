package com.tsrs.webedi.modular.system.jmodel;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 要員入場者
 * </p>
 *
 * @author tsrs
 * @since 2017-08-03
 */
public class ApprEmpHis  {

	private String entrance_record_k;

	private List<Map<String,Object>> entrance_record_list;
	public String getEntrance_record_k() {
		return entrance_record_k;
	}
	public void setEntrance_record_k(String entrance_record_k) {
		this.entrance_record_k = entrance_record_k;
	}
	public List<Map<String, Object>> getEntrance_record_list() {
		return entrance_record_list;
	}
	public void setEntrance_record_list(List<Map<String, Object>> entrance_record_list) {
		this.entrance_record_list = entrance_record_list;
	}
   

}
