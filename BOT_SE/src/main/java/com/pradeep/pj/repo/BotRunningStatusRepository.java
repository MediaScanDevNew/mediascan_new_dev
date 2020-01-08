package com.pradeep.pj.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pradeep.pj.model.BotRunningStatus;
//import com.pradeep.pj.model.Master_crawle_url;

public interface BotRunningStatusRepository extends CrudRepository<BotRunningStatus, String>{
	
	
	@Modifying
	@Query(value = "insert into bot_running_status (userName,uemail,projectName, keyphrase,"
			+ "machine, mailSend ) VALUES (:uname,:umail,:pname,:kph,:machine,0)", nativeQuery = true)
	@Transactional
	public Integer saveData(@Param("uname") String uname, @Param("umail") String umail, @Param("pname") String pname,
			@Param("kph") String kph, @Param("machine") String machine);
	@Query(value = "update bot_running_status set mailSend=1", nativeQuery = true)
	public boolean mailSendUpdate();
	/*
	@Query(value = "select userName,uemail,projectName, keyphrase from bot_running_status where machine=(:ip)", nativeQuery = true)
	publ
	*/

}
