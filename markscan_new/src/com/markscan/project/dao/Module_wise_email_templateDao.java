/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Module_wise_email_template;

/**
 * @author pradeep
 *
 */
public class Module_wise_email_templateDao {

	/**
	 * 
	 */
	public Module_wise_email_templateDao() {
		// TODO Auto-generated constructor stub
	}
	

	HibernateTemplate template;
	

	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public List<Module_wise_email_template> viewRecord(String query) {
		// template.setMaxResults(x);
		List<Module_wise_email_template> dd = new ArrayList<>();
		dd = (List<Module_wise_email_template>) template.find(query);
		System.out.println("oooo  size module_wise_email_templatedao  " + dd.size());
		return dd;
	}
}
