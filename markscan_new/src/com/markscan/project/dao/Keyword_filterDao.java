/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Crawle_url2;
import com.markscan.project.beans.Keyword_filter;

/**
 * @author pradeep 21 June 2016
 */
public class Keyword_filterDao {

	/**
	 * 
	 */
	public Keyword_filterDao() {
		// TODO Auto-generated constructor stub
	}
	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	public void saveData(Keyword_filter e){  
	    template.save(e);  
	}  
	
	public List viewRecord(String query)
	{
	   return template.find(query);
	}
	
	
	List<String> dashboardData = null;
}
