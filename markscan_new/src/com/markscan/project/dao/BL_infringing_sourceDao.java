/**
 * 
 */
package com.markscan.project.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.BL_infringing_source;

/**
 * @author pradeep
 *
 */
public class BL_infringing_sourceDao {

	/**
	 * 
	 */
	public BL_infringing_sourceDao() {
		// TODO Auto-generated constructor stub
	}

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public List<BL_infringing_source> viewRecord(String query) {
		 template.setMaxResults(0);
		List<BL_infringing_source> dd = new ArrayList<>();
		dd = (List<BL_infringing_source>) template.find(query);
		// System.out.println("oooo size Client_masterDao " + dd.size());
		return dd;
	}

	public List<BL_infringing_source> viewRecord01(String query, int x) {
		template.setMaxResults(x);
		List<BL_infringing_source> dd = new ArrayList<>();
		dd = (List<BL_infringing_source>) template.find(query);
		// System.out.println("oooo size Client_masterDao " + dd.size());
		return dd;
	}

	public String saveData(BL_infringing_source t) {
		String pj = null;
		try {
			Serializable ss = template.save(t);

			pj = ss.toString();
			// System.out.println("=== ss value----"+ss.toString());
			return pj;
		} catch (Exception e) {
			// System.err.println("template save error !!!");
			e.printStackTrace();
			return pj;
		}
	}

	public void customUpdateProject(String query) { // for qc
		template.bulkUpdate(query);

	}

	public void CustomDelete(String entityName, String entity) {
		template.delete(entityName, entity);
	}
}
