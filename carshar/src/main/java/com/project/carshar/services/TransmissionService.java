package com.project.carshar.services;

import com.project.carshar.model.Transmission;
import com.project.carshar.repositories.TransmissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransmissionService implements ToUpperCase{
    private final TransmissionRepository repository;

    public Iterable<Transmission> findAll() {
        return repository.findAll();
    }

    public Transmission findById(int id) {
        return repository.findById(id);
    }

    @Transactional
    public void delete(Transmission transmission) {
        repository.delete(transmission);
    }

    @Transactional
    public void save(Transmission transmission) throws Exception {
        if(repository.findByNameIgnoreCase(transmission.getName())==null) {
            transmission.setName(upper(transmission.getName()));
            repository.save(transmission);
            return;
        }
        throw new Exception("Тип коробки передач "+upper(transmission.getName())+" уже существует");
    }
}