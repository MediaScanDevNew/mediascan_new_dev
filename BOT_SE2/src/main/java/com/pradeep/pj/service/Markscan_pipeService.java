/**
 * 
 */
package com.pradeep.pj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeep.pj.repo.Markscan_pipeRepository;

/**
 * @author pradeep
 *
 */
@Service
public class Markscan_pipeService {
	@Autowired
	Markscan_pipeRepository mpr;

	public String findALLCustom(int pid) {
//		List<Object[]> allCustomRecords = new ArrayList<>();
//		allCustomRecords = mpr.AllDataCustom(pid);
		return mpr.AllDataCustom(pid);
	}

}
