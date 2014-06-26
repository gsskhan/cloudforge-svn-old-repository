package org.dms.web.test;

import org.apache.log4j.Logger;
import org.dms.web.dao.GenericDao;
import org.dms.web.entity.Constants;
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
public class BulkUploadConstants {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private GenericDao genericDao;
	
	@Test
	@Ignore
	public void uploadConstants(){
		log.info("uploading constants into db ...");
		
		Constants admin = new Constants("ACCESS TYPE", 10, "ADMINISTRATOR", 0);
		genericDao.saveEntity(admin);
		log.info("Saved - "+ admin);
		
		Constants principal = new Constants("ACCESS TYPE", 10, "PRINCIPAL", 0);
		genericDao.saveEntity(principal);
		log.info("Saved - "+ principal);
		
		Constants teacher = new Constants("ACCESS TYPE", 10, "TEACHER", 0);
		genericDao.saveEntity(teacher);
		log.info("Saved - "+ teacher);
		
		Constants student = new Constants("ACCESS TYPE", 10, "STUDENT", 0);
		genericDao.saveEntity(student);
		log.info("Saved - "+ student);
		
		log.info("completed ...");
	}	
	

}
