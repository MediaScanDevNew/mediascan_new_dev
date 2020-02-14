/**
 * 
 */
package com.pradeep.pj.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pradeep.pj.model.Markscan_machine;

/**
 * @author pradeep
 *
 */
public interface Markscan_machineRepository extends CrudRepository<Markscan_machine, String> {

	@Modifying(clearAutomatically = true)
	@Query(value = "update markscan_machine set status = 1 where ip_address= (:myIP)", nativeQuery = true)
	public void machineEngage(@Param("myIP") String myIP);

	@Modifying(clearAutomatically = true)
	@Query(value = "update markscan_machine set status = 0 where ip_address=  (:myIP)", nativeQuery = true)
	public void machineFree(@Param("myIP") String myIP);

}
