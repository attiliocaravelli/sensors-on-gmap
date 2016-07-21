package com.demo.webapplication.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.webapplication.domain.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	
	@Query("from User where SSO_ID = :ssoID")
	public User findBySSO(@Param("ssoID") String ssoID);
}
