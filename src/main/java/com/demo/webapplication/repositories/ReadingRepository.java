package com.demo.webapplication.repositories;

import org.springframework.data.repository.CrudRepository;

import com.demo.webapplication.domain.Reading;

public interface ReadingRepository extends CrudRepository<Reading, Integer>{
	
}
