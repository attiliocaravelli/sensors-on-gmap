package com.demo.webapplication.repositories;

import org.springframework.data.repository.CrudRepository;

import com.demo.webapplication.domain.Sensor;

public interface SensorRepository extends CrudRepository<Sensor, Integer>{
}
