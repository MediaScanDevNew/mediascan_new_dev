/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Qc_record;
import com.markscan.project.beans.Stored_project_setup;

/**
 * @author pradeep
 *
 */
public class Qc_recordDao {

	/**
	 * 
	 */
	public Qc_recordDao() {
		// TODO Auto-generated constructor stub
	}

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public List<Qc_record> viewRecord(String query) {
		// template.setMaxResults(x);
		List<Qc_record> dd = new ArrayList<>();
		dd = (List<Qc_record>) template.find(query);
		System.out.println("oooo  size Qc_record  " + dd.size());
		return dd;
	}

	public int customUpdateProject(String query) {
		return template.bulkUpdate(query);

	}
	public void saveData(Qc_record e){  
	    template.save(e);  
	}  
	public void saveUpdateData(Qc_record e){  
	    template.saveOrUpdate(e);  
	}  
}
