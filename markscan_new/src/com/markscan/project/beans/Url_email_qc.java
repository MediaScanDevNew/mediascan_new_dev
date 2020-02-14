/**
 * 
 */
package com.markscan.project.beans;

/**
 * @author pradeep
 *
 */
public class Url_email_qc {

	/**
	 * 05 dec 2016
	 */
	public Url_email_qc() {
		// TODO Auto-generated constructor stub
	}

	int id, url_email_id, email_qc, email_qc_by, email_send_by_email_id, email_send_by, choose_email_template, invalid,
			invalid_by,edit_by,edit , notice_id, configure_mail_id;
	String email_qc_time, email_send_time, invalid_date, edit_date,edit_field,notice_date, created_on;

	
	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public int getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}

	public int getConfigure_mail_id() {
		return configure_mail_id;
	}

	public void setConfigure_mail_id(int configure_mail_id) {
		this.configure_mail_id = configure_mail_id;
	}

	public String getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date;
	}

	public int getEdit_by() {
		return edit_by;
	}

	public void setEdit_by(int edit_by) {
		this.edit_by = edit_by;
	}

	public int getEdit() {
		return edit;
	}

	public void setEdit(int edit) {
		this.edit = edit;
	}

	public String getEdit_date() {
		return edit_date;
	}

	public void setEdit_date(String edit_date) {
		this.edit_date = edit_date;
	}

	public String getEdit_field() {
		return edit_field;
	}

	public void setEdit_field(String edit_field) {
		this.edit_field = edit_field;
	}

	public int getInvalid() {
		return invalid;
	}

	public void setInvalid(int invalid) {
		this.invalid = invalid;
	}

	public int getInvalid_by() {
		return invalid_by;
	}

	public void setInvalid_by(int invalid_by) {
		this.invalid_by = invalid_by;
	}

	public String getInvalid_date() {
		return invalid_date;
	}

	public void setInvalid_date(String invalid_date) {
		this.invalid_date = invalid_date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUrl_email_id() {
		return url_email_id;
	}

	public void setUrl_email_id(int url_email_id) {
		this.url_email_id = url_email_id;
	}

	public int getEmail_qc() {
		return email_qc;
	}

	public void setEmail_qc(int email_qc) {
		this.email_qc = email_qc;
	}

	public int getEmail_qc_by() {
		return email_qc_by;
	}

	public void setEmail_qc_by(int email_qc_by) {
		this.email_qc_by = email_qc_by;
	}

	public int getEmail_send_by_email_id() {
		return email_send_by_email_id;
	}

	public void setEmail_send_by_email_id(int email_send_by_email_id) {
		this.email_send_by_email_id = email_send_by_email_id;
	}

	public int getEmail_send_by() {
		return email_send_by;
	}

	public void setEmail_send_by(int email_send_by) {
		this.email_send_by = email_send_by;
	}

	public int getChoose_email_template() {
		return choose_email_template;
	}

	public void setChoose_email_template(int choose_email_template) {
		this.choose_email_template = choose_email_template;
	}

	public String getEmail_qc_time() {
		return email_qc_time;
	}

	public void setEmail_qc_time(String email_qc_time) {
		this.email_qc_time = email_qc_time;
	}

	public String getEmail_send_time() {
		return email_send_time;
	}

	public void setEmail_send_time(String email_send_time) {
		this.email_send_time = email_send_time;
	}

}
