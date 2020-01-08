package com.pradeep.pj.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pradeep.pj.model.Project_info;
import com.pradeep.pj.model.Whitelist;



public interface Whitelist_Repository extends CrudRepository<Whitelist,Integer>{
	
	@Query(nativeQuery = true ,value="Select domain_name from whitelist  where clientId=?1")
	String[] getClientwiseWhitelist(int clientId);
}
