package org.dms.web.repository;

import java.io.Serializable;

import org.dms.web.document.Constants;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConstantsRepository extends MongoRepository<Constants, Serializable>{
	
	Constants findOneByVariableNameAndVariableValue(String variableName, String variableValue);

}
