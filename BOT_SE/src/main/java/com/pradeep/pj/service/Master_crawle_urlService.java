/**
 * 
 */
package com.pradeep.pj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pradeep.pj.repo.Master_crawle_urlRepository;

/**
 * @author pradeep
 *
 */
@Service
public class Master_crawle_urlService {

	@Autowired
	Master_crawle_urlRepository mcur;

	@Transactional
	public Integer storeCrawleData(String lnk, int prid, int uid, int sid, int ppid, String machine, String domain,String title) {
		return mcur.saveData(lnk, prid, uid, sid, ppid, machine, domain,title);
	}

}
