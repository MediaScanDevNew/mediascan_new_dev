/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Greylist;

/**
 * @author pradeep
 *
 */
public class GreylistDao {

	/**
	 * 
	 */
	public GreylistDao() {
		// TODO Auto-generated constructor stub
	}

private HibernateTemplate template;

	
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	
	
	public List<Greylist> viewRecord(String query)
	{template.setMaxResults(0);
		List<Greylist> users =  new ArrayList<Greylist>(); 
		users = (List<Greylist>) template.find(query);
		System.out.println("ooo   "+users.size());
		return users;
	}
	
	public void saveData(Greylist t) {
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
