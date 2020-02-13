package com.pradeep.pj.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author Pentation/m on 11/02/19
 *
 */
@Entity
@Table(name="crawle_url4")
public class Crawle_url4 {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	int  user_id, project_id, pipe_id, w_list,send_crawl,link_logger,iwl_failed;
	public int getIwl_failed() {
		return iwl_failed;
	}
	public void setIwl_failed(int iwl_failed) {
		this.iwl_failed = iwl_failed;
	}
	public int getSend_crawl() {
		return send_crawl;
	}
	public void setSend_crawl(int send_crawl) {
		this.send_crawl = send_crawl;
	}
	public int getLink_logger() {
		return link_logger;
	}
	public void setLink_logger(int link_logger) {
		this.link_logger = link_logger;
	}
	public String getNote1() {
		return note1;
	}
	public void setNote1(String note1) {
		this.note1 = note1;
	}
	public String getNote2() {
		return note2;
	}
	public void setNote2(String note2) {
		this.note2 = note2;
	}
	public String getLink_logger_srclink() {
		return link_logger_srclink;
	}
	public void setLink_logger_srclink(String link_logger_srclink) {
		this.link_logger_srclink = link_logger_srclink;
	}
	public String getLink_type() {
		return link_type;
	}
	public void setLink_type(String link_type) {
		this.link_type = link_type;
	}
	public String getFilter_new() {
		return filter_new;
	}
	public void setFilter_new(String filter_new) {
		this.filter_new = filter_new;
	}
	String crawle_url2, created_on,  domain_name, page_rank,page_no,note1,note2,link_logger_srclink,link_type,filter_new;
	public Crawle_url4() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public int getPipe_id() {
		return pipe_id;
	}
	public void setPipe_id(int pipe_id) {
		this.pipe_id = pipe_id;
	}
	public int getW_list() {
		return w_list;
	}
	public void setW_list(int w_list) {
		this.w_list = w_list;
	}
	public String getCrawle_url2() {
		return crawle_url2;
	}
	public void setCrawle_url2(String crawle_url2) {
		this.crawle_url2 = crawle_url2;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	public String getDomain_name() {
		return domain_name;
	}
	public void setDomain_name(String domain_name) {
		this.domain_name = domain_name;
	}
	public String getPage_rank() {
		return page_rank;
	}
	public void setPage_rank(String page_rank) {
		this.page_rank = page_rank;
	}
	public String getPage_no() {
		return page_no;
	}
	public void setPage_no(String page_no) {
		this.page_no = page_no;
	}
}
