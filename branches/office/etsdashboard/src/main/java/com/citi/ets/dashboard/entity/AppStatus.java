package com.citi.ets.dashboard.entity;

import java.sql.Date;

/**
 * @author ak64503
 *
 */
public class AppStatus {
	
	private String app_status_id;
	private String app_id;
	private String app_compstatus;
	private String kpi_category_name;
	private Date last_update;
	private String last_updatedby;
	public String getApp_status_id() {
		return app_status_id;
	}
	public void setApp_status_id(String app_status_id) {
		this.app_status_id = app_status_id;
	}
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getApp_compstatus() {
		return app_compstatus;
	}
	public void setApp_compstatus(String app_compstatus) {
		this.app_compstatus = app_compstatus;
	}
	public String getKpi_category_name() {
		return kpi_category_name;
	}
	public void setKpi_category_name(String kpi_category_name) {
		this.kpi_category_name = kpi_category_name;
	}
	public Date getLast_update() {
		return last_update;
	}
	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
	public String getLast_updatedby() {
		return last_updatedby;
	}
	public void setLast_updatedby(String last_updatedby) {
		this.last_updatedby = last_updatedby;
	}




}