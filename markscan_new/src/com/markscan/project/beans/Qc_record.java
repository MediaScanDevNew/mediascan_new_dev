/**
 * 
 */
package com.markscan.project.beans;

/**
 * @author pradeep
 *
 */
public class Qc_record {

	/**
	 * 
	 */
	public Qc_record() {
		// TODO Auto-generated constructor stub
	}

	int id, crawl_url2_id, project_id, user_id_pm, user_id_ops, is_valid, del_by, m_perform, m_id, gt_perform,
			gt_user_id, browser_version, client_id, bypass_link, link_logger, take_down, take_down_by, ss_uploader_id;
	String pmqc_time, opsqc_time, is_valid_time, is_valid_reason, del_time, del_reason, m_date, gt_date, browser_name,
			created_on, take_down_time, ss_upload_time, ss_file_path;

	public int getSs_uploader_id() {
		return ss_uploader_id;
	}

	public void setSs_uploader_id(int ss_uploader_id) {
		this.ss_uploader_id = ss_uploader_id;
	}

	public String getSs_upload_time() {
		return ss_upload_time;
	}

	public void setSs_upload_time(String ss_upload_time) {
		this.ss_upload_time = ss_upload_time;
	}

	public String getSs_file_path() {
		return ss_file_path;
	}

	public void setSs_file_path(String ss_file_path) {
		this.ss_file_path = ss_file_path;
	}

	public int getTake_down() {
		return take_down;
	}

	public void setTake_down(int take_down) {
		this.take_down = take_down;
	}

	public int getTake_down_by() {
		return take_down_by;
	}

	public void setTake_down_by(int take_down_by) {
		this.take_down_by = take_down_by;
	}

	public String getTake_down_time() {
		return take_down_time;
	}

	public void setTake_down_time(String take_down_time) {
		this.take_down_time = take_down_time;
	}

	public int getBypass_link() {
		return bypass_link;
	}

	public void setBypass_link(int bypass_link) {
		this.bypass_link = bypass_link;
	}

	public int getLink_logger() {
		return link_logger;
	}

	public void setLink_logger(int link_logger) {
		this.link_logger = link_logger;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public int getBrowser_version() {
		return browser_version;
	}

	public void setBrowser_version(int browser_version) {
		this.browser_version = browser_version;
	}

	public String getBrowser_name() {
		return browser_name;
	}

	public void setBrowser_name(String browser_name) {
		this.browser_name = browser_name;
	}

	public int getGt_perform() {
		return gt_perform;
	}

	public void setGt_perform(int gt_perform) {
		this.gt_perform = gt_perform;
	}

	public int getGt_user_id() {
		return gt_user_id;
	}

	public void setGt_user_id(int gt_user_id) {
		this.gt_user_id = gt_user_id;
	}

	public String getGt_date() {
		return gt_date;
	}

	public void setGt_date(String gt_date) {
		this.gt_date = gt_date;
	}

	public int getM_perform() {
		return m_perform;
	}

	public void setM_perform(int m_perform) {
		this.m_perform = m_perform;
	}

	public int getM_id() {
		return m_id;
	}

	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	public String getM_date() {
		return m_date;
	}

	public void setM_date(String m_date) {
		this.m_date = m_date;
	}

	public int getDel_by() {
		return del_by;
	}

	public void setDel_by(int del_by) {
		this.del_by = del_by;
	}

	public String getIs_valid_time() {
		return is_valid_time;
	}

	public void setIs_valid_time(String is_valid_time) {
		this.is_valid_time = is_valid_time;
	}

	public String getIs_valid_reason() {
		return is_valid_reason;
	}

	public void setIs_valid_reason(String is_valid_reason) {
		this.is_valid_reason = is_valid_reason;
	}

	public String getDel_time() {
		return del_time;
	}

	public void setDel_time(String del_time) {
		this.del_time = del_time;
	}

	public String getDel_reason() {
		return del_reason;
	}

	public void setDel_reason(String del_reason) {
		this.del_reason = del_reason;
	}

	public int getIs_valid() {
		return is_valid;
	}

	public void setIs_valid(int is_valid) {
		this.is_valid = is_valid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCrawl_url2_id() {
		return crawl_url2_id;
	}

	public void setCrawl_url2_id(int crawl_url2_id) {
		this.crawl_url2_id = crawl_url2_id;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public int getUser_id_pm() {
		return user_id_pm;
	}

	public void setUser_id_pm(int user_id_pm) {
		this.user_id_pm = user_id_pm;
	}

	public int getUser_id_ops() {
		return user_id_ops;
	}

	public void setUser_id_ops(int user_id_ops) {
		this.user_id_ops = user_id_ops;
	}

	public String getPmqc_time() {
		return pmqc_time;
	}

	public void setPmqc_time(String pmqc_time) {
		this.pmqc_time = pmqc_time;
	}

	public String getOpsqc_time() {
		return opsqc_time;
	}

	public void setOpsqc_time(String opsqc_time) {
		this.opsqc_time = opsqc_time;
	}

}
