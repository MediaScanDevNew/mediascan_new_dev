package com.pradeep.pj.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.pradeep.pj.model.Markscan_users;
//import com.pradeep.pj.model.Stored_project_setup;
public interface Markscan_usersRepository extends CrudRepository<Markscan_users, String>
{
	@Query(value ="select email, user_name from markscan_users where id=:id", nativeQuery =true)
	public List<Object[]> findAllCustomQuery(@Param("id") int id);
	

}
