package org.dms.batch.tasklets;

import org.apache.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class PrintSyncDbJobDetails implements Tasklet{
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Override
	public RepeatStatus execute(StepContribution sc, ChunkContext cc) throws Exception {
		this.printJobDetails();
		return RepeatStatus.FINISHED;
	}

	private void printJobDetails() {
		log.info("Following steps will be performed in this job - ");
		log.info("1. Extract all db records from mysql and create a dump file on local disk with its contents.");		
	}

}
