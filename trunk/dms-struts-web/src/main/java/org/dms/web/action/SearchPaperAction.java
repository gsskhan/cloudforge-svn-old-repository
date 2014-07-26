package org.dms.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.dms.web.bo.PaperService;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

public class SearchPaperAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = -1352367164303870549L;
	private Logger log = Logger.getLogger(this.getClass());
	
	// fields to find a paper and display contents
	private long searchpapernumber;
	private HttpServletRequest request;
	
	// service beans
	@Autowired
	private PaperService paperService;
	
	public String findPaperDetailsAll(){
		this.clearErrorsAndMessages();		
		try {
		Map<String, Object> map = paperService.getPaperDetails(searchpapernumber);
		request.setAttribute("paperstore", map.get("paperstore"));
		log.info("found paper stores - " + map.get("paperstore"));
		request.setAttribute("paperstatus",  map.get("paperstatus"));
		log.info("found paper status - " + map.get("paperstatus"));
		request.setAttribute("wflist", map.get("paperworkflowlist"));	
		log.info("found paper workflows - " + map.get("paperworkflowlist"));
		if ( map.get("paperstore") ==null || map.get("paperstatus") == null || map.get("paperworkflowlist") == null) {
			this.addActionError("No paper details found with number - "+searchpapernumber+".");
			return ERROR;
		}
		log.info("successfully set the paper details to request attribute.");
		this.addActionMessage("Found paper number " +searchpapernumber+". Please refer below for details.");
		} catch (Exception e) {
			log.error("Error in finding paper with number - "+searchpapernumber, e);
			this.addActionError("No paper details found with number - "+searchpapernumber+". Error - " + e.getMessage());
			return ERROR;
		}
		return SUCCESS;
	}

	// getter-setter methods
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;		
	}
	public long getSearchpapernumber() {
		return searchpapernumber;
	}
	public void setSearchpapernumber(long searchpapernumber) {
		this.searchpapernumber = searchpapernumber;
	}
}
