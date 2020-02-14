/**
 * 
 */
package com.markscan.project.beans;

/**
 * @author pradeep
 *
 */
public class BotQueryStatus {

	/**
	 * 
	 */
	public BotQueryStatus() {
		// TODO Auto-generated constructor stub
	}

	int id, preority, userId, projectId, pipe;
	String keyphrase, machine, created_on, google, yahoo, bing, userName, completed, deleted;

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

	public int getPipe() {
		return pipe;
	}

	public void setPipe(int pipe) {
		this.pipe = pipe;
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

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getGoogle() {
		return google;
	}

	public void setGoogle(String google) {
		this.google = google;
	}

	public String getYahoo() {
		return yahoo;
	}

	public void setYahoo(String yahoo) {
		this.yahoo = yahoo;
	}

	public String getBing() {
		return bing;
	}

	public void setBing(String bing) {
		this.bing = bing;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCompleted() {
		return completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}
