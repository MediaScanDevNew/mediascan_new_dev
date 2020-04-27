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
			+ " from stored_project_setup2 as sps  where  sps.projectId=(:pId) and sps.completed=0  "
			+ " order by sps.priority desc ", nativeQuery = true)
	public List<Object[]> findAllCustomQuery(@Param("pId") int pId);
	
	
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
	
	
	//------------------------ 15.02.2020 ---------------------------------------------------------------------------
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set completed=2,yahoo=2 where id= (:id)", nativeQuery = true)
	int yahooStart(@Param("id") int id);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set completed=2,bing=2 where id= (:id)", nativeQuery = true)
	int bingStart(@Param("id") int id);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set completed=2,duckduckgo=2 where id= (:id)", nativeQuery = true)
	int duckduckStart(@Param("id") int id);
	
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set completed=2,russiago=2 where id= (:id)", nativeQuery = true)
	int russiaGoStart(@Param("id") int id);
	
	
	
	@Query(value = " SELECT ss.keyphrase,pi.project_name,cm.client_name,mu.name,ss.id "
				   + "FROM project_info pi,stored_project_setup2 ss,markscan_users mu,client_master cm "
				   + "where pi.id = (:projectId) and pi.id = ss.projectId and ss.completed=1 and pi.created_by = mu.id "
				   + "and pi.client_type = cm.id and ss.mailflag=0 ", nativeQuery = true)
	public List<Object[]> findAllMailRecords(@Param("projectId") int projectId);
	
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set whitelist=2 where id= (:id)", nativeQuery = true)
	int whitelistStart(@Param("id") int id);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set whitelist=1,greylist=2 where id= (:id)", nativeQuery = true)
	int greylistStart(@Param("id") int id);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set greylist=1,blacklist=2 where id= (:id)", nativeQuery = true)
	int blacklistStart(@Param("id") int id);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set failedflag=1 where id= (:id)", nativeQuery = true)
	int failedFlag(@Param("id") int id);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set blacklist=1 where id= (:id)", nativeQuery = true)
	int blacklistComplate(@Param("id") int id);
	
	
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set google=1 where id= (:id)", nativeQuery = true)
	int googleCompleted(@Param("id") int id);
	
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set yahoo=1 where id= (:id)", nativeQuery = true)
	int yahooCompleted(@Param("id") int id);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set bing=1 where id= (:id)", nativeQuery = true)
	int bingCompleted(@Param("id") int id);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set duckduckgo=1 where id= (:id)", nativeQuery = true)
	int duckduckCompleted(@Param("id") int id);
	
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update stored_project_setup2 set russiago=1 where id= (:id)", nativeQuery = true)
	int russiaCompleted(@Param("id") int id);
	

}
