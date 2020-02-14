/**
 * 
 */
package com.pradeep.pj.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pradeep.pj.model.Master_crawle_url;

/**
 * @author pradeep
 *
 */
public interface Master_crawle_urlRepository extends CrudRepository<Master_crawle_url, String> {

	@Modifying
	@Query(value = "insert into master_crawle_url (crawle_url2,project_id,user_id, stored_project_setup_id,"
			+ " pipe_id,machine, domain_name,title,page_no,page_rank) VALUES (:lnk,:prid,:uid,:sid,:ppid,:machine,:domain,:title,:page_no,:page_rank)", nativeQuery = true)
	@Transactional
	public Integer saveData(@Param("lnk") String lnk, @Param("prid") int prid, @Param("uid") int uid,
			@Param("sid") int sid, @Param("ppid") int ppid, @Param("machine") String machine,
			@Param("domain") String domain,@Param("title") String title,@Param("page_no") String page_no,@Param("page_rank") String page_rank);
	
	@Query(nativeQuery = true ,value="Select id,domain_name from master_crawle_url  where project_id=?1 and is_new=0")
	List<Object[]> getDomainProjectWise(int projectId);
	
	@Modifying
	@Query(nativeQuery = true ,value="UPDATE master_crawle_url set wh_list=1 where id =?1")
	@Transactional
	int updateWhitelistedDomain(int id);
	
	@Modifying
	@Query(nativeQuery = true ,value="UPDATE master_crawle_url set wh_list=1,status=?1 where id =?2")
	@Transactional
	int updateWhitelistedDomainWithStatus(String msg,int id);
	
	@Modifying
	@Query(nativeQuery = true ,value="UPDATE master_crawle_url set is_new=1 where project_id =?1 and is_new=0")
	@Transactional
	int updateOtherStatus(int projectId);
	
	@Query(nativeQuery = true ,value="Select id,domain_name,crawle_url2,title from master_crawle_url  where project_id=?1 and wh_list=0 and is_new=1")
	List<Object[]> getNotWhitelistedDomain(int projectId);
	
	@Modifying
	@Query(nativeQuery = true ,value="UPDATE master_crawle_url set status=?1 where id =?2")
	@Transactional
	int updateBlacklistStatus(String msg,int id);
	
	@Modifying
	@Query(nativeQuery = true ,value="UPDATE master_crawle_url set g_list=1,status=?1 where id =?2")
	@Transactional
	int updateGreylistedDomain(String msg,int id);
	
	@Modifying
	@Query(nativeQuery = true ,value="UPDATE master_crawle_url set is_new_grey=1 where project_id =?1 and is_new_grey=0")
	@Transactional
	int updateGreyListStatus(int projectId);
	
	@Query(nativeQuery = true ,value="Select crawle_url2,user_id,project_id,domain_name,pipe_id,w_list,page_no,page_rank from master_crawle_url  where project_id=?1 and status=?2")
	List<Object[]> getStatusWiseDomain(int projectId,String msg);
}
