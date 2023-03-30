package com.project.carshar.services;

import com.project.carshar.model.Firm;
import com.project.carshar.repositories.FirmRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class FirmService implements ToUpperCase{
    private final FirmRepository repository;


    public Iterable<Firm> findAll() {
        return repository.findAll();
    }

    public Firm findById(int id) {
        return repository.findById(id);
    }

    @Transactional
    public void delete(Firm object) {
        repository.delete(object);
    }

    @Transactional
    public void save(Firm object) throws Exception {
        if(repository.findByNameIgnoreCase(object.getName())==null) {
            object.setName(upper(object.getName()));
            repository.save(object);
            return;
        }
        throw new Exception("Фирма "+upper(object.getName())+" уже существует");
    }
}