package com.demo.webapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.webapplication.domain.Location;
import com.demo.webapplication.repositories.LocationRepository;

@Service
public class LocationService implements AbstractService<Location> {
	@Autowired
	private LocationRepository locationRepository;

	@Override
	public Iterable<Location> listAll() {
		return locationRepository.findAll();
	}

	@Override
	public Location getById(Integer id) {
		return locationRepository.findOne(id);
	}

	@Override
	public Location save(Location t) {
		return locationRepository.save(t);
	}
}
