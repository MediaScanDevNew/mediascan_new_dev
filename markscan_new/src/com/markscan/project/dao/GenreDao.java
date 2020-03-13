package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Genre;


public class GenreDao {

	public GenreDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public List<Genre> viewRecord(String query) {
		// template.setMaxResults(x);
		List<Genre> dd = new ArrayList<>();
		dd = (List<Genre>) template.find(query);
		// System.out.println("oooo size Client_masterDao " + dd.size());
		return dd;
	}
}
