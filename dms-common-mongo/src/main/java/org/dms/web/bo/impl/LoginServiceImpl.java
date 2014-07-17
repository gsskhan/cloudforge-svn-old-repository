package org.dms.web.bo.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dms.web.bo.LoginService;
import org.dms.web.core.SystemConstants;
import org.dms.web.document.Users;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginServiceImpl implements LoginService{
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public Users findUser(String username) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verifyAdminUser(String inputUsername, String inputPassword,
			String inputRole) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	

}
