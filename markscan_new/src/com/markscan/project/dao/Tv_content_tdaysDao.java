package com.markscan.project.dao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;
import com.markscan.project.beans.Tv_content_tdays;
public class Tv_content_tdaysDao {

	public Tv_content_tdaysDao() {
     
		// TODO Auto-generated constructor stub
	}
	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	public void saveData(Tv_content_tdays t) {
		try {
			template.save(t);
		} catch (Exception e) {
			System.err.println("template save error !!!");
			e.printStackTrace();
		}
	}

	public List<Tv_content_tdays> viewRecord(String query) {
		// template.setMaxResults(x);
		List<Tv_content_tdays> dd = new ArrayList<>();
		dd = (List<Tv_content_tdays>) template.find(query);
		System.out.println("oooo  size Tv_content_tdays  " + dd.size());
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
