package com.markscan.project.beans;

public class BlacklistTool_Data {

	public BlacklistTool_Data() {
		// TODO Auto-generated constructor stub
	}

	int id, projectid, userid, souce_link_not_found, row_in_use;
	String domain, search_keyword, infringing_link_by_date, infringing_link, source_link, created_on, updated_on, email,
			ipaddress_source, ipaddress_infringing, source_domain;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProjectid() {
		return projectid;
	}
	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getSouce_link_not_found() {
		return souce_link_not_found;
	}
	public void setSouce_link_not_found(int souce_link_not_found) {
		this.souce_link_not_found = souce_link_not_found;
	}
	public int getRow_in_use() {
		return row_in_use;
	}
	public void setRow_in_use(int row_in_use) {
		this.row_in_use = row_in_use;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getSearch_keyword() {
		return search_keyword;
	}
	public void setSearch_keyword(String search_keyword) {
		this.search_keyword = search_keyword;
	}
	public String getInfringing_link_by_date() {
		return infringing_link_by_date;
	}
	public void setInfringing_link_by_date(String infringing_link_by_date) {
		this.infringing_link_by_date = infringing_link_by_date;
	}
	public String getInfringing_link() {
		return infringing_link;
	}
	public void setInfringing_link(String infringing_link) {
		this.infringing_link = infringing_link;
	}
	public String getSource_link() {
		return source_link;
	}
	public void setSource_link(String source_link) {
		this.source_link = source_link;
	}
	public String getCreated_on() {
		return created_on;
	}
	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}
	public String getUpdated_on() {
		return updated_on;
	}
	public void setUpdated_on(String updated_on) {
		this.updated_on = updated_on;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIpaddress_source() {
		return ipaddress_source;
	}
	public void setIpaddress_source(String ipaddress_source) {
		this.ipaddress_source = ipaddress_source;
	}
	public String getIpaddress_infringing() {
		return ipaddress_infringing;
	}
	public void setIpaddress_infringing(String ipaddress_infringing) {
		this.ipaddress_infringing = ipaddress_infringing;
	}
	public String getSource_domain() {
		return source_domain;
	}
	public void setSource_domain(String source_domain) {
		this.source_domain = source_domain;
	}
	
	
}
