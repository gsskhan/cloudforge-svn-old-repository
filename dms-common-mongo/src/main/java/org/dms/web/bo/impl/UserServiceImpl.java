package org.dms.web.bo.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dms.web.bo.UserService;
import org.dms.web.core.PdfUtil;
import org.dms.web.document.Users;
import org.dms.web.exception.DmsException;
import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class UserServiceImpl implements UserService{
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public String addNewUser(String username, String password, String address,
			String email, String role) throws DmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteUserHavingId(String userId) throws DmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteUserHavingName(String username) throws DmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateUser(long userid, String username, String password,
			String address, String email, String role) throws DmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users findUserHavingId(String userId) throws DmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users findUserHavingName(String username) throws DmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> findUsersWithNameLike(String uname) throws DmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> allUsersNameList() throws DmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> allUsersList() throws DmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] buildPdfListOfAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
