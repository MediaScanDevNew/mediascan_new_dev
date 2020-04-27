/**
 * 
 */
package com.markscan.project.beans;

/**
 * @author pradeep
 *
 */
public class Greylist {

	/**
	 * 
	 */
	public Greylist() {
		// TODO Auto-generated constructor stub
	}

	int id, created_by;
	String domain = null;
	String created_on = null;
	String clientId;

	
	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public String getCreated_on() {
		return created_on;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

}
