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

	@Field(value="authorizedby")
	private long authorizedBy;

	@Field(value="authorizationtime")
	private Timestamp authorizationTime;

	@Field(value="approved")
	private boolean approved;

	@Field(value="approvedby")
	private long approvedBy;

	@Field(value="approvaltime")
	private Timestamp approvalTime;

	@Field(value="rejected")
	private boolean rejected;

	@Field(value="rejectedby")
	private long rejectedBy;

	@Field(value="rejectiontime")
	private Timestamp rejectionTime;

	@Field(value="comments")
	private String comments;
	
	public PaperStatus() {
		super();
	}

	public PaperStatus(PaperStores paperStores, boolean authorized,
			long authorizedBy, Timestamp authorizationTime, boolean approved,
			long approvedBy, Timestamp approvalTime, boolean rejected,
			long rejectedBy, Timestamp rejectionTime, String comments) {
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

	public long getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(long authorizedBy) {
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

	public long getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(long approvedBy) {
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

	public long getRejectedBy() {
		return rejectedBy;
	}

	public void setRejectedBy(long rejectedBy) {
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
