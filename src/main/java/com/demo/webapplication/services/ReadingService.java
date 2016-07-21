package com.demo.webapplication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.webapplication.domain.Reading;
import com.demo.webapplication.repositories.ReadingRepository;

@Service
public class ReadingService implements AbstractService<Reading> {
	@Autowired
	private ReadingRepository readingRepository;

	@Override
	public Iterable<Reading> listAll() {
		return readingRepository.findAll();
	}

	@Override
	public Reading getById(Integer id) {
		return readingRepository.findOne(id);
	}

	@Override
	public Reading save(Reading t) {
		return readingRepository.save(t);
	}
}
