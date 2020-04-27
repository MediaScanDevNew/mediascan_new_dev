/**
 * 
 */
package com.markscan.project.beans;

/**
 * @author pradeep
 *
 */
public class Master_email {

	/**
	 * 29 nov 2016
	 */
	public Master_email() {
		// TODO Auto-generated constructor stub
	}

	int id,user_id;
	String created_on, email, domain_name, host_name, isp_name, reg_name, email_type, other;

	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDomain_name() {
		return domain_name;
	}

	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}

	public String getHost_name() {
		return host_name;
	}

	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}

	public String getIsp_name() {
		return isp_name;
	}

	public void setIsp_name(String isp_name) {
		this.isp_name = isp_name;
	}

	public String getReg_name() {
		return reg_name;
	}

	public void setReg_name(String reg_name) {
		this.reg_name = reg_name;
	}

	public String getEmail_type() {
		return email_type;
	}

	public void setEmail_type(String email_type) {
		this.email_type = email_type;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

}
