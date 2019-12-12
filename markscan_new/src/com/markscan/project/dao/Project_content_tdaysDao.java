package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Project_content_tdays;
import com.markscan.project.beans.Tv_content_tdays;
public class Project_content_tdaysDao {

	public Project_content_tdaysDao() {
     
		// TODO Auto-generated constructor stub
	}
	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	public void saveData(Project_content_tdays t) {
		try {
			template.save(t);
		} catch (Exception e) {
			System.err.println("template save error !!!");
			e.printStackTrace();
		}
	}

	public List<Project_content_tdays> viewRecord(String query) {
		// template.setMaxResults(x);
		List<Project_content_tdays> dd = new ArrayList<>();
		dd = (List<Project_content_tdays>) template.find(query);
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
