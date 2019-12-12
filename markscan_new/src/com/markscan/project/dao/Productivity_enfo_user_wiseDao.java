/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Productivity_enfo_user_wise;



/**
 * @author pradeep
 *
 */
public class Productivity_enfo_user_wiseDao {

	/**
	 * 
	 */
	public Productivity_enfo_user_wiseDao() {
		// TODO Auto-generated constructor stub
	}

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public List<Productivity_enfo_user_wise> viewRecord(String query) { // for Qc limit 500
		template.setMaxResults(500);
		List<Productivity_enfo_user_wise> dd = new ArrayList<Productivity_enfo_user_wise>();
		dd = (List<Productivity_enfo_user_wise>) template.find(query);
		System.out.println("oooo   " + dd.size());
		template.setMaxResults(0);
		return dd;
	}

	public void customUpdateProject(String query) { // for qc
		template.bulkUpdate(query);

	}

	public void saveupdate(Productivity_enfo_user_wise query) { // for qc
		// template.bulkUpdate(query);
		template.saveOrUpdate(query);

	}

	public void saveData(Productivity_enfo_user_wise t) {
		try {
			template.save(t);
		} catch (org.hibernate.exception.ConstraintViolationException e) {

		}

	}


}
