package org.dms.web.test.dao.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dms.web.dao.GenericDao;
import org.dms.web.entity.Examination;
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
				, null , null , null);
		genericDao.saveEntity(newPaper);		
		log.info("saved - " + newPaper);
		
		PaperStatusInfo statusInfo = new PaperStatusInfo(newPaper, false, 0, null, false, 0, null, false, 0, null, "dummy test");
		genericDao.saveEntity(statusInfo);
		log.info("saved - "+ statusInfo);
		
		PaperWorkflow workflow = new PaperWorkflow(newPaper, 0, null, false, null, "dummy test");
		genericDao.saveEntity(workflow);
		log.info("saved - "+ workflow);
		
		Examination exam = new Examination(newPaper, new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime() + 5000), 0);
		genericDao.saveEntity(exam);
		log.info("saved - "+ exam);
				
		log.info("Completed ...");
	}
	
	@Test
	@Ignore
	public void displayFewPaperDetails(){
		log.info("Displaying few details papers -");
		List<Object[]> paperList = genericDao.getList(
			  " select p.paperStoreId , p.paperTitle, e.examId "
			+ " from PaperStore p , Examination e "
			+ " where p.paperStoreId = e.paperStoreRef ", null, 1000);
		for (Object[] obj : paperList) {
			long paperId = (long) obj[0];
			String paperTitle = (String) obj[1];
			long examId = (long) obj[2];
			log.info("PaperId = "+paperId +", PaperTitle="+ paperTitle +", ExamId="+ examId);
		}
	}

}
