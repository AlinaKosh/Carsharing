package com.project.carshar.services;

import com.project.carshar.model.Firm;
import com.project.carshar.repositories.FirmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
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
    public void delete(Firm firm) {
        repository.delete(firm);
    }

    @Transactional
    public void save(Firm firm) throws Exception {
        if(repository.findByNameIgnoreCase(firm.getName())==null) {
            firm.setName(upper(firm.getName()));
            repository.save(firm);
            return;
        }
        throw new Exception("Фирма "+upper(firm.getName())+" уже существует");
    }
}