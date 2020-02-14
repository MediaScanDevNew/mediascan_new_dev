/**
 * 
 */
package com.pradeep.pj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeep.pj.repo.Blacklist_sitesRepository;

/**
 * @author pradeep
 *
 */
@Service
public class Blacklist_sitesService {
	
	@Autowired
	Blacklist_sitesRepository bsr;
	
	public List<Object[]> findALLCustom() {
		List<Object[]> allCustomRecords = new ArrayList<>();
		allCustomRecords = bsr.findAllCustomQuery();
		return allCustomRecords;
	}

}
