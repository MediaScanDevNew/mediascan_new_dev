package com.markscan.project.beans;


public class Project_info {

	public Project_info() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * public Project_info(int id, int client_type, String project_name) {
	 * this.client_type = client_type; this.id = id; this.project_name =
	 * project_name; }
	 */

	int id;
	int project_type, project_complete, project_state_machine_wise, closed, created_by;
	

	String project_name, created_on, complete_on, file_attach_link, actual_hosted_site, start_date, end_date,
			client_type,channel_name, language,realeasingDate,ttime,property_category,current_value,archive_value,last_updated_on;
	
	

	public String getTtime() {
		return ttime;
	}

	public void setTtime(String ttime) {
		this.ttime = ttime;
	}

	public String getRealeasingDate() {
		return realeasingDate;
	}

	public void setRealeasingDate(String realeasingDate) {
		this.realeasingDate = realeasingDate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public int getProject_type() {
		return project_type;
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

	public int getProject_complete() {
		return project_complete;
	}

	public void setProject_complete(int project_complete) {
		this.project_complete = project_complete;
	}

	public int getProject_state_machine_wise() {
		return project_state_machine_wise;
	}

	public void setProject_state_machine_wise(int project_state_machine_wise) {
		this.project_state_machine_wise = project_state_machine_wise;
	}

	public int getClosed() {
		return closed;
	}

	public void setClosed(int closed) {
		this.closed = closed;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public String getComplete_on() {
		return complete_on;
	}

	public void setComplete_on(String complete_on) {
		this.complete_on = complete_on;
	}

	public String getFile_attach_link() {
		return file_attach_link;
	}

	public void setFile_attach_link(String file_attach_link) {
		this.file_attach_link = file_attach_link;
	}

	public String getActual_hosted_site() {
		return actual_hosted_site;
	}

	public void setActual_hosted_site(String actual_hosted_site) {
		this.actual_hosted_site = actual_hosted_site;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getClient_type() {
		return client_type;
	}

	public void setClient_type(String client_type) {
		this.client_type = client_type;
	}

	public String getProject_name() {
		return this.project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	
	public String getProperty_category() {
		return property_category;
	}

	public void setProperty_category(String property_category) {
		this.property_category = property_category;
	}

	public String getCurrent_value() {
		return current_value;
	}

	public void setCurrent_value(String current_value) {
		this.current_value = current_value;
	}

	public String getArchive_value() {
		return archive_value;
	}

	public void setArchive_value(String archive_value) {
		this.archive_value = archive_value;
	}

	public String getLast_updated_on() {
		return last_updated_on;
	}

	public void setLast_updated_on(String last_updated_on) {
		this.last_updated_on = last_updated_on;
	}

	
	
	
	
	

}
