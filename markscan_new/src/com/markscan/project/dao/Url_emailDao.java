/**
 * 
 */
package com.markscan.project.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Url_email;

/**
 * @author pradeep
 *
 */
public class Url_emailDao {

	/**
	 * 01-Dec-2016
	 */
	public Url_emailDao() {
		// TODO Auto-generated constructor stub
	}

	private HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public List<Url_email> viewRecord(String query) {
		template.setMaxResults(500);
		List<Url_email> users = new ArrayList<Url_email>();
		users = (List<Url_email>) template.find(query);
		System.out.println("user size   " + users.size());
		template.setMaxResults(0);
		return users;
		
	}
	
	public List<Url_email> viewRecord(String query, int x) { // for data
		List<Url_email> dd = new ArrayList<Url_email>();	// export limit
		try{												    // 50000
		template.setMaxResults(x);
		
		dd = (List<Url_email>) template.find(query);
		System.out.println("oooo   " + dd.size());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return dd;
	}
	

	public void saveData(Url_email t) {
		
		try {
			template.save(t);
		} catch (Exception e) {
			System.err.println("template save error !!!");
			e.printStackTrace();
		}
	}
	
	
	public String saveData_retuenID(Url_email t) {
		String pj = null;
		try {
			Serializable ss = template.save(t);
			pj = ss.toString();
			// System.out.println("=== ss value----"+ss.toString());
			ss=null;
			return pj;
		} catch (Exception e) {
			System.err.println("template save error !!!");
			e.printStackTrace();
			return pj;
		}
	}
	public void customUpdateProject(String query) { // for qc
		template.bulkUpdate(query);

	}
	
}
