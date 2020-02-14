/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Master_email_tracker;


/**
 * @author pradeep
 *
 */
public class Master_email_trackerDao {

	/**
	 * 16 jan 2016
	 */
	public Master_email_trackerDao() {
		// TODO Auto-generated constructor stub
	}
	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public List<String> viewData(Master_email_tracker cc, String query) {
		dashboardData = (List<String>) template.find(query);
		System.out.println(dashboardData.size());
		return dashboardData;
	}

	public List<Master_email_tracker> viewRecord(String query, int x) { // for data
		List<Master_email_tracker> dd = new ArrayList<Master_email_tracker>(); // export
																		// limit
		try { // 50000
			template.setMaxResults(x);

			dd = (List<Master_email_tracker>) template.find(query);
			System.out.println("oooo   " + dd.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dd;
	}

	public void saveData(Master_email_tracker t) {
		try {
			template.save(t);
		} catch (Exception e) {
			System.err.println("template save error !!!");
			e.printStackTrace();
		}
	}

	public List<Master_email_tracker> viewRecord(String query) { // for Qc limit 500
		template.setMaxResults(500);
		List<Master_email_tracker> dd = new ArrayList<Master_email_tracker>();
		dd = (List<Master_email_tracker>) template.find(query);
		System.out.println("oooo   " + dd.size());
		template.setMaxResults(0);
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
