package com.project.carshar.services;

import com.project.carshar.model.Type;
import com.project.carshar.repositories.TypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
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
    public void delete(Type object) {
        repository.delete(object);
    }
    @Transactional
    public void save(Type object) throws Exception {
        if(repository.findByNameIgnoreCase(object.getName())==null) {
            object.setName(upper(object.getName()));
            repository.save(object);
            return;
        }
        throw new Exception("Тип "+upper(object.getName())+" уже существует");
    }
}
