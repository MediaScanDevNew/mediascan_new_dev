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
@Table(name = "blacklist_sites")
public class Blacklist_sites {

	/**
	 * 
	 */
	public Blacklist_sites() {
		// TODO Auto-generated constructor stub
	}

	@Id
	String id;
	String domain, created_on;
	int created_by, is_active;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public int getIs_active() {
		return is_active;
	}

	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}

}
