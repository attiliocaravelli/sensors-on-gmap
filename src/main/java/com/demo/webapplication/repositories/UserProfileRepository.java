package com.demo.webapplication.repositories;

import org.springframework.data.repository.CrudRepository;

import com.demo.webapplication.domain.UserProfile;

public interface UserProfileRepository extends CrudRepository<UserProfile, Integer>{

}
