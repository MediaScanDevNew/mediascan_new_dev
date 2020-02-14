/**
 * 
 */
package com.pradeep.pj.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pradeep.pj.model.Blacklist_sites;


/**
 * @author pradeep
 *
 */
public interface Blacklist_sitesRepository extends CrudRepository<Blacklist_sites, String>{

//	@Modifying(clearAutomatically = true)
//	@Query(value = "update crawle_url3 set qc_new=1, w_list_perform=1 WHERE id in (:id)", nativeQuery = true)
//	int allUpdateQuery(@Param("id") List<Integer> id);
	
	@Query(value = "select domain from blacklist_sites", nativeQuery = true)
	public List<Object []> findAllCustomQuery();
	@Query(value = "select domain from blacklist_sites where is_active=0", nativeQuery = true)
	public String[] findAllActiveBlacklists();
	@Query(value = "select distinct domain from blacklist_sites where project_type=?1 and is_active=0", nativeQuery = true)
	public String[] findAllActiveBlacklistsTypeWise(int project_type);
}
