/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Crawle_url2;
import com.markscan.project.beans.Markscan_machine;
import com.markscan.project.beans.Markscan_projecttype;
import com.markscan.project.beans.Markscan_users;

/**
 * @author pradeep
 *
 */
public class Markscan_usersDao {

	/**
	 * 
	 */
	public Markscan_usersDao() {
		// TODO Auto-generated constructor stub
	}

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public List<Markscan_users> viewRecord(String query) {
		// template.setMaxResults(x);
		List<Markscan_users> dd = new ArrayList<>();
		dd = (List<Markscan_users>) template.find(query);
		System.out.println("oooo   " + dd.size());
		return dd;
	}

	public void saveData(Markscan_users t) {
		try {
			template.save(t);
		} catch (Exception e) {
			System.err.println("template save error !!!");
			e.printStackTrace();
		}
	}

	public int customUpdateProject(String query) { // for qc
		return template.bulkUpdate(query);

	}
}
