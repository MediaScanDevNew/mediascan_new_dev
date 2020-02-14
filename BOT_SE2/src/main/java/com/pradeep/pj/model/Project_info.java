package com.pradeep.pj.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project_info")
public class Project_info {

	public Project_info() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * public Project_info(int id, int client_type, String project_name) {
	 * this.client_type = client_type; this.id = id; this.project_name =
	 * project_name; }
	 */
	@Id
	int id;
	int project_type, project_complete, project_state_machine_wise, closed;
	String project_name, created_on, complete_on, file_attach_link, actual_hosted_site, start_date, end_date,
			client_type;

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

}
