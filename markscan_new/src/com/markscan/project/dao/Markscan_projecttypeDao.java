/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Markscan_machine;
import com.markscan.project.beans.Markscan_projecttype;

/**
 * @author pradeep
 *
 */
public class Markscan_projecttypeDao {

	/**
	 * 
	 */
	public Markscan_projecttypeDao() {
		// TODO Auto-generated constructor stub
	}

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	public List<Markscan_projecttype> viewRecord(String query) {
//		template.setMaxResults(x);
		List<Markscan_projecttype> dd = new ArrayList<>();
		dd = (List<Markscan_projecttype>) template.find(query);
		System.out.println("oooo  size Markscan_projecttypeDao  "+dd.size());
		return dd;
	}
}
