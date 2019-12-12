/**
 * 
 */
package com.markscan.project.beans;

/**
 * @author pradeep
 *
 */
public class Whitelist_instagram {

	/**
	 * 
	 */
	public Whitelist_instagram() {
		// TODO Auto-generated constructor stub
	}

	int id, project_type, created_by;
	String created_on = null;
	String taken_by_id = null;
	String insta_link = null;
	String clientId =null;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getInsta_link() {
		return insta_link;
	}

	public void setInsta_link(String insta_link) {
		this.insta_link = insta_link;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProject_type() {
		return project_type;
	}

	public void setProject_type(int project_type) {
		this.project_type = project_type;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getTaken_by_id() {
		return taken_by_id;
	}

	public void setTaken_by_id(String taken_by_id) {
		this.taken_by_id = taken_by_id;
	}

}
