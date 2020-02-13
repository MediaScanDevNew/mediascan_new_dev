package com.pradeep.pj.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pradeep.pj.model.Crawle_url4;
import com.pradeep.pj.repo.Crawle_url4Repository;

@Service
public class Crawle_url4RepoIMPL {

	public Crawle_url4RepoIMPL() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Autowired
	private Crawle_url4Repository crawleRepo;
	
	public void addData(Crawle_url4 crawleUrl){
		crawleRepo.save(crawleUrl);
	}
}
