package com.markscan.project.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Crawle_url2;
import com.markscan.project.beans.Txn_tbl;

public class Txn_tblDao {

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	// public void saveData(Txn_tbl t) {
	// template.save(t);
	// }

	public void saveAll(Crawle_url2 t) {
		// template.save
		
	}

	public String saveData(Crawle_url2 t) {
		String pj = null;
		try {
			Serializable ss = template.save(t);
			return ss.toString();
//			pj = ss.toString();
			// System.out.println("=== ss value----"+ss.toString());
//			ss=null;
//			return pj;
		}
		catch (Exception e) {
			System.err.println("data save error !!!----------------------"+e);
//			e.printStackTrace();
			return "pradeep__ERROR";
		}
	}

	public void customUpdateProject(String query) {
		template.bulkUpdate(query);

	}
	
	public String saveBatchData(List<Crawle_url2>t )
	{String pj = null;
		try {
			
			 template.saveOrUpdateAll(t);
			
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return pj;
	}
	
}
