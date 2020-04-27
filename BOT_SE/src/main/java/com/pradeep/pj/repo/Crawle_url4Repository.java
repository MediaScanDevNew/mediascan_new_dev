package com.pradeep.pj.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface Crawle_url4Repository extends CrudRepository<com.pradeep.pj.model.Crawle_url4, String> {
	
	@Modifying
	@Query(value = "insert into crawle_url4 (crawle_url2,project_id,user_id,w_list,"
			+ " pipe_id,domain_name,page_no,page_rank) VALUES (:lnk,:prid,:uid,:wlist,:ppid,:domain,:page_no,:page_rank)", nativeQuery = true)
	@Transactional
	public Integer saveData(@Param("lnk") String lnk, @Param("prid") int prid, @Param("uid") int uid,
			@Param("wlist") int wlist, @Param("ppid") int ppid, 
			@Param("domain") String domain,@Param("page_no") String page_no,@Param("page_rank") String page_rank);
}
