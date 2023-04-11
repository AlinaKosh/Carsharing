package com.project.carshar.services;

import com.project.carshar.model.Sum;
import com.project.carshar.repositories.SumRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
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
    public void delete(Sum sum) {
        repository.delete(sum);
    }

    @Transactional
    public void save(Sum sum) throws Exception {
            repository.save(sum);
    }
}