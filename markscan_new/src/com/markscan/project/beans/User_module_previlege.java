/**
 * 
 */
package com.markscan.project.beans;

/**
 * @author pradeep
 *
 */
public class User_module_previlege {

	/**
	 * 
	 */
	public User_module_previlege() {
		// TODO Auto-generated constructor stub
	}

	int id, user_id, module_id, created_by;
	String created_on = null;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getModule_id() {
		return module_id;
	}
	public void setModule_id(int module_id) {
		this.module_id = module_id;
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
	
}
