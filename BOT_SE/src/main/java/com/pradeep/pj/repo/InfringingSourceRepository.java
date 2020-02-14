/**
 * 
 */
package com.pradeep.pj.repo;

import org.springframework.data.repository.CrudRepository;

import com.pradeep.pj.model.Infringing_source;

/**
 * @author pradeep
 *
 */
public interface InfringingSourceRepository extends CrudRepository<Infringing_source, String> {

//	@Query(value = "select infs.id, infs.projectid, infs.userid, infs.search_keyword, infs.infringing_link_by_date,"
//			+ " infs.infringing_link, infs.source_link from infringing_source infs where infs.userid = (:uid) "
//			+ " and infs.infi_time = '(:date)' ", nativeQuery = true)
//	List<Object[]> findAllCustomQuery(int uid, String date__);

}
