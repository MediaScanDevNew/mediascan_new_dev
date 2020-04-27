package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Crawle_url4;


public class Crawle_url4Dao {

	public Crawle_url4Dao() {
		super();
		// TODO Auto-generated constructor stub
	}
	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public List<Crawle_url4> viewRecord(String query) {
		// template.setMaxResults(x);
		List<Crawle_url4> dd = new ArrayList<>();
		dd = (List<Crawle_url4>) template.find(query);
//		System.out.println("oooo   " + dd.size());
		return dd;         
	}
	public void customUpdateProject(String query) {
		template.bulkUpdate(query);

	}
}
