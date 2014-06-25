package org.dms.web.core;

public enum SystemConstants {
	
	ROLE_ADMIN("ADMINISTRATOR"),
	ROLE_STUDENT("STUDENT"),; 
	
	private String value;
	
	SystemConstants(String val){
		this.value = val;
	}
	
	public String getValue(){
		return value;
	}
}
