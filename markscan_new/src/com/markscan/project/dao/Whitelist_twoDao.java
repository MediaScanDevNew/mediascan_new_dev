/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Whitelist;
import com.markscan.project.beans.Whitelist_two;

/**
 * @author pradeep
 *
 */
public class Whitelist_twoDao {

	/**
	 * 13 oct 2016
	 */
	public Whitelist_twoDao() {

		// TODO Auto-generated constructor stub
	}

	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {

		this.template = template;
	}

	public List<Whitelist_two> viewRecord(String query) {

		template.setMaxResults(0);
		List<Whitelist_two> users = new ArrayList<Whitelist_two>();
		users = (List<Whitelist_two>) template.find(query);
		System.out.println("ooo   " + users.size());
		return users;
	}

	public void saveData(Whitelist_two t) {

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
