package com.project.carshar.services;

import com.project.carshar.model.Type;
import com.project.carshar.repositories.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TypeService implements ToUpperCase{
    private final TypeRepository repository;

    public Iterable<Type> findAll() {
        return repository.findAll();
    }

    public Type findById(int id) {
        return repository.findById(id);
    }

    @Transactional
    public void delete(Type type) {
        repository.delete(type);
    }
    @Transactional
    public void save(Type type) throws Exception {
        if(repository.findByNameIgnoreCase(type.getName())==null) {
            type.setName(upper(type.getName()));
            repository.save(type);
            return;
        }
        throw new Exception("Тип "+upper(type.getName())+" уже существует");
    }
}
