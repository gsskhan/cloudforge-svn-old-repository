package org.dms.web.test.dao.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.dms.web.dao.GenericDao;
import org.dms.web.entity.PaperStatusInfo;
import org.dms.web.entity.PaperStore;
import org.dms.web.entity.PaperWorkflow;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext-dbaccess.xml")
@TransactionConfiguration(transactionManager="dbTxManager", defaultRollback= true)
@Transactional
public class PaperStoreDaoTest {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private GenericDao genericDao;
	
	@Test
	@Ignore
	public void testAddPaperStore(){		
		log.info("adding one dummy paper to paper store table ...");
		long paperNumber = genericDao.getEntity("select count(paperNumber) from PaperStore", null);
		paperNumber++;	
		PaperStore newPaper = new PaperStore(paperNumber, 1
				, "dummy test paper", "just test data".getBytes()
				, 1 , new Timestamp(new Date().getTime())
				, null , null);
		genericDao.saveEntity(newPaper);		
		log.info("saved - " + newPaper);
		
		PaperStatusInfo statusInfo = new PaperStatusInfo(newPaper, false, 0, null, false, 0, null, false, 0, null, "dummy test");
		genericDao.saveEntity(statusInfo);
		log.info("saved - "+ statusInfo);
		
		PaperWorkflow workflow = new PaperWorkflow(newPaper, 0, null, false, null, "dummy test");
		genericDao.saveEntity(workflow);
		log.info("saved - "+ workflow);
	}

}