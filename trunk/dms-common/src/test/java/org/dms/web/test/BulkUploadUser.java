package org.dms.web.test;

import org.apache.log4j.Logger;
import org.dms.web.dao.GenericDao;
import org.dms.web.entity.User;
import org.dms.web.exception.DmsException;
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
@TransactionConfiguration(transactionManager = "dbTxManager", defaultRollback = false)
@Transactional
public class BulkUploadUser {

	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private GenericDao genericDao;

	@Test
	@Ignore
	public void uploadUser() throws DmsException {
		log.info("uploading users into db ...");

		User tmpUser = new User("gs2k", "password", "Ramgarh cantt, India",
				"gs2k@dms.org", "ADMINISTRATOR");
		genericDao.saveEntity(tmpUser);
		log.info("added - " + tmpUser);

		tmpUser = new User("BheshanBedi", "bb1298", "Delhi, India",
				"bheshan@dms.org", "TEACHER");
		genericDao.saveEntity(tmpUser);
		log.info("added - " + tmpUser);

		tmpUser = new User("AmitShah", "as1298", "Jaipur, India",
				"amit.shah@dms.org", "PRINCIPAL");
		genericDao.saveEntity(tmpUser);
		log.info("added - " + tmpUser);

		tmpUser = new User("RahulKumar", "rk1298", "Kolkata, India",
				"rahul.kr@dms.org", "STUDENT");
		genericDao.saveEntity(tmpUser);
		log.info("added - " + tmpUser);

		log.info("completed ...");

	}

}
