package org.dms.web.constants;

public enum ViewConstants {
	
	HOME_PAGE("dms.home.def"),
	LOGIN_PAGE("login.def"),
	ADMIN_HOME_PAGE("admin.home.def");
	
	private String value;
	private ViewConstants(String viewName){
		this.value = viewName;
	}
	public String getValue(){
		return this.value;
	}	
	
}
