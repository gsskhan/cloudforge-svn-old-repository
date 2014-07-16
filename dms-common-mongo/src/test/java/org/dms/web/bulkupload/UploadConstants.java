package org.dms.web.bulkupload;

import org.apache.log4j.Logger;
import org.dms.web.core.RandomNumberGenerator;
import org.dms.web.document.Constants;
import org.dms.web.repository.ConstantsRepository;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

public class UploadConstants {

	
	private static Logger log = Logger.getLogger(UploadConstants.class);
	
	private static AbstractApplicationContext ctx = null;	
	
	public static void main(String[] args) {
		log.info("begin uploading constants into system ...");
		ctx = new ClassPathXmlApplicationContext("/spring/applicationContext-mongo.xml"
												,"/spring/applicationContext-core.xml");
		MongoTemplate mongoTemplate = ctx.getBean(MongoTemplate.class);
		ConstantsRepository constantsRepository = ctx.getBean(ConstantsRepository.class);
		RandomNumberGenerator randomGen = ctx.getBean(RandomNumberGenerator.class);
		log.info("spring initialized sucessfully ...");
		
		Constants constant = new Constants(randomGen.getRandomInt(),"ACCESS TYPE", 10, "ADMINISTRATOR", 0);
		constantsRepository.save(constant);
		log.info("Saved - "+ constant);		
		constant = new Constants(randomGen.getRandomInt(),"ACCESS TYPE", 10, "PRINCIPAL", 0);
		constantsRepository.save(constant);
		log.info("Saved - "+ constant);		
		constant = new Constants(randomGen.getRandomInt(),"ACCESS TYPE", 10, "TEACHER", 0);
		constantsRepository.save(constant);
		log.info("Saved - "+ constant);		
		constant = new Constants(randomGen.getRandomInt(),"ACCESS TYPE", 10, "STUDENT", 0);
		constantsRepository.save(constant);
		log.info("Saved - "+ constant);
		
		constant = new Constants(randomGen.getRandomInt(),"PAPER STATUS", 11, "CREATED", 0);
		constantsRepository.save(constant);
		log.info("Saved - "+ constant);
		constant = new Constants(randomGen.getRandomInt(),"PAPER STATUS", 11, "PENDING AUTHORIZATION", 0);
		constantsRepository.save(constant);
		log.info("Saved - "+ constant);
		constant = new Constants(randomGen.getRandomInt(),"PAPER STATUS", 11, "PENDING APPROVAL", 0);
		constantsRepository.save(constant);
		log.info("Saved - "+ constant);
		constant = new Constants(randomGen.getRandomInt(),"PAPER STATUS", 11, "APPROVED", 0);
		constantsRepository.save(constant);
		log.info("Saved - "+ constant);
		constant = new Constants(randomGen.getRandomInt(),"PAPER STATUS", 11, "AUTHORIZED", 0);
		constantsRepository.save(constant);
		log.info("Saved - "+ constant);
		constant = new Constants(randomGen.getRandomInt(),"PAPER STATUS", 11, "REJECTED", 0);
		constantsRepository.save(constant);
		log.info("Saved - "+ constant);
				
		log.info("completed ...");
	}

}
