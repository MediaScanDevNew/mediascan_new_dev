/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

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
	
	public void saveData(Keyword_filter_extension_master t) {
		try {
			template.save(t);
		} catch (Exception e) {
			System.err.println("template save error !!!");
			e.printStackTrace();
		}
	}
}
