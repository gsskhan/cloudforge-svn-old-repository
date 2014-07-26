package org.dms.web.action;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.dms.web.bo.PaperService;
import org.dms.web.bo.WorkflowService;
import org.dms.web.exception.DmsException;
import org.dms.web.vo.PendingActionWorkflow;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

public class ManagePapersAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 7393984910913039847L;
	private Logger log = Logger.getLogger(this.getClass());

	// form fields for adding new paper
	private String papertitle;
	
	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;

	private boolean authrequired;
	private String authorizerName;
	private String ownername;
	
	private List<String> authorizersNameList;	
	private HttpServletRequest request;
	
	// fields to get pending workflows
	private List<PendingActionWorkflow> pendingActionWorkflowsList;
	
	// fields to get a paper data
	private InputStream fileInputStream;	

	@Autowired
	private PaperService paperService;
	@Autowired
	private WorkflowService workflowService;

	public String findAuthorizersList(){
		authorizersNameList = new ArrayList<String>();
		try {
			this.clearErrorsAndMessages();
			String creator = request.getParameter("creatorname");				
			authorizersNameList = paperService.getAuthorizerListWhenCreatorIs(creator);
		} catch (DmsException e) {
			this.addActionError("Error retriving possible authorizer name(s). Error: "+ e.getMessage());
			log.error("error in  finding list of all authorizers when creator's name is "+ownername, e);
			return ERROR;
		}
		return SUCCESS;		
	}
	
	public String addNewPaper(){
		try {
			this.clearErrorsAndMessages();
			log.info("Uploading "+fileUploadFileName + ", content-type : "+ fileUploadContentType+" .");
			byte[] databuf = IOUtils.toByteArray(new FileInputStream(fileUpload));
			String msg =paperService.addNewPaperToStore(papertitle, databuf, fileUploadFileName, ownername, authrequired, authorizerName);
			this.addActionMessage(msg);
			// Re-load list of workflows pending for action
			this.pendingActionWorkflows();
		} catch (IOException | DmsException e) {
			this.addActionError("Error in adding/processing new paper. Error: "+ e.getMessage());
			log.error("error adding new paper", e);
			return ERROR;
		} catch (Exception e) {
			this.addActionError("Error in adding/processing new paper. Error: "+ e.getMessage());
			log.error("error adding new paper", e);
			return ERROR;
		}
		this.clearfields();
		return SUCCESS;
	}
	
	public String pendingActionWorkflows(){		
		try {			
			pendingActionWorkflowsList = workflowService.getWorkflowsPendingActions();
		} catch (Exception e) {
			this.addActionError("Unable to retrive list of workflows pending actions.");
			log.error("error finding list of workflows pending actions.", e);
		}
		return SUCCESS;
	}
	
	public String findPaper(){
		try {
			this.clearErrorsAndMessages();
			String searchpaperid = request.getParameter("paperid");
			Map<String, Object> result = paperService.getPaperContents(searchpaperid);
			if (result != null) {
				fileUploadFileName =  (String) result.get("filename");
				fileInputStream = new ByteArrayInputStream((byte[]) result.get("data"));
			} else {
				this.addActionError("No paper found. Please retry with valid info.");
				log.warn("No paper found with id. ["+searchpaperid+","+"].");
				return ERROR;
			}
			log.info("found paper with id. ["+searchpaperid+"].");
		} catch (Exception e) {
			this.addActionError("Unable to download paper.");
			log.error("Error in downloading paper ", e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	private void clearfields(){
		this.papertitle ="";
		this.fileUpload = null;
		this.authrequired = true;
		this.authorizerName = "";		
	}

	// getter-setter methods	

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public String getPapertitle() {
		return papertitle;
	}
	public File getFileUpload() {
		return fileUpload;
	}
	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}
	public String getFileUploadContentType() {
		return fileUploadContentType;
	}
	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}
	public String getFileUploadFileName() {
		return fileUploadFileName;
	}
	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}
	public void setPapertitle(String papertitle) {
		this.papertitle = papertitle;
	}
	public boolean isAuthrequired() {
		return authrequired;
	}
	public void setAuthrequired(boolean authrequired) {
		this.authrequired = authrequired;
	}
	public String getAuthorizerName() {
		return authorizerName;
	}
	public void setAuthorizerName(String authorizerName) {
		this.authorizerName = authorizerName;
	}
	public String getOwnername() {
		return ownername;
	}
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	public List<String> getAuthorizersNameList() {
		return authorizersNameList;
	}
	public void setAuthorizersNameList(List<String> authorizersNameList) {
		this.authorizersNameList = authorizersNameList;
	}
	public List<PendingActionWorkflow> getPendingActionWorkflowsList() {
		return pendingActionWorkflowsList;
	}
	public void setPendingActionWorkflowsList(List<PendingActionWorkflow> pendingActionWorkflowsList) {
		this.pendingActionWorkflowsList = pendingActionWorkflowsList;
	}
	public InputStream getFileInputStream() {
		return fileInputStream;
	}
	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}
}
