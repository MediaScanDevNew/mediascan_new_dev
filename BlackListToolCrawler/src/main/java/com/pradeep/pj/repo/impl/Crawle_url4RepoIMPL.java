package com.pradeep.pj.repo.impl;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import com.pradeep.pj.model.Crawle_url4;
import com.pradeep.pj.repo.Crawle_url4Repository;

@Service
@Configurable
public class Crawle_url4RepoIMPL {

	public Crawle_url4RepoIMPL() {
		
	}
	@Autowired
	private Crawle_url4Repository crawleRepo;
	
	public void addData(Crawle_url4 crawleUrl){
//		crawleRepo.save(crawleUrl);
		new IWLDataProcess().addDataCrawle4(crawleUrl);
	}
	
	
	public int getIdByURL(String URL) throws SQLException{
		//new IWLDataProcess().DataProcess(Integer.parseInt(ids[i]));
		//int val = crawleRepo.getIdByURL(URL);
		int val = new IWLDataProcess().getIdByURL(URL);
		return val;
	}
	
	public void updateIWLErrorField(int URL_id) throws SQLException{
		//crawleRepo.updateIWLErrorField(URL_id);
		new IWLDataProcess().updateIWLErrorField(URL_id);
	}
	
	public void saveData(String URL, int projectid, int userid, int k, String domain_name, int l) throws SQLException{
		//crawleRepo.saveData(URL, projectid, userid, 0,  domain_name, 1);
		new IWLDataProcess().saveData(URL, projectid, userid, 0,  domain_name, 1);
	}
}
