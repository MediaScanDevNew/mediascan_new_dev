/**
 * 
 */
package com.pradeep.pj.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author pradeep
 *
 */
@Entity
@Table(name = "stored_project_setup1")
public class Stored_project_setup {

	/**
	 * 
	 */
	public Stored_project_setup() {
		// TODO Auto-generated constructor stub
	}

	@Id
	int id;
	//int pipe, preority, userId, projectId, completed, deleted, bing, yahoo, google;
	//String keyphrase, machine, created_on;
	
	int  userId,pipe, projectId, completed, bing, yahoo, google,duckduckgo,russiago;
	String keyphrase, created_on;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getPipe() {
		return pipe;
	}
	public void setPipe(int pipe) {
		this.pipe = pipe;
	}
	/*
	public int getPreority() {
		return preority;
	}
	public void setPreority(int preority) {
		this.preority = preority;
	}
	
	*/
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getCompleted() {
		return completed;
	}
	public void setCompleted(int completed) {
		this.completed = completed;
	}
	/*
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	*/
	public int getBing() {
		return bing;
	}
	public void setBing(int bing) {
		this.bing = bing;
	}
	public int getYahoo() {
		return yahoo;
	}
	public void setYahoo(int yahoo) {
		this.yahoo = yahoo;
	}
	public int getGoogle() {
		return google;
	}
	public void setGoogle(int google) {
		this.google = google;
	}
	public String getKeyphrase() {
		return keyphrase;
	}
	public void setKeyphrase(String keyphrase) {
		this.keyphrase = keyphrase;
	}
	/*
	public String getMachine() {
		return machine;
	}
	public void setMachine(String machine) {
		this.machine = machine;
	}
	*/
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	public int getDuckduckgo() {
		return duckduckgo;
	}
	public void setDuckduckgo(int duckduckgo) {
		this.duckduckgo = duckduckgo;
	}
	public int getRussiago() {
		return russiago;
	}
	public void setRussiago(int russiago) {
		this.russiago = russiago;
	}
	
	
	
	
	


}
