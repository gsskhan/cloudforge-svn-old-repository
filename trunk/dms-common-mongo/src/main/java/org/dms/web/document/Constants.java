package org.dms.web.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="constants")
public class Constants {
	
	@Id
	private String id;
	
	@Field(value="constantid")
	@Indexed(name="constantidIdx",unique= true, dropDups = true)
	private int constantId;
	
	@Field(value="variablename")
	private String variableName;
	
	@Field(value="variableid")
	private int variableId;
	
	@Field(value="variablevalue")
	private String variableValue;
	
	@Field(value="parentVariableId")
	private int parentvariableid;
	
	public Constants() {
		super();
	}	

	public Constants(int constantId, String variableName, int variableId,
			String variableValue, int parentvariableid) {
		super();
		this.constantId = constantId;
		this.variableName = variableName;
		this.variableId = variableId;
		this.variableValue = variableValue;
		this.parentvariableid = parentvariableid;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getConstantId() {
		return constantId;
	}

	public void setConstantId(int constantId) {
		this.constantId = constantId;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public int getVariableId() {
		return variableId;
	}

	public void setVariableId(int variableId) {
		this.variableId = variableId;
	}

	public String getVariableValue() {
		return variableValue;
	}

	public void setVariableValue(String variableValue) {
		this.variableValue = variableValue;
	}

	public int getParentvariableid() {
		return parentvariableid;
	}

	public void setParentvariableid(int parentvariableid) {
		this.parentvariableid = parentvariableid;
	}

	@Override
	public String toString() {
		return "Constants [id=" + id + ", constantId=" + constantId
				+ ", variableName=" + variableName + ", variableId="
				+ variableId + ", variableValue=" + variableValue
				+ ", parentvariableid=" + parentvariableid + "]";
	}

}
