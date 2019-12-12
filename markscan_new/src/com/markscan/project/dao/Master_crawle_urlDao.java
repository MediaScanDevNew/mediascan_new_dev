/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Markscan_users;
import com.markscan.project.beans.Master_crawle_url;

/**
 * @author pradeep
 *
 */
public class Master_crawle_urlDao {

	/**
	 * 
	 */
	public Master_crawle_urlDao() {
		// TODO Auto-generated constructor stub
	}

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public List<Master_crawle_url> viewRecord(String query) {
		// template.setMaxResults(x);
		List<Master_crawle_url> dd = new ArrayList<>();
		dd = (List<Master_crawle_url>) template.find(query);
		System.out.println("oooo   " + dd.size());
		return dd;
	}
	public void customUpdateProject(String query) {
		template.bulkUpdate(query);

	}
}
