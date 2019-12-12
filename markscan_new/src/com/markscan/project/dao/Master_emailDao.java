/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Master_email;

/**
 * @author pradeep
 *
 */
public class Master_emailDao {

	/**
	 *  29 nov 2016
	 */
	public Master_emailDao() {
		// TODO Auto-generated constructor stub
	}
	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public void saveData(Master_email e) {
		template.save(e);
	}

	List<String> dashboardData = null;

	public List<Master_email> viewRecord(String query) {
		// template.setMaxResults(x);
		List<Master_email> dd = new ArrayList<>();
		dd = (List<Master_email>) template.find(query);
		System.out.println("Module_detail  ooo   " + dd.size());
		return dd;
	}
	public void customUpdateProject(String query) { // for qc
		template.bulkUpdate(query);

	}
}
