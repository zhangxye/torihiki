/**
 * VUE RouterのEntity
 * Menuデータを生成用
 */
package com.tsrs.webedi.common.persistence.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author jin-qk
 *
 */
public class RouteConfig implements Serializable{
	
	private static final long serialVersionUID = 1L;

	String menuId;
	
	String subMenuId;
	
	String pgId;

	String path;
	
	String component;
	
	String name ;
	
	String icon;
	
	String redirect;
	
	String props;
	
	Boolean hidden;
	
	List<RouteConfig> children;

	
	
	public String getPgId() {
		return pgId;
	}

	public void setPgId(String pgId) {
		this.pgId = pgId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	

	public String getSubMenuId() {
		return subMenuId;
	}

	public void setSubMenuId(String subMenuId) {
		this.subMenuId = subMenuId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String componet) {
		this.component = componet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}

	public String getProps() {
		return props;
	}

	public void setProps(String props) {
		this.props = props;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public List<RouteConfig> getChildren() {
		return children;
	}

	public void setChildren(List<RouteConfig> children) {
		this.children = children;
	}
}
