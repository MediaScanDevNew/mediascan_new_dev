/**
 * 
 */
package com.markscan.project.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Blacklist_sites;
import com.markscan.project.beans.Crawle_url2;
import com.markscan.project.beans.Keyword_filter_extension_master;

/**
 * @author pradeep
 *
 */
public class Keyword_filter_extension_masterDao {

	/**
	 * 
	 */
	public Keyword_filter_extension_masterDao() {
		// TODO Auto-generated constructor stub
	}

	
	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	public List<String> viewData(Keyword_filter_extension_master cc, String query) {
		dashboardData = (List<String>) template.find(query);
		System.out.println(dashboardData.size());
		return dashboardData;
	}

	public List<Keyword_filter_extension_master> viewRecord(String query) {
//		template.setMaxResults(x);
		List<Keyword_filter_extension_master> dd = new ArrayList<>();
		dd = (List<Keyword_filter_extension_master>) template.find(query);
		System.out.println("oooo   "+dd.size());
		return dd;
	}
	
	
	List<String> dashboardData = null;
	public Object dao;
	

	
	public String saveData(Keyword_filter_extension_master t) {
		String pj = null;
		try {
			Serializable ss = template.save(t);

			pj = ss.toString();
			// System.out.println("=== ss value----"+ss.toString());
			return pj;
		} catch (Exception e) {
			// System.err.println("template save error !!!");
			e.printStackTrace();
			return pj;
		}
	}
	
	public int customUpdateProject(String query) { // for qc
		return template.bulkUpdate(query);

	}
	
	int id;
	int isactive;
	int projectType;
	String keyphrase, created_on;

	public int getId() {
		return id;
	}

	public int getIsactive() {
		return isactive;
	}

	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}

	public String getCreated_on() {
		return created_on;
	}

	public void setCreated_on(String created_on) {
		this.created_on = created_on;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProjectType() {
		return projectType;
	}

	public void setProjectType(int projectType) {
		this.projectType = projectType;
	}

	public String getKeyphrase() {
		return keyphrase;
	}

	public void setKeyphrase(String keyphrase) {
		this.keyphrase = keyphrase;
	}
	
}
