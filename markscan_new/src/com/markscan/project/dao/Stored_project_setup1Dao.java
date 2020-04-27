package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

//import com.markscan.project.beans.Stored_project_setup;
import com.markscan.project.beans.Stored_project_setup1;

public class Stored_project_setup1Dao {

	public Stored_project_setup1Dao() {

		// TODO Auto-generated constructor stub
	}
	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	public void saveData(Stored_project_setup1 sps){  
	    template.save(sps);  
	}  
	
	public List<Stored_project_setup1> viewRecord(String query) {
//		template.setMaxResults(x);
		List<Stored_project_setup1> dd = new ArrayList<>();
		dd = (List<Stored_project_setup1>) template.find(query);
		System.out.println("oooo   "+dd.size());
		return dd;
	}
	public void customUpdateProject(String query) { // for qc
		template.bulkUpdate(query);

	}

}
