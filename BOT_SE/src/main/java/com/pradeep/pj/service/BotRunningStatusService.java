package com.pradeep.pj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeep.pj.repo.BotRunningStatusRepository;

@Service
public class BotRunningStatusService {
	@Autowired
	BotRunningStatusRepository brs;
	public Integer botStatusStoredData(String uname, String umail,String pname, String kph, String machine)
	{
		 return brs.saveData(uname, umail, pname, kph, machine);
	}
	/*
	public List<Object[]> findALLCustom(String ip) {
		System.out.println("~~~~~~~ipaddress---------"+ip);
		List<Object[]> allCustomRecords = new ArrayList<>();
		
		allCustomRecords = brs.findAllCustomQuery(ip);
		return allCustomRecords;
	}
	*/
	public boolean mailSendUpdate()
	{
		return brs.mailSendUpdate();
	}

}
