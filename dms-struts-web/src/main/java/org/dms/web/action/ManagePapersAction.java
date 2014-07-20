package org.dms.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.dms.web.bo.PaperService;
import org.dms.web.exception.DmsException;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

public class ManagePapersAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 7393984910913039847L;
	private Logger log = Logger.getLogger(this.getClass());

	private String papertitle;
	
	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;

	private boolean authrequired;
	private String authorizerName;
	private String ownername;
	
	private List<String> authorizersNameList;
	private HttpServletRequest request;

	@Autowired
	private PaperService paperService;

	public String findAuthorizersList(){
		authorizersNameList = new ArrayList<String>();
		try {
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
			log.info("Uploading "+fileUploadFileName + ", content-type : "+ fileUploadContentType+" .");
			byte[] databuf = IOUtils.toByteArray(new FileInputStream(fileUpload));
			String msg =paperService.addNewPaperToStore(papertitle, databuf, ownername, authrequired, authorizerName);
			this.addActionMessage(msg);
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

}
