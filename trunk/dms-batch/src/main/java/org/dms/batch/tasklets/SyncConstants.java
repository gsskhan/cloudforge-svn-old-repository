package org.dms.batch.tasklets;


import org.apache.log4j.Logger;
import org.dms.web.dao.GenericDao;
import org.dms.web.entity.Constants;
import org.dms.web.repository.ConstantsRepository;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class SyncConstants implements Tasklet{
	
	private Logger log = Logger.getLogger(this.getClass());
	
	private GenericDao genericDao;
	private ConstantsRepository constantsRepository;

	public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
		this.syncConstants();
		return RepeatStatus.FINISHED;
	}
	
	private void syncConstants(){
		log.info("starting to sync constants records in mongo ...");
		for (Constants cons : genericDao.getListWhereEq(Constants.class, null, 1000000000)) {
			org.dms.web.document.Constants mongoConst = constantsRepository.findOneByVariableNameAndVariableValue(cons.getVariable(), cons.getValue());
			if(mongoConst == null){
				org.dms.web.document.Constants savedConstant 
					= constantsRepository.save(new org.dms.web.document.Constants(cons.getVariable(),cons.getVariableId(), cons.getValue(), cons.getParentVariableId()));
				log.info("added new constant to mongo with id "+ savedConstant.getId());
			} else {
				if (mongoConst.getVariableName().equals(cons.getVariable()) &&
					mongoConst.getVariableValue().equals(cons.getValue()) &&
					mongoConst.getVariableId() == cons.getVariableId() &&
					mongoConst.getParentVariableId() == cons.getParentVariableId()) {
					log.info("constant "+ cons.getVariable()+"/"+cons.getValue()+ " records same in mongo as compared to mysql.");
				} else {
					mongoConst.setVariableName(cons.getVariable());
					mongoConst.setVariableValue(cons.getValue());
					mongoConst.setVariableId(cons.getVariableId());
					mongoConst.setParentVariableId(cons.getParentVariableId());
					constantsRepository.save(mongoConst);
					log.info("constant "+ cons.getVariable()+"/"+cons.getValue()+ " records updated in mongo.");
				}
			}
		}
		log.info("starting to sync constants records in mysql ...");
		
	}

	public GenericDao getGenericDao() {
		return genericDao;
	}
	public void setGenericDao(GenericDao genericDao) {
		this.genericDao = genericDao;
	}
	public ConstantsRepository getConstantsRepository() {
		return constantsRepository;
	}
	public void setConstantsRepository(ConstantsRepository constantsRepository) {
		this.constantsRepository = constantsRepository;
	}

}
