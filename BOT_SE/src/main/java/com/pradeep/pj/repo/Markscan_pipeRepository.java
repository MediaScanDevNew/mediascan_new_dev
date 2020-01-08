/**
 * 
 */
package com.pradeep.pj.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pradeep.pj.model.Markscan_pipe;

/**
 * @author pradeep
 *
 */
public interface Markscan_pipeRepository extends CrudRepository<Markscan_pipe, String>{

	@Query(value = "select domain from markscan_pipe where id=(:pid)", nativeQuery = true)
	public String AllDataCustom(@Param("pid") int pid);
}
