package com.pradeep.pj.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.pradeep.pj.model.Greylist;

public interface Greylist_Repository extends CrudRepository<Greylist, Integer>{
	
	@Query(nativeQuery = true ,value="Select domain from greylist ")
	String[] getClientwiseGreylist();
}
