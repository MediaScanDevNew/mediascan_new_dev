package com.pradeep.pj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeep.pj.repo.Markscan_usersRepository;

@Service
public class Markscan_usersService
{
	@Autowired
	Markscan_usersRepository mur;
	public List<Object[]> findUserDetails(int id)
	{
		List<Object[]> getDetails =new ArrayList<Object[]>();
		getDetails=mur.findAllCustomQuery(id);
		return getDetails;
	}
	
	
	

}
