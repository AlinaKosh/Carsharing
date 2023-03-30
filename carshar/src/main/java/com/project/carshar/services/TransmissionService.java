package com.project.carshar.services;

import com.project.carshar.model.Transmission;
import com.project.carshar.repositories.TransmissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
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
    public void delete(Transmission object) {
        repository.delete(object);
    }

    @Transactional
    public void save(Transmission object) throws Exception {
        if(repository.findByNameIgnoreCase(object.getName())==null) {
            object.setName(upper(object.getName()));
            repository.save(object);
            return;
        }
        throw new Exception("Тип коробки передач "+upper(object.getName())+" уже существует");
    }
}