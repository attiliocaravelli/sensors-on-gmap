package com.demo.webapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.webapplication.domain.Alert;
import com.demo.webapplication.repositories.AlertRepository;

@Service
public class AlertService implements AbstractService<Alert> {
	@Autowired
	private AlertRepository alertRepository;

	@Override
	public Iterable<Alert> listAll() {
		return alertRepository.findAll();
	}
	
	@Override
	public Alert getById(Integer id) {
		return alertRepository.findOne(id);
	}

	@Override
	public Alert save(Alert t) {
		return alertRepository.save(t);
	}
}
