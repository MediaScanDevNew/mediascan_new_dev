/**
 * 
 */
package com.pradeep.pj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeep.pj.repo.Project_infoRepository;

/**
 * @author pradeep
 *
 */
@Service
public class Project_infoService {

	@Autowired
	Project_infoRepository pir;
	
	@Autowired
	Project_infoRepository projInfo;

	public String findALLCustom(int pid) {
//		List<Object[]> allCustomRecords = new ArrayList<>();
//		allCustomRecords = pir.AllDataCustom(pid);
		System.out.println("The pid value is ------------------------------------>"+pid);
		return pir.AllDataCustom(pid);
	}
	
	
	
	public List<Object[]> findCustomData(int pid)
	{
		List<Object[]> allCustomRecords = new ArrayList<>();
    	allCustomRecords = pir.findCustomData(pid);
    	return allCustomRecords;
		
	}
	
	
	

}
