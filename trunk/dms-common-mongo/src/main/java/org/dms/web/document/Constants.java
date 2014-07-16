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
	
	@Field(value="parentvariableid")
	private int parentVariableId;
	
	public Constants() {
		super();
	}	

	public Constants(int constantId, String variableName, int variableId,
			String variableValue, int parentVariableId) {
		super();
		this.constantId = constantId;
		this.variableName = variableName;
		this.variableId = variableId;
		this.variableValue = variableValue;
		this.parentVariableId = parentVariableId;
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

	public int getParentVariableId() {
		return parentVariableId;
	}

	public void setParentVariableId(int parentVariableId) {
		this.parentVariableId = parentVariableId;
	}

	@Override
	public String toString() {
		return "Constants [id=" + id + ", constantId=" + constantId
				+ ", variableName=" + variableName + ", variableId="
				+ variableId + ", variableValue=" + variableValue
				+ ", parentVariableId=" + parentVariableId + "]";
	}

}
