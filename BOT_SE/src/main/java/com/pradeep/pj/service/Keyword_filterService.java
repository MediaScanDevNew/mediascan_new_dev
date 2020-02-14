/**
 * 
 */
package com.pradeep.pj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeep.pj.repo.Keyword_filterRepository;

/**
 * @author pradeep
 *
 */
@Service
public class Keyword_filterService {
	
	@Autowired
	Keyword_filterRepository kfs;
	
	public List<String> findALLCustom(int pid) {
		List<String> allCustomRecords = new ArrayList<>();
		allCustomRecords = kfs.findAllCustomQuery(pid);
		return allCustomRecords;
	}

}
