package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Productivity_client_wise;


public class Productivity_client_wiseDao {

	public Productivity_client_wiseDao() {
		// TODO Auto-generated constructor stub
	}

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	@SuppressWarnings("unchecked")
	public List<Productivity_client_wise> viewRecord(String query) { // for Qc limit 500
		template.setMaxResults(500);
		List<Productivity_client_wise> dd = new ArrayList<Productivity_client_wise>();
		dd =  template.find(query);
		System.out.println("oooo   " + dd.size());
		template.setMaxResults(0);
		return dd;
	}

	public void customUpdateProject(String query) { // for qc
		template.bulkUpdate(query);

	}

	public void saveupdate(Productivity_client_wise query) { // for qc
		// template.bulkUpdate(query);
		template.saveOrUpdate(query);

	}

	public void saveData(Productivity_client_wise t) {
		try {
			template.save(t);
		} catch (org.hibernate.exception.ConstraintViolationException e) {

		}

	}

	
}
