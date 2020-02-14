package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.Crawle_url2;

public class Crawle_url2Dao {
	private static final Logger logger = Logger.getLogger(Crawle_url2Dao.class);
	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public List<String> viewData(Crawle_url2 cc, String query) {
		dashboardData = (List<String>) template.find(query);
		System.out.println(dashboardData.size());
		return dashboardData;
	}

	public List<Crawle_url2> viewRecord(String query, int x) { // for data
		List<Crawle_url2> dd = new ArrayList<Crawle_url2>();	// export limit
		try{												    // 50000
		template.setMaxResults(x);
		
		dd = (List<Crawle_url2>) template.find(query);
		System.out.println("oooo   " + dd.size());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return dd;
	}
	
	public List<Crawle_url2> viewRecordListParameter(String query, int x, String aa) { // for data
		List<Crawle_url2> dd = new ArrayList<Crawle_url2>();	// export limit // 50000
		String pj[]=aa.split(",");
		Crawle_url2 cu= null;
		List <Integer> prade= new ArrayList<>();
		for(String gg:pj)
		{
			cu.setId(Integer.parseInt(gg.trim()));
			prade.add(Integer.parseInt(gg.trim()));
		}
		
		try{												    
//		template.setMaxResults(x);
		
		dd = (List<Crawle_url2>) template.find(query,aa);
		System.out.println("oooo   " + dd.size());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return dd;
	}

	public List<Crawle_url2> viewRecord(String query) { // for Qc limit 500
		template.setMaxResults(500);
		List<Crawle_url2> dd = new ArrayList<Crawle_url2>();
		dd = (List<Crawle_url2>) template.find(query);
		logger.info("oooo   " + dd.size());
		template.setMaxResults(0);
		return dd;
	}

	public void customUpdateProject(String query) { // for qc
		template.bulkUpdate(query);

	}
	
	public void customUpdateProjectList(String query, Set<Integer>i) { // for qc
		template.bulkUpdate(query,i);

	}

	public int getNumberOfUsers(String query) { // count no of rows
	      return DataAccessUtils.intResult(template.find(query));
	   }
	
	List<String> dashboardData = null;

}
