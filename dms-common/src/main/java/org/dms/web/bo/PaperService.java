package org.dms.web.bo;

import org.dms.web.exception.DmsException;

public interface PaperService {
	
	String addNewPaperToStore(String paperTitle, byte[] uploadedData, String uploaderName) throws DmsException;

}
