package com.demo.webapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.webapplication.domain.User;
import com.demo.webapplication.repositories.UserRepository;

@Service
public class UserService implements AbstractService<User> {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Iterable<User> listAll() {
		return userRepository.findAll();
	}

	@Override
	public User getById(Integer id) {
		return userRepository.findOne(id);
	}

	@Override
	public User save(User t) {
		return userRepository.save(t);
	}
	
	public User findBySso(String sso) {
		return userRepository.findBySSO(sso);
	}
}

