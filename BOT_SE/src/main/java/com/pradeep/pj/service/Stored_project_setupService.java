/**
 * 
 */
package com.pradeep.pj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pradeep.pj.repo.Stored_project_setupRepository;

/**
 * @author pradeep
 *
 */
@Service
public class Stored_project_setupService {

	@Autowired
	Stored_project_setupRepository ssr;

	public List<Object[]> findALLCustom() {
		System.out.println("~~~~~~~ipaddress---------");
		List<Object[]> allCustomRecords = new ArrayList<>();
		
		allCustomRecords = ssr.findAllCustomQuery();
		
		return allCustomRecords;
	}
	
	public String findKeyPhrase(int id)
	{
		return ssr.findKeyPhrase(id);
	}
	
	@Transactional
	public int googleStart(int id) {
		return ssr.googleStart(id);
	}
	@Transactional
	public int yahooStartGoogleComplate(int id) {
		return ssr.googlecomplate(id);
	}
	@Transactional
	public int bingStartYahooComplate(int id) {
		return ssr.yahoocomplate(id);
	}
	/*@Transactional
	public int bingComplate(int id) {
		return ssr.bingcomplate(id);
	}*/

	@Transactional
	public int googleOversiesStart(int id) {
		return ssr.onlyGoogleOversiesStart(id);
	}
	@Transactional
	public int googleOversiesComplate(int id) {
		return ssr.onlyGoogleOversiesComplete(id);
	}
	@Transactional
	public int allComplate(int id) {
		return ssr.allComplete(id);
	}
	
	//-------------- 20.12.2019 -----------------------
	
	@Transactional
	public int duckduckStartBingComplete(int id) {
		return ssr.bingcomplate(id);
	}
	
	@Transactional
	public int russiaGoStartduckduckGoComplete(int id) {
		return ssr.duckduckGocomplate(id);
	}
}
