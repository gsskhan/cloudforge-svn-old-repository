package org.dms.web.bo;

import java.util.List;

import org.dms.web.exception.DmsException;
import org.dms.web.vo.PendingActionWorkflow;

public interface WorkflowService {

	List<PendingActionWorkflow> getWorkflowsPendingActions() throws DmsException;

}
