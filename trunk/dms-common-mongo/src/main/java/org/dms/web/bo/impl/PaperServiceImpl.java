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
import org.dms.web.document.Users;
import org.dms.web.exception.DmsException;
import org.dms.web.exception.DmsRuntimeException;
import org.dms.web.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class PaperServiceImpl implements PaperService {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public String addNewPaperToStore(String paperTitle, byte[] uploadedData, String uploaderName, boolean assignForauthorization,
			String assignedAuthorizerName) throws DmsException {
		log.info("paper title-"+paperTitle);
		log.info("data-"+uploadedData.length);
		log.info("uploaderName-"+uploaderName);
		log.info("assignForauthorization-"+assignForauthorization);
		log.info("assignedAuthorizerName-"+assignedAuthorizerName);
		return "upload successfull.";
	}

	@Override
	public List<String> getAuthorizerListWhenCreatorIs(String creatorName) throws DmsException {
		List<String> tmpList = new ArrayList<String>();
		if (StringUtils.isEmpty(creatorName)) {
			throw new DmsException("file creator's name not received.");
		}
		Users creator = usersRepository.findOneByUsername(creatorName);
		if (creator == null) {
			throw new DmsException(creatorName +" is not a valid system user name.");
		}
		for(Users usr :mongoTemplate.find(Query.query(Criteria.where("username").ne(creatorName)
									.and("role").ne(SystemConstants.ROLE_STUDENT.getValue())), Users.class)){
			tmpList.add(usr.getUsername());
		}
		log.info("found LOVs of possible authorizers name as "+ tmpList);
		return tmpList;
	}
	
	

}
