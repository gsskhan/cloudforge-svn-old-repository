package org.dms.web.document;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="paperstatus")
public class PaperStatus {
	
	@Id
	private String id;
	
	@DBRef(lazy=true)
	private PaperStores paperStores;
	
	@Field(value="authorized")
	private boolean authorized;

	@DBRef
	private Users authorizedBy;

	@Field(value="authorizationtime")
	private Timestamp authorizationTime;

	@Field(value="approved")
	private boolean approved;

	@DBRef
	private Users approvedBy;

	@Field(value="approvaltime")
	private Timestamp approvalTime;

	@Field(value="rejected")
	private boolean rejected;

	@DBRef
	private Users rejectedBy;

	@Field(value="rejectiontime")
	private Timestamp rejectionTime;

	@Field(value="comments")
	private String comments;
	
	public PaperStatus() {
		super();
	}

	public PaperStatus(PaperStores paperStores, boolean authorized,
			Users authorizedBy, Timestamp authorizationTime, boolean approved,
			Users approvedBy, Timestamp approvalTime, boolean rejected,
			Users rejectedBy, Timestamp rejectionTime, String comments) {
		super();
		this.paperStores = paperStores;
		this.authorized = authorized;
		this.authorizedBy = authorizedBy;
		this.authorizationTime = authorizationTime;
		this.approved = approved;
		this.approvedBy = approvedBy;
		this.approvalTime = approvalTime;
		this.rejected = rejected;
		this.rejectedBy = rejectedBy;
		this.rejectionTime = rejectionTime;
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

	public boolean isAuthorized() {
		return authorized;
	}

	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}

	public Users getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(Users authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	public Timestamp getAuthorizationTime() {
		return authorizationTime;
	}

	public void setAuthorizationTime(Timestamp authorizationTime) {
		this.authorizationTime = authorizationTime;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Users getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(Users approvedBy) {
		this.approvedBy = approvedBy;
	}

	public Timestamp getApprovalTime() {
		return approvalTime;
	}

	public void setApprovalTime(Timestamp approvalTime) {
		this.approvalTime = approvalTime;
	}

	public boolean isRejected() {
		return rejected;
	}

	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}

	public Users getRejectedBy() {
		return rejectedBy;
	}

	public void setRejectedBy(Users rejectedBy) {
		this.rejectedBy = rejectedBy;
	}

	public Timestamp getRejectionTime() {
		return rejectionTime;
	}

	public void setRejectionTime(Timestamp rejectionTime) {
		this.rejectionTime = rejectionTime;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "PaperStatus [id=" + id + ", paperStores=" + paperStores
				+ ", authorized=" + authorized + ", authorizedBy="
				+ authorizedBy + ", authorizationTime=" + authorizationTime
				+ ", approved=" + approved + ", approvedBy=" + approvedBy
				+ ", approvalTime=" + approvalTime + ", rejected=" + rejected
				+ ", rejectedBy=" + rejectedBy + ", rejectionTime="
				+ rejectionTime + ", comments=" + comments + "]";
	}

}
