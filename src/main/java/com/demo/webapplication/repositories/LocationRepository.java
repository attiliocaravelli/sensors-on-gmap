package com.demo.webapplication.repositories;

import org.springframework.data.repository.CrudRepository;

import com.demo.webapplication.domain.Location;

public interface LocationRepository extends CrudRepository<Location, Integer>{
}
