package org.dms.web.document;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "paperworkflow")
public class PaperWorkflow {

	@Id
	private String id;
	
	@DBRef
	private PaperStores paperStores;
	
	@DBRef
	private Users assignedToUser;

	@Field(value="assignedontime")
	private Timestamp assignedOnTime;

	@DBRef
	private Users completedByUser;

	@Field(value="completed")
	private boolean completed;
	
	@Field(value="completiontime")
	private Timestamp completionTime;

	@Field(value="comments")
	private String comments;
	
	public PaperWorkflow() {
		super();
	}

	public PaperWorkflow(PaperStores paperStores, Users assignedToUser,
			Timestamp assignedOnTime, Users completedByUser,
			boolean completed, Timestamp completionTime, String comments) {
		super();
		this.paperStores = paperStores;
		this.assignedToUser = assignedToUser;
		this.assignedOnTime = assignedOnTime;
		this.completedByUser = completedByUser;
		this.completed = completed;
		this.completionTime = completionTime;
		this.comments = comments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public PaperStores getPaperStores() {
		return paperStores;
	}

	public void setPaperStores(PaperStores paperStores) {
		this.paperStores = paperStores;
	}

	public Users getAssignedToUser() {
		return assignedToUser;
	}

	public void setAssignedToUserId(Users assignedToUser) {
		this.assignedToUser = assignedToUser;
	}

	public Timestamp getAssignedOnTime() {
		return assignedOnTime;
	}

	public void setAssignedOnTime(Timestamp assignedOnTime) {
		this.assignedOnTime = assignedOnTime;
	}

	public Users getCompletedByUser() {
		return completedByUser;
	}

	public void setCompletedByUserId(Users completedByUser) {
		this.completedByUser = completedByUser;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Timestamp getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(Timestamp completionTime) {
		this.completionTime = completionTime;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "PaperWorkflow [id=" + id + ", paperStores=" + paperStores
				+ ", assignedToUser=" + assignedToUser
				+ ", assignedOnTime=" + assignedOnTime + ", completedByUserId="
				+ completedByUser + ", completed=" + completed
				+ ", completionTime=" + completionTime + ", comments="
				+ comments + "]";
	}	

}
