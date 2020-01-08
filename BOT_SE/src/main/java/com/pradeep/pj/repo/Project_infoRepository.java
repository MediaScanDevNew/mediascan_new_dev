/**
 * 
 */
package com.pradeep.pj.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pradeep.pj.model.Project_info;

/**
 * @author pradeep
 *
 */
public interface Project_infoRepository extends CrudRepository<Project_info, String>{
	
	@Query(value = "select project_name from project_info where id=(:pid)", nativeQuery = true)
	public String AllDataCustom(@Param("pid") int pid);
	
	@Query(value = "select project_name,client_type from project_info where id=(:pid)", nativeQuery = true)
	public List<Object[]> findCustomData(@Param("pid") int pid);
	
	@Query(nativeQuery = true ,value="Select client_type from project_info  where id=?1")
	List<Integer> getClientByProjectId(int projectId);
	
	@Query(nativeQuery = true ,value="Select project_name from project_info  where id=?1")
	String getNameByProjectId(int projectId);

}
