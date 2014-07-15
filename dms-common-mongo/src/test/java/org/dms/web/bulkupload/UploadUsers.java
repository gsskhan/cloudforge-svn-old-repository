package org.dms.web.bulkupload;

import org.apache.log4j.Logger;
import org.dms.web.core.RandomNumberGenerator;
import org.dms.web.document.Users;
import org.dms.web.repository.UsersRepository;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UploadUsers {
	
	private static Logger log = Logger.getLogger(UploadUsers.class);
	
	private static AbstractApplicationContext ctx = null;

	public static void main(String[] args) {
		log.info("begin uploading users into system ...");
		ctx = new ClassPathXmlApplicationContext("/spring/applicationContext-mongo.xml"
												,"/spring/applicationContext-common.xml");
		UsersRepository usersRepository = ctx.getBean(UsersRepository.class);
		RandomNumberGenerator randomGen = ctx.getBean(RandomNumberGenerator.class);
		log.info("spring initialized sucessfully ...");
		
		Users tmpUser = new Users(randomGen.getRandomLong(), "gs2k", "password", "Ramgarh cantt, India", "gs2k@dms.org", "ADMINISTRATOR");
		usersRepository.save(tmpUser);
		log.info("added - " + tmpUser);
		tmpUser = new Users(randomGen.getRandomLong(),"BheshanBedi", "bb1298", "Delhi, India",	"bheshan@dms.org", "TEACHER");
		usersRepository.save(tmpUser);
		log.info("added - " + tmpUser);
		tmpUser = new Users(randomGen.getRandomLong(),"AmitShah", "as1298", "Jaipur, India", "amit.shah@dms.org", "PRINCIPAL");
		usersRepository.save(tmpUser);
		log.info("added - " + tmpUser);
		tmpUser = new Users(randomGen.getRandomLong(), "RahulKumar", "rk1298", "Kolkata, India", "rahul.kr@dms.org", "STUDENT");
		usersRepository.save(tmpUser);
		log.info("added - " + tmpUser);
		
		log.info("Total users in syatem = "+usersRepository.count());
		log.info("completed ...");
	}

}
