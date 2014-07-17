package org.dms.web.document;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="examination")
public class Examination {
	
	@Id
	private String id;

	@DBRef
	private PaperStores paperStores;
	
	@Field(value="starttime")
	private Timestamp startTime;

	@Field(value="endtime")
	private Timestamp endtime;

	@Field(value="examowner")
	private long examowner;

	public Examination() {
		super();
	}

	@PersistenceConstructor
	public Examination(PaperStores paperStores, Timestamp startTime,
			Timestamp endtime, long examowner) {
		super();
		this.paperStores = paperStores;
		this.startTime = startTime;
		this.endtime = endtime;
		this.examowner = examowner;
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

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndtime() {
		return endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public long getExamowner() {
		return examowner;
	}

	public void setExamowner(long examowner) {
		this.examowner = examowner;
	}

	@Override
	public String toString() {
		return "Examination [id=" + id + ", paperStores=" + paperStores
				+ ", startTime=" + startTime + ", endtime=" + endtime
				+ ", examowner=" + examowner + "]";
	}
	

}
