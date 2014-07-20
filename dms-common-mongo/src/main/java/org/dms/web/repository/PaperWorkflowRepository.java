package org.dms.web.repository;

import java.io.Serializable;

import org.dms.web.document.PaperWorkflow;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaperWorkflowRepository extends MongoRepository<PaperWorkflow, Serializable>{

}
