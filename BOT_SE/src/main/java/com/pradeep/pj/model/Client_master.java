package com.pradeep.pj.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.springframework.data.annotation.Id;

@Entity
@Table(name = "client_master")
public class Client_master {
	@Id
	private int id;
	private String client_name;
	private int project_type;
	private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public int getProject_type() {
		return project_type;
	}
	public void setProject_type(int project_type) {
		this.project_type = project_type;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
