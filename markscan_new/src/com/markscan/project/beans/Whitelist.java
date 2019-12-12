/**
 * 
 */
package com.markscan.project.beans;

/**
 * @author pradeep
 *
 */
public class Whitelist {

	/**
	 * 
	 */
	public Whitelist() {
		// TODO Auto-generated constructor stub
	}

	int id, project_type, created_by;
	String created_on = null;
	String domain_name = null;
	String clientId =null;
	String platformTypeId = null;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getPlatformTypeId() {
		return platformTypeId;
	}
	public void setPlatformTypeId(String platformTypeId) {
		this.platformTypeId = platformTypeId;
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
	public String getDomain_name() {
		return domain_name;
	}
	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}

}
