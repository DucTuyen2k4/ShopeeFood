package com.example.shopeefood.service;

import java.util.Optional;

public interface IGenerateService<T>{
    Iterable<T> findAll();
    T save(T t);
    void remove(Long id);
    Optional<T> findById(Long id);
}
