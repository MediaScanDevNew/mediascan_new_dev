/**
 * 
 */
package com.markscan.project.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.markscan.project.beans.YT_video_detail;
import com.markscan.project.classes.QualityCheck;


/**
 * @author pradeep CREATED ON 20FEB2018
 */
public class YT_video_detailDao {
	private static final Logger logger = Logger.getLogger(YT_video_detailDao.class);
	/**
	 * 
	 */
	public YT_video_detailDao() {
		// TODO Auto-generated constructor stub
	}

	HibernateTemplate template;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	public void customUpdateProject(String query) { // for qc
		template.bulkUpdate(query);

	}

	public List<YT_video_detail> viewRecord(String query) { // for Qc limit 500
		template.setMaxResults(500);
		List<YT_video_detail> dd = new ArrayList<YT_video_detail>();
		dd = (List<YT_video_detail>) template.find(query);
		logger.info("oooo   " + dd.size());
		template.setMaxResults(0);
		return dd;
	}



}
