package com.project.carshar.services;

import com.project.carshar.model.Tax;
import com.project.carshar.repositories.TaxRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class TaxService {

    private final TaxRepository repository;

    public Iterable<Tax> findAll() {
        return repository.findAll();
    }

    public Tax findById(long id) {
        return repository.findById(id);
    }

    @Transactional
    public void delete(Tax object) {
        repository.delete(object);
    }

    @Transactional
    public void save(Tax object) throws Exception{
        repository.save(object);
    }
}