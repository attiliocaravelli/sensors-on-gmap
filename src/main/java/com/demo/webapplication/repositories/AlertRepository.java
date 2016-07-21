package com.demo.webapplication.repositories;

import org.springframework.data.repository.CrudRepository;

import com.demo.webapplication.domain.Alert;

public interface AlertRepository extends CrudRepository<Alert, Integer>{

}
