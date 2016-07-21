package com.demo.webapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.webapplication.domain.Sensor;
import com.demo.webapplication.repositories.SensorRepository;


@Service
public class SensorService implements AbstractService<Sensor> {
	@Autowired
	private SensorRepository sensorRepository;

	@Override
	public Iterable<Sensor> listAll() {
		return sensorRepository.findAll();
	}

	@Override
	public Sensor getById(Integer id) {
		return sensorRepository.findOne(id);
	}

	@Override
	public Sensor save(Sensor t) {
		return sensorRepository.save(t);
	}
}
