package com.pradeep.pj.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pradeep.pj.model.Client_master;



public interface Client_MasterRepository extends CrudRepository<Client_master, String> {
	@Query(value = "select client_name, email from client_master where id=(:cid)", nativeQuery = true)
	public List <Object[]> AllDataCustom(@Param("cid") int cid);

}
