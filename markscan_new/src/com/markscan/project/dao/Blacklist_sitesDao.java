/**
 * 
 */
package com.markscan.project.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Blacklist_sites;

/**
 * @author pradeep
 *
 */
public class Blacklist_sitesDao {

	/**
	 * 
	 */
	public Blacklist_sitesDao() {
		// TODO Auto-generated constructor stub
	}

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public List<Blacklist_sites> viewRecord(String query) {
		// template.setMaxResults(x);
		List<Blacklist_sites> dd = new ArrayList<>();
		dd = (List<Blacklist_sites>) template.find(query);
		// System.out.println("oooo size Client_masterDao " + dd.size());
		return dd;
	}
	
	
	
	public List<Blacklist_sites> viewRecord01(String query, int x) {
		template.setMaxResults(x);
		List<Blacklist_sites> dd = new ArrayList<>();
		dd = (List<Blacklist_sites>) template.find(query);
		// System.out.println("oooo size Client_masterDao " + dd.size());
		return dd;
	}

	public String saveData(Blacklist_sites t) {
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

/*	public void customUpdateProject(String query) { // for qc
		template.bulkUpdate(query);

	}*/
	
	public int customUpdateProject(String query) { // for qc
		return template.bulkUpdate(query);

	}	

	public void CustomDelete(String entityName, String entity) {
		template.delete(entityName, entity);
	}	
	
	int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getIs_active() {
		return is_active;
	}

	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}

	public int getProject_type() {
		return project_type;
	}

	public void setProject_type(int project_type) {
		this.project_type = project_type;
	}

	String domain;
	int is_active;
	int project_type;


}
