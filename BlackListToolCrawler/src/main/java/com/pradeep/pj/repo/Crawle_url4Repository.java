package com.pradeep.pj.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface Crawle_url4Repository extends CrudRepository<com.pradeep.pj.model.Crawle_url4, String> {
	
	@Modifying
	@Query(value = "insert into crawle_url4 (crawle_url2,project_id,user_id,w_list"
			+ ",domain_name,iwl_failed) VALUES (:lnk,:prid,:uid,:wlist,:machine,:domain,:title,:iwl_failed)", nativeQuery = true)
	@Transactional
	public Integer saveData(@Param("lnk") String lnk, @Param("prid") int prid, @Param("uid") int uid,
			@Param("wlist") int wlist,@Param("domain") String domain,@Param("iwl_failed") int iwl_failed);
	
	@Modifying
	@Query(nativeQuery = true ,value="UPDATE crawle_url4 set iwl_field=1 where id =?1 ")
	@Transactional
	int updateIWLErrorField(int id);
	
	@Query(nativeQuery = true ,value="Select id from crawle_url4  where crawle_url2=?1")
	int getIdByURL(String url);
}
