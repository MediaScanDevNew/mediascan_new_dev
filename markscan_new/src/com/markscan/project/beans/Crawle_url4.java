package com.markscan.project.beans;

public class Crawle_url4 {
	int id,user_id,project_id,iwl_downloaded,iwl_failed;
	public int getIwl_failed() {
		return iwl_failed;
	}
	public void setIwl_failed(int iwl_failed) {
		this.iwl_failed = iwl_failed;
	}
	public int getIwl_downloaded() {
		return iwl_downloaded;
	}
	public void setIwl_downloaded(int iwl_downloaded) {
		this.iwl_downloaded = iwl_downloaded;
	}
	String crawle_url2,created_on,projectName;
	
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
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public String getCrawle_url2() {
		return crawle_url2;
	}
	public void setCrawle_url2(String crawle_url2) {
		this.crawle_url2 = crawle_url2;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
}
