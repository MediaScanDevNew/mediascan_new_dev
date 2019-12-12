package com.markscan.project.beans;

public class Project_content_tdays {
	
	private int id;
	private int projectId;
	private String projectName;
	private String archive_days;
	private int projecttype;
	
	
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
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public String getArchive_days() {
		return archive_days;
	}
	public void setArchive_days(String archive_days) {
		this.archive_days = archive_days;
	}
	public int getProjecttype() {
		return projecttype;
	}
	public void setProjecttype(int projecttype) {
		this.projecttype = projecttype;
	}
	
	
	

}
