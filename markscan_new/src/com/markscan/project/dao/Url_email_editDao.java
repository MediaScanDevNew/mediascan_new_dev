/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Url_email_edit;

/**
 * @author pradeep
 * 2 jan 2017
 */
public class Url_email_editDao {

	/**
	 * 05 dec 2016
	 */
	public Url_email_editDao() {
		// TODO Auto-generated constructor stub
	}

	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public List<Url_email_edit> viewRecord(String query) {
		List<Url_email_edit> users = new ArrayList<Url_email_edit>();
		users = (List<Url_email_edit>) template.find(query);
		System.out.println("user size   " + users.size());
		return users;
	}

	public void saveData(Url_email_edit t) {
		try {
			template.save(t);
		} catch (Exception e) {
			System.err.println("template save error !!!");
			e.printStackTrace();
		}
	}
	public void customUpdateProject(String query) { // for qc
		template.bulkUpdate(query);

	}
	
}
