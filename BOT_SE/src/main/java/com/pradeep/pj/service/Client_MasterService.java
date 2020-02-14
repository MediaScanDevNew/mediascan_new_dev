package com.pradeep.pj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeep.pj.repo.Client_MasterRepository;

@Service
public class Client_MasterService
{
	@Autowired
	Client_MasterRepository cmr;
	
	public List<Object[]> findCustomData(int ctype)
	{
		List<Object[]> allCustomRecords = new ArrayList<>();
    	allCustomRecords = cmr.AllDataCustom(ctype);
    	return allCustomRecords;
		
	}
	

}
