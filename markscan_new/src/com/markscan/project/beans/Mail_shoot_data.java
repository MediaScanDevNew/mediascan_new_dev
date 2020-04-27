/**
 * 
 */
package com.markscan.project.beans;

/**
 * @author pradeep 13 jan 2017
 */
public class Mail_shoot_data {

	/**
	 * 
	 */
	public Mail_shoot_data() {
		// TODO Auto-generated constructor stub
	}

	String url_email_id_group, project_name, attachment_name, actual_hosted_site, client_address, client_name, ip_address,
			channel_name, domain, url_group, date_time, crawle_url_Id, email_type, project_type, email_address,
			clientSubject= null;


	int id, notice_id, project_id, email_module, user_id;

	
	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getClientSubject() {
		return clientSubject;
	}

	public void setClientSubject(String clientSubject) {
		this.clientSubject = clientSubject;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public String getUrl_email_id_group() {
		return url_email_id_group;
	}

	public void setUrl_email_id_group(String url_email_id_group) {
		this.url_email_id_group = url_email_id_group;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getAttachment_name() {
		return attachment_name;
	}

	public void setAttachment_name(String attachment_name) {
		this.attachment_name = attachment_name;
	}

	public String getActual_hosted_site() {
		return actual_hosted_site;
	}

	public void setActual_hosted_site(String actual_hosted_site) {
		this.actual_hosted_site = actual_hosted_site;
	}

	public String getClient_address() {
		return client_address;
	}

	public void setClient_address(String client_address) {
		this.client_address = client_address;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getUrl_group() {
		return url_group;
	}

	public void setUrl_group(String url_group) {
		this.url_group = url_group;
	}

	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}

	public String getCrawle_url_Id() {
		return crawle_url_Id;
	}

	public void setCrawle_url_Id(String crawle_url_Id) {
		this.crawle_url_Id = crawle_url_Id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getProject_type() {
		return project_type;
	}

	public void setProject_type(String project_type) {
		this.project_type = project_type;
	}

	public String getEmail_type() {
		return email_type;
	}

	public void setEmail_type(String email_type) {
		this.email_type = email_type;
	}

	public int getEmail_module() {
		return email_module;
	}

	public void setEmail_module(int email_module) {
		this.email_module = email_module;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
