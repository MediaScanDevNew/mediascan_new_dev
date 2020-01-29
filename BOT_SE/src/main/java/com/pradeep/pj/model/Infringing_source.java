/**
 * 
 */
package com.pradeep.pj.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author pradeep
 *
 */
@Entity
@Table(name = "BL_infringing_source")
public class Infringing_source {

	/**
	 * 
	 */
	public Infringing_source() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	private String infringing_link, ipaddress;
	private String source_link;
	private int userid, souce_link_not_found, row_in_use, projectid, qc, data_move;
	private String domain, search_keyword, infringing_link_by_date, infi_time, source_time, email, i_ipaddress,
			source_domain;

	public int getQc() {
		return qc;
	}

	public void setQc(int qc) {
		this.qc = qc;
	}

	public int getData_move() {
		return data_move;
	}

	public void setData_move(int data_move) {
		this.data_move = data_move;
	}

	public String getSource_domain() {
		return this.source_domain;
	}

	public void setSource_domain(String source_domain) {
		this.source_domain = source_domain;
	}

	public String getI_ipaddress() {
		return this.i_ipaddress;
	}

	public void setI_ipaddress(String i_ipaddress) {
		this.i_ipaddress = i_ipaddress;
	}

	public String getIpaddress() {
		return this.ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public int getRow_in_use() {
		return row_in_use;
	}

	public void setRow_in_use(int row_in_use) {
		this.row_in_use = row_in_use;
	}

	public int getSouce_link_not_found() {
		return souce_link_not_found;
	}

	public void setSouce_link_not_found(int souce_link_not_found) {
		this.souce_link_not_found = souce_link_not_found;
	}

	public String getInfi_time() {
		return infi_time;
	}

	public void setInfi_time(String infi_time) {
		this.infi_time = infi_time;
	}

	public String getSource_time() {
		return source_time;
	}

	public void setSource_time(String source_time) {
		this.source_time = source_time;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInfringing_link() {
		return infringing_link;
	}

	public void setInfringing_link(String infringing_link) {
		this.infringing_link = infringing_link;
	}

	public String getInfringing_link_by_date() {
		return infringing_link_by_date;
	}

	public void setInfringing_link_by_date(String infringing_link_by_date) {
		this.infringing_link_by_date = infringing_link_by_date;
	}

	public String getSource_link() {
		return source_link;
	}

	public void setSource_link(String source_link) {
		this.source_link = source_link;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

}
