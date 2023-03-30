package com.project.carshar.services;

import com.project.carshar.model.Sum;
import com.project.carshar.repositories.SumRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class SumService implements ToUpperCase{
    private final SumRepository repository;


    public Iterable<Sum> findAll() {
        return repository.findAll();
    }

    public Sum findById(int id) {
        return repository.findById(id);
    }

    @Transactional
    public void delete(Sum object) {
        repository.delete(object);
    }

    @Transactional
    public void save(Sum object) throws Exception {
            repository.save(object);
    }
}