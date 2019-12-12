/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Markscan_users;
import com.markscan.project.beans.User_module_previlege;

/**
 * @author pradeep
 *
 */
public class User_module_previlegeDao {

	/**
	 * 
	 */
	public User_module_previlegeDao() {
		// TODO Auto-generated constructor stub
	}

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public void deleteData(User_module_previlege t){  
	    template.delete(t);  
	    
	}  
	
	public void saveData(User_module_previlege t) {
		try {
			template.save(t);
		} catch (Exception e) {
			System.err.println("template save error !!!");
			e.printStackTrace();
		}
	}

	public List<String> viewData(User_module_previlege cc, String query) {
		dashboardData = (List<String>) template.find(query);
		System.out.println(dashboardData.size());
		return dashboardData;
	}

	public List<User_module_previlege> viewRecord(String query, int x) { // for
																			// data
		// export limit
		// 50000
		template.setMaxResults(x);
		List<User_module_previlege> dd = new ArrayList<User_module_previlege>();
		dd = (List<User_module_previlege>) template.find(query);
		System.out.println("oooo   " + dd.size());
		return dd;
	}

	public List<User_module_previlege> viewRecord(String query) { // for Qc
																	// limit 500
		template.setMaxResults(500);
		List<User_module_previlege> dd = new ArrayList<User_module_previlege>();
		dd = (List<User_module_previlege>) template.find(query);
		System.out.println("oooo   " + dd.size());
		template.setMaxResults(0);
		return dd;
	}

	public void customUpdateProject(String query) { // for qc
		template.bulkUpdate(query);

	}

	public int getNumberOfUsers(String query) { // count no of rows
		return DataAccessUtils.intResult(template.find(query));
	}

	List<String> dashboardData = null;
}
