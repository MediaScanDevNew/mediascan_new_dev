/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Whitelist_yt;

/**
 * @author pradeep created on 23feb2018
 */
public class Whitelist_ytDao {

	/**
	 * 
	 */
	public Whitelist_ytDao() {
		// TODO Auto-generated constructor stub
	}

	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public List<Whitelist_yt> viewRecord(String query) {
		template.setMaxResults(0);
		List<Whitelist_yt> users = new ArrayList<Whitelist_yt>();
		users = (List<Whitelist_yt>) template.find(query);
		System.out.println("ooo   " + users.size());
		return users;
	}

	public void saveData(Whitelist_yt t) {
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
