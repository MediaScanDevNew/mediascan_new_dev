package com.pradeep.pj.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="whitelist")
public class Whitelist {
	private int clientId;
	@Id
	private int id;
	private String domain_name;
	public Whitelist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDomain_name() {
		return domain_name;
	}
	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}
}
