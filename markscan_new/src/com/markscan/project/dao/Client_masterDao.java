package com.markscan.project.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Client_master;

public class Client_masterDao {

	public Client_masterDao() {
		// TODO Auto-generated constructor stub
	}

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public List<Client_master> viewRecord(String query) {
		// template.setMaxResults(x);
		List<Client_master> dd = new ArrayList<>();
		dd = (List<Client_master>) template.find(query);
		System.out.println("oooo  size Client_masterDao  " + dd.size());
		return dd;
	}
	public String saveData(Client_master t) {
		String pj = null;
		try {
			 template.saveOrUpdate(t);
//			pj = ss.toString();
			// System.out.println("=== ss value----"+ss.toString());
			return pj;
		} catch (Exception e) {
			System.err.println("template save error !!!");
			e.printStackTrace();
			return pj;
		}
	}
	
	public void customUpdateProject(String query) { // for qc
		template.bulkUpdate(query);

	}
	
}
