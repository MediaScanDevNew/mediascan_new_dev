package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import com.markscan.project.beans.Domain_Mst;

public class Domain_masterDao {
	
	
	public Domain_masterDao() {
		// TODO Auto-generated constructor stub
	}

	HibernateTemplate template;
	
	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	public List<Domain_Mst> viewRecord(String query) {
		// template.setMaxResults(x);
		List<Domain_Mst> dd = new ArrayList<>();
		dd = (List<Domain_Mst>) template.find(query);
		System.out.println("Domain master size is --->  " + dd.size());
		return dd;
	}
}
