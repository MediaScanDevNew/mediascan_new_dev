package com.pradeep.pj.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="crawle_url4")
public class Crawle_url4 {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	int  user_id, project_id, pipe_id, w_list;
	String crawle_url2, created_on,  domain_name, page_rank,page_no;
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
