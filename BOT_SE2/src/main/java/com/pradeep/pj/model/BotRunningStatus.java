package com.pradeep.pj.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bot_running_status")
public class BotRunningStatus 
{
	@Id
	private int id;
	private String userName;
	private String uemail;
	private String projectName;
	//private String machine;
	private String keyphrase;
	private String machine;
	private int mailSend;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getUemail() {
		return uemail;
	}
	public void setUemail(String umail) {
		this.uemail = umail;
	}
	
	public String getKeyphrase() {
		return keyphrase;
	}
	public void setKeyphrase(String keyphrase) {
		this.keyphrase = keyphrase;
	}
	public String getMachine() {
		return machine;
	}
	public void setMachine(String machine) {
		this.machine = machine;
	}
	public int getMailSend() {
		return mailSend;
	}
	public void setMailSend(int mailSend) {
		this.mailSend = mailSend;
	}
	

}
