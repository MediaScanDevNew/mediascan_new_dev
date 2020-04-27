/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Markscan_users;
import com.markscan.project.beans.Module_detail;

/**
 * @author pradeep 20 sep 2016
 */
public class Module_detailDao {

	/**
	 * 
	 */
	public Module_detailDao() {
		// TODO Auto-generated constructor stub
	}

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public void saveData(Module_detail e) {
		template.save(e);
	}

	List<String> dashboardData = null;

	public List<Module_detail> viewRecord(String query) {
		// template.setMaxResults(x);
		List<Module_detail> dd = new ArrayList<>();
		dd = (List<Module_detail>) template.find(query);
		System.out.println("Module_detail  ooo   " + dd.size());
		return dd;
	}
}
