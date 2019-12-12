/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Enforcement_mail_id;


/**
 * @author pradeep
 *
 */
public class Enforcement_mail_idDao {

	/**
	 * 4 jan 2017
	 */
	public Enforcement_mail_idDao() {
		// TODO Auto-generated constructor stub
	}

	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	public List<Enforcement_mail_id> viewRecord(String query) {
		List<Enforcement_mail_id> users = new ArrayList<Enforcement_mail_id>();
		users = (List<Enforcement_mail_id>) template.find(query);
		System.out.println("user size   " + users.size());
		return users;
	}

	public void saveData(Enforcement_mail_id t) {
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
