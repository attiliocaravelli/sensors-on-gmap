package com.demo.webapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.webapplication.domain.UserProfile;
import com.demo.webapplication.repositories.UserProfileRepository;

@Service
public class UserProfileService implements AbstractService<UserProfile>{
	
	@Autowired
	private UserProfileRepository userProfileRepository;
	
	@Override
	public Iterable<UserProfile> listAll() {
		return userProfileRepository.findAll();
	}

	@Override
	public UserProfile getById(Integer id) {
		return userProfileRepository.findOne(id);
	}

	@Override
	public UserProfile save(UserProfile t) {
		return userProfileRepository.save(t);
	}
}

