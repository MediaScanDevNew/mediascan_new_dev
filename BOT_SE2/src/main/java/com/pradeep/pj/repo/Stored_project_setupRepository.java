/**
 * 
 */
package com.pradeep.pj.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pradeep.pj.model.Stored_project_setup;

/**
 * @author pradeep
 *
 */
public interface Stored_project_setupRepository extends CrudRepository<Stored_project_setup, String> {

	/*
	@Query(value = "select sps.id,sps.keyphrase,sps.machine,sps.pipe,sps.preority,sps.userId,sps.projectId,sps.bls "
			+ " from stored_project_setup as sps  where  sps.completed=0 and sps.deleted=0 and sps.machine = :ip "
			+ " order by sps.preority asc ", nativeQuery = true)
			*/
	@Query(value = "select sps.id,sps.keyphrase,sps.pipe, sps.userId,sps.projectId "
			+ " from stored_project_setup2 as sps  where  sps.completed=0  "
			+ " order by sps.priority desc ", nativeQuery = true)
	//id asc
	
	//public List<Object[]> findAllCustomQuery(@Param("ip") String myIP);
	public List<Object[]> findAllCustomQuery();
	@Query(value="select sps.keyphrase "
			+ " from stored_project_setup2 sps where completed=0 and sps.id=(:id) ", nativeQuery = true)
	public String findKeyPhrase(@Param("id") int id);

	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set completed=2,google=2 where id= (:id)", nativeQuery = true)
	int googleStart(@Param("id") int id);

	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set google=1 , yahoo=2 where id= (:id)", nativeQuery = true)
	int googlecomplate(@Param("id") int id);

	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set yahoo=1, bing=2 where id=(:id)", nativeQuery = true)
	int yahoocomplate(@Param("id") int id);

	/*@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup1 set bing=1 where id= (:id)", nativeQuery = true)
	int bingcomplate(@Param("id") int id);*/

	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set completed=2,google=2,yahoo=3,bing=3 where id= (:id)", nativeQuery = true)
	int onlyGoogleOversiesStart(@Param("id") int id);

	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set google=1  where id= (:id)", nativeQuery = true)
	int onlyGoogleOversiesComplete(@Param("id") int id);

	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set completed=1 where id= (:id)", nativeQuery = true)
	int allComplete(@Param("id") int id);
	
	
	
	//------------------------ 20.12.2019 -----------------------------------------
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set bing=1,duckduckgo=2 where id= (:id)", nativeQuery = true)
	int bingcomplate(@Param("id") int id);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set duckduckgo=1,russiago=2 where id= (:id)", nativeQuery = true)
	int duckduckGocomplate(@Param("id") int id);
	
	/** Added on Jan/27 to track end of Russian Search engine crawl **/
	/** End of Russian Search Engine */
	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set russiago=1 where id= (:id)", nativeQuery = true)
	int russiacomplete(@Param("id") int id);

}
