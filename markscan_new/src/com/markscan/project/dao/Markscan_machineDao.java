/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Markscan_machine;

/**
 * @author pradeep
 *
 */
public class Markscan_machineDao {

	/**
	 * 
	 */
	public Markscan_machineDao() {
		// TODO Auto-generated constructor stub
	}

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	public List<Markscan_machine> viewRecord(String query) {
//		template.setMaxResults(x);
		List<Markscan_machine> dd = new ArrayList<>();
		dd = (List<Markscan_machine>) template.find(query);
		System.out.println("oooo   "+dd.size());
		return dd;
	}
}
