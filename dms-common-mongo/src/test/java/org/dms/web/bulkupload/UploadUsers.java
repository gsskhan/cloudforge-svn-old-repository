package org.dms.web.bulkupload;

import org.apache.log4j.Logger;
import org.dms.web.core.RandomNumberGenerator;
import org.dms.web.document.Users;
import org.dms.web.repository.UsersRepository;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.WriteResult;

public class UploadUsers {
	
	private static Logger log = Logger.getLogger(UploadUsers.class);
	
	private static AbstractApplicationContext ctx = null;	

	public static void main(String[] args) {
		log.info("begin uploading users into system ...");
		ctx = new ClassPathXmlApplicationContext("/spring/applicationContext-mongo.xml"
												,"/spring/applicationContext-core.xml");
		MongoTemplate mongoTemplate = ctx.getBean(MongoTemplate.class);
		UsersRepository usersRepository = ctx.getBean(UsersRepository.class);
		RandomNumberGenerator randomGen = ctx.getBean(RandomNumberGenerator.class);
		log.info("spring initialized sucessfully ...");
		
		Users tmpUser = new Users(randomGen.getRandomLong(), "gs2k", "password", "Ramgarh cantt, India", "gs2k@dms.org", "ADMINISTRATOR");
		addOrUpdateUser(tmpUser,mongoTemplate);		
		tmpUser = new Users(randomGen.getRandomLong(),"BheshanBedi", "bb1298", "Delhi, India",	"bheshan@dms.org", "TEACHER");
		addOrUpdateUser(tmpUser,mongoTemplate);
		tmpUser = new Users(randomGen.getRandomLong(),"AmitShah", "as1298", "Jaipur, India", "amit.shah@dms.org", "PRINCIPAL");
		addOrUpdateUser(tmpUser,mongoTemplate);
		tmpUser = new Users(randomGen.getRandomLong(), "RahulKumar", "rk1298", "Kolkata, India", "rahul.kr@dms.org", "STUDENT");
		addOrUpdateUser(tmpUser,mongoTemplate);
		
		log.info("Total users in system = "+usersRepository.count());
		log.info("completed ...");
	}
	
	private static void addOrUpdateUser(Users toSaveUserDetails, MongoTemplate mongoTemplate ){
		log.info("Processing record, "+ toSaveUserDetails);
		Update update = new Update();
		update.set("userid", toSaveUserDetails.getUserId());
		update.set("username", toSaveUserDetails.getUsername());
		update.set("password", toSaveUserDetails.getPassword());
		update.set("address", toSaveUserDetails.getAddress());
		update.set("email", toSaveUserDetails.getEmail());
		update.set("role", toSaveUserDetails.getRole());		
		WriteResult writeResult = mongoTemplate.upsert(Query.query(Criteria.where("username").is(toSaveUserDetails.getUsername())), update, Users.class);
		if (writeResult.isUpdateOfExisting()) {
			log.info("Record updated");
		} else {
			log.info("Record inserted with id = "+ writeResult.getUpsertedId());
		}
	}

}
