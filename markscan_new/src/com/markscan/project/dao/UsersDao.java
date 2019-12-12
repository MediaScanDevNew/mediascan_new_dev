/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Markscan_users;

/**
 * @author pradeep
 *
 */
public class UsersDao {
	
	private HibernateTemplate template;

	
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	
	
	public List<Markscan_users> viewRecord(String query)
	{
		List<Markscan_users> users =  new ArrayList<Markscan_users>(); 
		users = (List<Markscan_users>) template.find(query);
		System.out.println("user size   "+users.size());
		return users;
	}


	

}
