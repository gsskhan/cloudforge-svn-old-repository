package org.dms.web.bo.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dms.web.bo.ConstantsService;
import org.dms.web.core.PdfUtil;
import org.dms.web.document.Constants;
import org.dms.web.exception.DmsRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class ConstantsServiceImpl implements ConstantsService {
	
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public String addNewConstant(String variableName, int variableId,
			String value, int parentVariableId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Constants> findAllConstantsForVariable(String variable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> findAllValuesForVariable(String variable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Constants> findAllConstants() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String removeConstant(int constantId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Constants findConstants(int constantId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateConstants(int constantId, String variableName,
			int variableId, String value, int parentVariableId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] buildPdfListOfAllConstants() {
		// TODO Auto-generated method stub
		return null;
	}
	
	


}
