package com.demo.webapplication.services;

public interface AbstractService<T> {
    Iterable<T> listAll();

    T getById(Integer id);

    T save(T t);
}
