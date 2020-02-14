/**
 * 
 */
package com.pradeep.pj.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pradeep.pj.model.Keyword_filter;

/**
 * @author pradeep
 *
 */
public interface Keyword_filterRepository extends CrudRepository<Keyword_filter, String> {

	@Query(value = "select keyword from keyword_filter where project_id=(:pid)", nativeQuery = true)
	public List<String> findAllCustomQuery(@Param("pid") int pid);

}
