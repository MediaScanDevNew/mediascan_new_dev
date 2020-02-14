/**
 * 
 */
package com.markscan.project.beans;

/**
 * @author pradeep
 *
 */
public class Module_wise_mail_send {

	/**
	 * 
	 */
	public Module_wise_mail_send() {
		// TODO Auto-generated constructor stub
	}
int id, module, email_type,client_type;
String email_id;

public int getClient_type() {
	return client_type;
}
public void setClient_type(int client_type) {
	this.client_type = client_type;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getModule() {
	return module;
}
public void setModule(int module) {
	this.module = module;
}
public int getEmail_type() {
	return email_type;
}
public void setEmail_type(int email_type) {
	this.email_type = email_type;
}
public String getEmail_id() {
	return email_id;
}
public void setEmail_id(String email_id) {
	this.email_id = email_id;
} 

}
