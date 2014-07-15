package org.dms.web.repository;

import java.io.Serializable;
import java.util.List;

import org.dms.web.document.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<Users, Serializable>{
	
	List<Users> findByUsername(String username);
	
	Users findOneByUsername(String username);
}
