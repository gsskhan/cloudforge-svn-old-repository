package org.dms.web.bo.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dms.web.bo.PaperService;
import org.dms.web.bo.UserService;
import org.dms.web.core.RandomNumberGenerator;
import org.dms.web.dao.GenericDao;
import org.dms.web.entity.PaperStatusInfo;
import org.dms.web.entity.PaperStore;
import org.dms.web.entity.User;
import org.dms.web.exception.DmsException;
import org.dms.web.exception.DmsRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;

public class PaperServiceImpl implements PaperService {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private GenericDao genericDao;
	@Autowired
	private UserService userService;
	@Autowired
	private RandomNumberGenerator randumNumber;
	
	
	@Override
	public String addNewPaperToStore(String paperTitle, byte[] uploadedData, String uploaderName) throws DmsException {
		if (StringUtils.isEmpty(uploaderName)) {
			throw new DmsException("name of the user who is uploading file not recieved.");
		} else if (StringUtils.isEmpty(paperTitle)) {
			throw new DmsException("name/title of the paper not found.");
		}				
		User creator = userService.findUserHavingName(uploaderName);
		if (creator == null) {
			throw new DmsRuntimeException("No record of user having name as "+uploaderName+" found in database.");
		}
		PaperStore paper = new PaperStore(randumNumber.getRandomLong(), 0, paperTitle , uploadedData
				, creator.getUserId(), new Timestamp(new Date().getTime()), null, null, null);
		log.info("To be saved paper contents = "+ paper);
		genericDao.saveEntity(paper);
		log.info("saved new paper, id = "+ paper.getPaperStoreId());
		PaperStatusInfo statusInfo= new PaperStatusInfo(paper, false,  "new paper '"+paperTitle +"' has been uploaded.");
		genericDao.saveEntity(statusInfo);
		log.info("saved new paper status info , id = "+ statusInfo.getPaperStatusId());
		return "New paper {number "+paper.getPaperNumber()+"/version "+paper.getPaperVersion()+"} stored into database.";
	}

}
