package org.dms.web.document;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="assignment")
public class Assignment {

	@Id
	private String id;
	
	@DBRef
	private Examination exam;
	
	@DBRef
	private Users assignedToUser;
	
	@Field(value="timewhenassigned")
	private Timestamp timeWhenAssigned;
	
	@Field(value="completed")
	private boolean completed;
	
	@DBRef
	private AssignmentResponse assignmentResponse;
	
	public Assignment() {
		super();
	}

	@PersistenceConstructor
	public Assignment(Examination exam, Users assignedToUser,
			Timestamp timeWhenAssigned, boolean completed,
			AssignmentResponse assignmentResponse) {
		super();
		this.exam = exam;
		this.assignedToUser = assignedToUser;
		this.timeWhenAssigned = timeWhenAssigned;
		this.completed = completed;
		this.assignmentResponse = assignmentResponse;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Examination getExam() {
		return exam;
	}

	public void setExam(Examination exam) {
		this.exam = exam;
	}

	public Users getAssignedToUser() {
		return assignedToUser;
	}

	public void setAssignedToUser(Users assignedToUser) {
		this.assignedToUser = assignedToUser;
	}

	public Timestamp getTimeWhenAssigned() {
		return timeWhenAssigned;
	}

	public void setTimeWhenAssigned(Timestamp timeWhenAssigned) {
		this.timeWhenAssigned = timeWhenAssigned;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public AssignmentResponse getAssignmentResponse() {
		return assignmentResponse;
	}

	public void setAssignmentResponse(AssignmentResponse assignmentResponse) {
		this.assignmentResponse = assignmentResponse;
	}

	@Override
	public String toString() {
		return "Assignment [id=" + id + ", exam=" + exam + ", assignedToUser="
				+ assignedToUser + ", timeWhenAssigned=" + timeWhenAssigned
				+ ", completed=" + completed + ", assignmentResponse="
				+ assignmentResponse + "]";
	}
		

}
