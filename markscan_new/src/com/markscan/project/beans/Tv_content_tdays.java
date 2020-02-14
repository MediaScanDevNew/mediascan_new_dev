package com.markscan.project.beans;

public class Tv_content_tdays {
	
	private int id;
	private int projectId;
	private String projectName;
	private String telecast_days;
	private int projecttype;
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getTelecast_days() {
		return telecast_days;
	}
	public String setTelecast_days(String telecast_days) {
		return this.telecast_days = telecast_days;
	}

	public int getProjecttype() {
		return projecttype;
	}
	public void setProjecttype(int projecttype) {
		this.projecttype = projecttype;
	}

	public Tv_content_tdays() {
		// TODO Auto-generated constructor stub
	}

}
