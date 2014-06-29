package org.dms.web.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping(value="/upload")
public class UploadController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@ResponseBody
	@RequestMapping(value="/newpaper")
	public String uploadNewPaper(MultipartHttpServletRequest request){
		try {
			log.info("uploading new paper ...");		
			Iterator<String> itr = request.getFileNames();
			while (itr.hasNext()) {
				MultipartFile mpf = request.getFile(itr.next());
				log.info("recieved file name= "+mpf.getOriginalFilename()+" , file size= "+mpf.getSize()/1024 +" Kb.");
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream("/tmp/"+mpf.getOriginalFilename()));
				log.info("saved to disk.");
			}
			return "{success}";
		} catch (IOException e) {
			log.error("Error in uploading new paper.", e);
		}				
		return "{failed}";		
	}
}
