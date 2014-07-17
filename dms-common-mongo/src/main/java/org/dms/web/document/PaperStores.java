package org.dms.web.document;

import java.sql.Timestamp;
import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="paperstores")
@CompoundIndexes(value={
		@CompoundIndex(def="{'number':1,'version':1}", name="paperNameAndVersionIdx", unique=true, dropDups=true)
})
public class PaperStores {

	@Id
	private String id;
	
	@Field(value="number")
	@Indexed(name="numberIdx",dropDups=true)
	private long number;
	
	@Field(value="version")
	private int version;
	
	@Field(value="title")
	private String title;

	@Field(value="data")
	private byte[] data;

	@Field(value="createdbyuserid")
	private long createdByUserId;

	@Field(value="creationtime")
	private Timestamp creationTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public long getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(long createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	@Override
	public String toString() {
		return "PaperStores [id=" + id + ", number=" + number + ", version="
				+ version + ", title=" + title + ", data record byte length="
				+ Arrays.toString(data).length() + ", createdByUserId="
				+ createdByUserId + ", creationTime=" + creationTime + "]";
	}		
	
	
}
