/**
 * 
 */
package com.pradeep.pj.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author pradeep
 *
 */
@Entity
@Table(name = "markscan_pipe")
public class Markscan_pipe {

	/**
	 * 
	 */
	public Markscan_pipe() {
		// TODO Auto-generated constructor stub
	}
@Id
	int id;
	String name, domain;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

}
