package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Imdb_content_detail;


public class Imdb_content_detailDao {
	
	
	public Imdb_content_detailDao(){}
	
	
	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	public void saveData(Imdb_content_detail t) {
		try {
			template.save(t);
		} catch (Exception e) {
			System.err.println("template save error !!!");
			e.printStackTrace();
		}
	}

	public List<Imdb_content_detail> viewRecord(String query) {
		// template.setMaxResults(x);
		List<Imdb_content_detail> dd = new ArrayList<>();
		dd = (List<Imdb_content_detail>) template.find(query);
		System.out.println("Imdb TV content days  " + dd.size());
		return dd;
	}
	public void updateData(String query)
	{
		template.bulkUpdate(query);
	}
	
	public void customUpdateProject(String query) { // for qc
		template.bulkUpdate(query);

	}

}
