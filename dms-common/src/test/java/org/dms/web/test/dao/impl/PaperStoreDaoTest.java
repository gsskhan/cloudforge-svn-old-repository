package org.dms.web.test.dao.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.log4j.Logger;
import org.dms.web.dao.GenericDao;
import org.dms.web.entity.PaperStore;
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
		long paperNumber = genericDao.getEntity("select max(paperNumber) from PaperStore", null);
		paperNumber++;
		PaperStore newPaper = new PaperStore(paperNumber, 1, "dummy test paper", "just test data".getBytes(), 1, new Timestamp(new Date().getTime()));
		log.info("Before saving - "+ newPaper);
		genericDao.saveEntity(newPaper);
		log.info("After saving - "+ newPaper);
		log.info("completed ...");
	}

}
