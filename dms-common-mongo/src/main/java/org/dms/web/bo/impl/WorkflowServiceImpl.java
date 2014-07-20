package org.dms.web.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dms.web.bo.WorkflowService;
import org.dms.web.document.PaperWorkflow;
import org.dms.web.exception.DmsException;
import org.dms.web.repository.PaperWorkflowRepository;
import org.dms.web.vo.PendingActionWorkflow;
import org.springframework.beans.factory.annotation.Autowired;

public class WorkflowServiceImpl implements WorkflowService{

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private PaperWorkflowRepository workflowRepository;
	
	@Override
	public List<PendingActionWorkflow> getWorkflowsPendingActions() throws DmsException {
		log.info("searching all workflows with any pending actions.");
		List<PendingActionWorkflow> tmpList = new ArrayList<PendingActionWorkflow>();
		for (PaperWorkflow wf : workflowRepository.findByCompletedIsFalse()) {
			tmpList.add(new PendingActionWorkflow(wf.getPaperStores().getNumber(), wf.getPaperStores().getVersion()
					, wf.getPaperStores().getTitle(), wf.getId(), wf.getComments(), wf.getAssignedToUser().getUsername()));
		}log.info(tmpList.size() +" Record(s) found.");
		return tmpList;
	}

}
