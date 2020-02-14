package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Markscan_projecttype;
import com.markscan.project.beans.Markscan_users;
import com.markscan.project.beans.Project_info;

public class Project_infoDao {
	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public List<Project_info> getData(String query, int value) {
		List<Project_info> list = new ArrayList<Project_info>();
		list = (List<Project_info>) template.find(query, value);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Project_info> viewRecord(String query) {
		// template.setMaxResults(x);
		List<Project_info> dd = new ArrayList<>();
		dd = (List<Project_info>) template.find(query);
		System.out.println("oooo  size Project_infoDao  " + dd.size());
		return dd;
	}
	

	public void updateProject(Project_info e) {
		template.update(e);
	}

	public void customUpdateProject(String query) {
		template.bulkUpdate(query);

	}
	public void saveData(Project_info t) {
		try {
			template.save(t);
		} catch (Exception e) {
			System.err.println("template save error !!!");
			e.printStackTrace();
		}
	}
}
