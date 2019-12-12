/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Whitelist_instagram;


/**
 * @author pradeep
 *
 */
public class Whitelist_instagramDao {

	/**
	 * 
	 */
	public Whitelist_instagramDao() {
		// TODO Auto-generated constructor stub
	}

	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public List<Whitelist_instagram> viewRecord(String query) {
		template.setMaxResults(0);
		List<Whitelist_instagram> users = new ArrayList<Whitelist_instagram>();
		users = (List<Whitelist_instagram>) template.find(query);
		System.out.println("ooo   " + users.size());
		return users;
	}

	public void saveData(Whitelist_instagram t) {
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
