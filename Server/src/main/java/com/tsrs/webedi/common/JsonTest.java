package com.tsrs.webedi.common;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONAware;
import com.tsrs.webedi.common.persistence.model.RouteConfig;

public class JsonTest {

	 static class Function implements JSONAware {
		private String functionString;

		public Function() {
		}

		public Function(String functionString) {
			this.functionString = functionString;
		}

		@Override
		public String toJSONString() {
			return this.functionString;
		}

		public String getFunctionString() {
			return functionString;
		}

		public void setFunctionString(String functionString) {
			this.functionString = functionString;
		}
	}

	public static void main(String[] args) {
		RouteConfig config = new RouteConfig();

//		Component com = new Component();
//		com.setComponentStr("ApprDocInq");
		
//		config.setComponent(com);
		config.setHidden(Boolean.FALSE);
		config.setName("Test");
		
		String jsonString = JSON.toJSONString(config);
		System.out.println(jsonString);
	}
}
