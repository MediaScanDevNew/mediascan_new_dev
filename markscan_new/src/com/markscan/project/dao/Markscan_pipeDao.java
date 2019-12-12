/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Markscan_pipe;

/**
 * @author pradeep
 *
 */
public class Markscan_pipeDao {

	/**
	 * 
	 */
	public Markscan_pipeDao() {
		// TODO Auto-generated constructor stub
	}

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	public List<Markscan_pipe> viewRecord(String query) {
//		template.setMaxResults(x);
		List<Markscan_pipe> dd = new ArrayList<>();
		dd = (List<Markscan_pipe>) template.find(query);
		System.out.println("oooo   "+dd.size());
		return dd;
	}
	
	
}
