/**
 * 
 */
package com.markscan.project.beans;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author pradeep
 *
 */
public class Url_email__c {

	/**
	 * 01-Dec-2016
	 */
	public Url_email__c() {
		// TODO Auto-generated constructor stub
	}

	private ArrayList<Integer> url_email_id = null;
	private String sendEmail = null;
	private ArrayList<String> dateTime = null;
	private ArrayList<String> urlGroup = null;
	private ArrayList<String> domainGroup = null;
	private ArrayList<Integer> crawle_url_Id = null;
	private int projcetId;
	private String attachmentName = null;
	private String actualHostedSite = null;
	private Date emailSendDate = null;
	private String emailType = null;
	private String projectName = null;
	private String clientAddress = null;
	private String clientSubject = null;
	private String projectType = null;
	private String ipAddress = null;
	private String channel_name= null;
	private String clientName=null;
	

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getSendEmail() {
		return sendEmail;
	}

	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}

	public ArrayList<String> getDateTime() {
		return dateTime;
	}

	public void setDateTime(ArrayList<String> dateTime) {
		this.dateTime = dateTime;
	}

	public ArrayList<String> getUrlGroup() {
		return urlGroup;
	}

	public void setUrlGroup(ArrayList<String> urlGroup) {
		this.urlGroup = urlGroup;
	}

	public ArrayList<String> getDomainGroup() {
		return domainGroup;
	}

	public void setDomainGroup(ArrayList<String> domainGroup) {
		this.domainGroup = domainGroup;
	}

	

	public ArrayList<Integer> getUrl_email_id() {
		return url_email_id;
	}

	public void setUrl_email_id(ArrayList<Integer> url_email_id) {
		this.url_email_id = url_email_id;
	}

	public ArrayList<Integer> getCrawle_url_Id() {
		return crawle_url_Id;
	}

	public void setCrawle_url_Id(ArrayList<Integer> crawle_url_Id) {
		this.crawle_url_Id = crawle_url_Id;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public int getProjcetId() {
		return projcetId;
	}

	public void setProjcetId(int projcetId) {
		this.projcetId = projcetId;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public String getActualHostedSite() {
		return actualHostedSite;
	}

	public void setActualHostedSite(String actualHostedSite) {
		this.actualHostedSite = actualHostedSite;
	}

	public Date getEmailSendDate() {
		return emailSendDate;
	}

	public void setEmailSendDate(Date emailSendDate) {
		this.emailSendDate = emailSendDate;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getClientSubject() {
		return clientSubject;
	}

	public void setClientSubject(String clientSubject) {
		this.clientSubject = clientSubject;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}
