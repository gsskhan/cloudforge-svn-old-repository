package org.dms.web.action;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class ManagePaperWorkflowAction extends ActionSupport {

	private static final long serialVersionUID = -1352367164303870549L;
	private Logger log = Logger.getLogger(this.getClass());
	
	private String launchworkflowId;
	private long paperNumber;
	private String paperTitle;
	private String actionText;
	private String assignedToUsername;
	private String remarks;
	
	public String processWorkflow(){
		try {
			this.clearErrorsAndMessages();
			log.info("processing workflow [workflow-"+launchworkflowId+",Number-"+paperNumber+",Title-"+paperTitle
					+",Action-"+actionText+",AssignedTo-"+assignedToUsername+",Remarks-"+remarks+"].");
			
		} catch (Exception e) {
			this.addActionError("problem processing workflow. "+e.getMessage());
			log.error("error in processing workflow", e);
		}		
		return SUCCESS;
	}
	
	// getter-setter methods
	public String getLaunchworkflowId() {
		return launchworkflowId;
	}
	public void setLaunchworkflowId(String launchworkflowId) {
		this.launchworkflowId = launchworkflowId;
	}
	public long getPaperNumber() {
		return paperNumber;
	}
	public void setPaperNumber(long paperNumber) {
		this.paperNumber = paperNumber;
	}
	public String getPaperTitle() {
		return paperTitle;
	}
	public void setPaperTitle(String paperTitle) {
		this.paperTitle = paperTitle;
	}
	public String getActionText() {
		return actionText;
	}
	public void setActionText(String actionText) {
		this.actionText = actionText;
	}
	public String getAssignedToUsername() {
		return assignedToUsername;
	}
	public void setAssignedToUsername(String assignedToUsername) {
		this.assignedToUsername = assignedToUsername;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}	
	
}
