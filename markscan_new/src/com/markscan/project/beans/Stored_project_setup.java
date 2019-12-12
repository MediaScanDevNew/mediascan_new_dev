/**
 * 
 */
package com.markscan.project.beans;

/**
 * @author pradeep
 *
 */
public class Stored_project_setup {

	/**
	 * 
	 */
	public Stored_project_setup() {
		// TODO Auto-generated constructor stub
	}

	int id, preority, userId, projectId, completed, deleted, pipe, google, yahoo, bing, bls ;
	String keyphrase, machine, created_on;

	

	

	public int getBls() {
		return bls;
	}

	public void setBls(int bls) {
		this.bls = bls;
	}

	public int getGoogle() {
		return google;
	}

	public void setGoogle(int google) {
		this.google = google;
	}

	public int getYahoo() {
		return yahoo;
	}

	public void setYahoo(int yahoo) {
		this.yahoo = yahoo;
	}

	public int getBing() {
		return bing;
	}

	public void setBing(int bing) {
		this.bing = bing;
	}

	public int getCompleted() {
		return completed;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPreority() {
		return preority;
	}

	public void setPreority(int preority) {
		this.preority = preority;
	}

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

	public String getKeyphrase() {
		return keyphrase;
	}

	public void setKeyphrase(String keyphrase) {
		this.keyphrase = keyphrase;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public int getPipe() {
		return pipe;
	}

	public void setPipe(int pipe) {
		this.pipe = pipe;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

}
