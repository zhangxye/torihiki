package com.tsrs.webedi.common.persistence.model;

import java.io.Serializable;

public class LabelValueBean extends java.lang.Object implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String Label;
    private String value;
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
    
}
