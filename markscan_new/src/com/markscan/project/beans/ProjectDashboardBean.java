package com.markscan.project.beans;



public class ProjectDashboardBean {

	int id,closeFlag;
	String project_name, created_on, complete_on, file_attach_link, actual_hosted_site, start_date, end_date,
			client_name, name, channel_name,ttime,language,realeasingDate,property_category,current_value,archive_value;
	String telecast_days[];
	String archive_days[];
	
	public String[] getTelecast_days() {
		return telecast_days;
	}

	public void setTelecast_days(String[] telecast_days) {
		this.telecast_days = telecast_days;
	}
	
	public String[] getArchive_days() {
		return archive_days;
	}

	public void setArchive_days(String[] archive_days) {
		this.archive_days = archive_days;
	}

	public String getTtime() {
		return ttime;
	}

	public void setTtime(String ttime) {
		this.ttime = ttime;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
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

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getRealeasingDate() {
		return realeasingDate;
	}

	public void setRealeasingDate(String realeasingDate) {
		this.realeasingDate = realeasingDate;
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

	public int getCloseFlag() {
		return closeFlag;
	}

	public void setCloseFlag(int closeFlag) {
		this.closeFlag = closeFlag;
	}

	
	
	
	
	
	

}
