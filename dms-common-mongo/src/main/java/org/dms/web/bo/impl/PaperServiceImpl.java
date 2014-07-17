package org.dms.web.bo.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dms.web.bo.PaperService;
import org.dms.web.bo.UserService;
import org.dms.web.core.RandomNumberGenerator;
import org.dms.web.core.SystemConstants;
import org.dms.web.exception.DmsException;
import org.dms.web.exception.DmsRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;

public class PaperServiceImpl implements PaperService {

	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public String addNewPaperToStore(String paperTitle, byte[] uploadedData,
			String uploaderName, boolean assignForauthorization,
			String assignedAuthorizerName) throws DmsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAuthorizerListWhenCreatorIs(String creatorName)
			throws DmsException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
