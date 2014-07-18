package org.dms.web.bo.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dms.web.bo.ConstantsService;
import org.dms.web.core.PdfUtil;
import org.dms.web.document.Constants;
import org.dms.web.exception.DmsRuntimeException;
import org.dms.web.repository.ConstantsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public class ConstantsServiceImpl implements ConstantsService {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ConstantsRepository constantsRepository;

	@Override
	public String addNewConstant(String variableName, int variableId, String value, int parentVariableId) {
		Constants cons = new Constants(variableName, variableId, value, parentVariableId);
		if (StringUtils.isEmpty(variableName) || StringUtils.isEmpty(value)) {
			return "Either/both of variable name{"+variableName+"}, value{"+value+"} is null or empty.";
		}
		if(constantsRepository.findOneByVariableNameAndVariableValue(variableName, value) != null){
			return "Pair of variable name{"+variableName+"}, value{"+value+"} already exist.";
		}
		cons = constantsRepository.save(cons);
		log.info("1 record saved to db. "+cons);
		return "Added 1 record successfully.";
	}

	@Override
	public List<Constants> findAllConstantsForVariable(String variable) {
		return constantsRepository.findByVariableNameRegex(variable);
	}

	@Override
	public List<String> findAllValuesForVariable(String variable) {
		List<String> tmpList = new ArrayList<String>();
		for (Constants cons : constantsRepository.findDistinctVariableValueByVariableName(variable)) {
			tmpList.add(cons.getVariableValue());
		}
		log.info("found LOVs as "+tmpList+" from db for variable - "+ variable);
		return tmpList;
	}	

	@Override
	public List<String> findAllVariableNames() {
		Set<String> tmpList = new HashSet<String>();
		for(Constants cons : constantsRepository.findAll()){
			tmpList.add(cons.getVariableName());
		}
		log.info("found LOVs of variable names "+tmpList+" from db");
		return new ArrayList<String>(tmpList);
	}
	

	@Override
	public List<Constants> findAllConstants() {
		return constantsRepository.findAll();
	}

	@Override
	public String removeConstant(String constantId) {
		long num =constantsRepository.deleteConstantsById(constantId);
		return "Deleted "+num +" record(s).";
	}

	@Override
	public Constants findConstants(String constantId) {
		return constantsRepository.findOneById(constantId);
	}

	@Override
	public String updateConstants(String constantId, String variableName, int variableId, String value, int parentVariableId) {
		Constants cons = new Constants(variableName, variableId, value, parentVariableId);
		cons.setId(constantId);
		cons = constantsRepository.save(cons);
		log.info("Updated constant record, final contents as in db - "+ cons);
		return "Updated constant having id as "+ cons.getId();
	}

	@Override
	public byte[] buildPdfListOfAllConstants() {
		// TODO Auto-generated method stub
		return null;
	}


}
