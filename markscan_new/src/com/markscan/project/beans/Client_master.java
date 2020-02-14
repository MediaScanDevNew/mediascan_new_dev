package com.markscan.project.beans;

public class Client_master implements Comparable<Client_master>{

	public Client_master() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * public Client_master(int id, String client_name, int project_type) {
	 * this.id = id; this.client_name = client_name; this.project_type =
	 * project_type; }
	 */

	int id;
	String client_name, created_time, client_address,client_email, client_subject=null;
	int project_type,email_module,created_by;

	
	
	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public int getEmail_module() {
		return email_module;
	}

	public void setEmail_module(int email_module) {
		this.email_module = email_module;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

	public String getClient_address() {
		return client_address;
	}

	public void setClient_address(String client_address) {
		this.client_address = client_address;
	}

	public String getClient_subject() {
		return client_subject;
	}

	public void setClient_subject(String client_subject) {
		this.client_subject = client_subject;
	}

	public int getProject_type() {
		return this.project_type;
	}

	public void setProject_type(int project_type) {
		this.project_type = project_type;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClient_name() {
		return this.client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getClient_email() {
		return client_email;
	}

	public void setClient_email(String client_email) {
		this.client_email = client_email;
	}

	@Override
	public String toString() {
		return "Client_master [client_name=" + client_name + "]";
	}

	@Override
	public int compareTo(Client_master o) {
		Client_master bn = (Client_master) o;
     return this.client_name.compareTo(bn.client_name);
		
	}

	
	
	

}
