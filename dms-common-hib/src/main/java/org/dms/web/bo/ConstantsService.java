package org.dms.web.bo;

import java.util.List;

import org.dms.web.entity.Constants;

public interface ConstantsService {
	
	String addNewConstant(String variableName, int variableId, String value, int parentVariableId);
	List<Constants> findAllConstantsForVariable(String variable);
	List<String> findAllValuesForVariable(String variable);
	List<Constants> findAllConstants();
	String removeConstant(int constantId);
	Constants findConstants(int constantId);
	String updateConstants(int constantId, String variableName, int variableId, String value, int parentVariableId);
	byte[] buildPdfListOfAllConstants();	
	
}
