package org.dms.web.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dms.web.constants.ViewConstants;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value="/upload-new-paper", method=RequestMethod.POST)
	public ModelAndView uploadNewPaper(MultipartHttpServletRequest request){
		ModelAndView mav = new ModelAndView(ViewConstants.ADMIN_HOME_PAGE.getValue());
		try {
			log.info("uploading new paper ...");				
			Iterator<String> itr = request.getFileNames();
			while (itr.hasNext()) {
				MultipartFile mpf = request.getFile(itr.next());
				log.info("recieved file name= "+mpf.getOriginalFilename()+" , file size= "+mpf.getSize()/1024 +" Kb.");
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("/tmp/"+mpf.getOriginalFilename()));
				log.info("saved to disk.");
				mav.getModel().put("statusmsg", "Paper uploaded successfully.");
				mav.getModel().put("status", "success");
			}
		} catch (IOException e) {
			mav.getModel().put("statusmsg", "Paper upload un-successfull, please try again.");
			mav.getModel().put("status", "error");
			log.error("Error in uploading new paper.", e);
		}				
		return mav;	
	}
}
