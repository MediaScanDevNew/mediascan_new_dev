/**
 * 
 */
package com.pradeep.pj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pradeep.pj.repo.Markscan_machineRepository;

/**
 * @author pradeep
 *
 */
@Service
public class Markscan_machineService {

	@Autowired
	Markscan_machineRepository mmr;
	
	@Transactional 
	public void machineStatusEngage(String myIP)throws InvalidDataAccessApiUsageException {
		  mmr.machineEngage(myIP);
	}
	@Transactional 
	public void machineStatusFree(String myIP)throws InvalidDataAccessApiUsageException {
		  mmr.machineFree(myIP);
	}

}
