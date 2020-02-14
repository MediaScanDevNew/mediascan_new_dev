/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Mail_shoot_data;

/**
 * @author pradeep 13 jan 2017
 */
public class Mail_shoot_dataDao {

	/**
	 * 
	 */
	public Mail_shoot_dataDao() {
		// TODO Auto-generated constructor stub
	}

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public List<String> viewData(Mail_shoot_data cc, String query) {
		dashboardData = (List<String>) template.find(query);
		System.out.println(dashboardData.size());
		return dashboardData;
	}

	public List<Mail_shoot_data> viewRecord(String query, int x) { // for data
		List<Mail_shoot_data> dd = new ArrayList<Mail_shoot_data>(); // export
																		// limit
		try { // 50000
			template.setMaxResults(x);

			dd = (List<Mail_shoot_data>) template.find(query);
			System.out.println("oooo   " + dd.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dd;
	}

	public void saveData(Mail_shoot_data t) {
		try {
			template.save(t);
		} catch (Exception e) {
			System.err.println("template save error !!!");
			e.printStackTrace();
		}
	}

	public List<Mail_shoot_data> viewRecord(String query) { // for Qc limit 500
//		template.setMaxResults(500);
		List<Mail_shoot_data> dd = new ArrayList<Mail_shoot_data>();
		dd =   template.find(query);
		System.out.println("oooo   " + dd.size());
//		template.setMaxResults(0);
		return dd;
	}

	public void customUpdateProject(String query) { // for qc
		template.bulkUpdate(query);

	}

	public void customUpdateProjectList(String query, Set<Integer> i) { // for
																		// qc
		template.bulkUpdate(query, i);

	}

	public int getNumberOfUsers(String query) { // count no of rows
		return DataAccessUtils.intResult(template.find(query));
	}

	/*public void customDelete(Mail_shoot_data t) {
		template.delete(t);
	}*/

	List<String> dashboardData = null;
}
