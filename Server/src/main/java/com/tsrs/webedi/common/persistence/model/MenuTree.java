package com.tsrs.webedi.common.persistence.model;

import java.io.Serializable;
import java.util.List;

public class MenuTree implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String label;
	private Boolean isMenu;
	private String path;
	private String displayFlg;
	private List<MenuTree> children;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<MenuTree> getChildren() {
		return children;
	}
	public void setChildren(List<MenuTree> children) {
		this.children = children;
	}
	public String getDisplayFlg() {
		return displayFlg;
	}
	public void setDisplayFlg(String displayFlg) {
		this.displayFlg = displayFlg;
	}
	public Boolean getIsMenu() {
		return isMenu;
	}
	public void setIsMenu(Boolean isMenu) {
		this.isMenu = isMenu;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

}
