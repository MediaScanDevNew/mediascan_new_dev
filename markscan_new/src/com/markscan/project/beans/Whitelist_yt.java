/**
 * 
 */
package com.markscan.project.beans;

/**
 * @author pradeep created on 23feb2018
 */
public class Whitelist_yt {

	/**
	 * 
	 */
	public Whitelist_yt() {
		// TODO Auto-generated constructor stub
	}

	int id, created_by;
	String created_on, ch_id,clientId;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getCh_id() {
		return ch_id;
	}

	public void setCh_id(String ch_id) {
		this.ch_id = ch_id;
	}

}
