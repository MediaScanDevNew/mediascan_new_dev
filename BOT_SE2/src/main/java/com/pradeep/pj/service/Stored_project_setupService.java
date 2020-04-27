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

	public List<Object[]> findALLCustom(int pId) {
		List<Object[]> allCustomRecords = new ArrayList<>();
		allCustomRecords = ssr.findAllCustomQuery(pId);
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
	
	/** Added on Jan/27 to track end of Russian Search engine crawl **/
	/** End of Russian Search Engine */ 
	@Transactional
	public int russiacomplete(int id) {
		return ssr.russiacomplete(id);
	}
	
	
	//--------------- 15.02.2020 ------------------------------------
	
	@Transactional
	public int yahooStart(int id) {
		return ssr.yahooStart(id);
	}
	
	@Transactional
	public int bingStart(int id) {
		return ssr.bingStart(id);
	}
	
	@Transactional
	public int duckduckStart(int id) {
		return ssr.duckduckStart(id);
	}
	
	@Transactional
	public int russiaGoStart(int id) {
		return ssr.russiaGoStart(id);
	}
	
	
	
	
	public List<Object[]> getDataForMails(int projectId) {
		List<Object[]> allMailRecords = new ArrayList<>();
		allMailRecords = ssr.findAllMailRecords(projectId);
		return allMailRecords;
	}
	
	@Transactional
	public int whitelistStart(int id) {
		return ssr.whitelistStart(id);
	}
	
	@Transactional
	public int greylistStart(int id) {
		return ssr.greylistStart(id);
	}
	
	@Transactional
	public int blacklistStart(int id) {
		return ssr.blacklistStart(id);
	}
	
	@Transactional
	public int blacklistComplate(int id) {
		return ssr.blacklistComplate(id);
	}
	
	@Transactional
	public int failedFlag(int id) {
		return ssr.failedFlag(id);
	}
	
	
	//--------------- 07.04.2020 ------------------------------------
	
	@Transactional
	public int googleCompleted(int id) {
		return ssr.googleCompleted(id);
	}
	
	@Transactional
	public int yahooCompleted(int id) {
		return ssr.yahooCompleted(id);
	}
	
	@Transactional
	public int bingCompleted(int id) {
		return ssr.bingCompleted(id);
	}
	
	@Transactional
	public int duckduckCompleted(int id) {
		return ssr.duckduckCompleted(id);
	}
	
	@Transactional
	public int russiaCompleted(int id) {
		return ssr.russiaCompleted(id);
	}
	
	//------------------- 07.04.2020 --------------------------------------
}
