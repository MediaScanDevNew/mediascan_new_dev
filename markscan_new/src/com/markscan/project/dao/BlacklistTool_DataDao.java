/**
 * 
 */
package com.markscan.project.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.BlacklistTool_Data;

/**
 * @author pradeep
 *
 */
public class BlacklistTool_DataDao {

	/**
	 * 
	 */
	public BlacklistTool_DataDao() {
		// TODO Auto-generated constructor stub
	}

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public List<BlacklistTool_Data> viewRecord(String query) {
		// template.setMaxResults(x);
		List<BlacklistTool_Data> dd = new ArrayList<>();
		dd = (List<BlacklistTool_Data>) template.find(query);
//		System.out.println("oooo  size Client_masterDao  " + dd.size());
		return dd;
	}
	
	public List<BlacklistTool_Data> viewRecord01(String query, int x) {
		 template.setMaxResults(x);
		List<BlacklistTool_Data> dd = new ArrayList<>();
		dd = (List<BlacklistTool_Data>) template.find(query);
//		System.out.println("oooo  size Client_masterDao  " + dd.size());
		return dd;
	}

	public String saveData(BlacklistTool_Data t) {
		String pj = null;
		try {
			Serializable ss=template.save(t);
			
			 pj = ss.toString();
			// System.out.println("=== ss value----"+ss.toString());
			return pj;
		} catch (Exception e) {
//			System.err.println("template save error !!!");
			e.printStackTrace();
			return pj;
		}
	}

	public void customUpdateProject(String query) { // for qc
		template.bulkUpdate(query);

	}

}
