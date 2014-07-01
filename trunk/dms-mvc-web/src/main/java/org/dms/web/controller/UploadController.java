package org.dms.web.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dms.web.bo.PaperService;
import org.dms.web.constants.ViewConstants;
import org.dms.web.exception.DmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private PaperService paperService;
	
	private String statusmsg = "";
	
	@RequestMapping(value="/upload-new-paper", method=RequestMethod.POST)
	public ModelAndView uploadNewPaper(MultipartHttpServletRequest request
			, @RequestParam(value="paper-title") String title
			, @RequestParam(value="uploader-name") String uploaderName){
		ModelAndView mav = new ModelAndView(ViewConstants.ADMIN_HOME_PAGE.getValue());
		try {
			log.info("uploading new paper for user "+uploaderName+"...");
			Iterator<String> itr = request.getFileNames();
			while (itr.hasNext()) {
				MultipartFile mpf = request.getFile(itr.next());
				log.info("recieved file name= "+mpf.getOriginalFilename()+" , file size= "+mpf.getSize()/1024 +" Kb.");
				statusmsg = paperService.addNewPaperToStore(title, mpf.getBytes(), uploaderName);
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("/tmp/"+mpf.getOriginalFilename()));
				log.info("saved to disk at /tmp/"+ mpf.getOriginalFilename());
				mav.getModel().put("statusmsg", "Paper upload successfull. "+statusmsg);
				mav.getModel().put("status", "success");
			}
		} catch (IOException | DmsException e) {
			mav.getModel().put("statusmsg", "Paper upload un-successfull, please try again. "+ e.getMessage());
			mav.getModel().put("status", "error");
			log.error("Error in uploading new paper.", e);
		}				
		return mav;	
	}
}
