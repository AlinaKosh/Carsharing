package com.project.carshar.services;

import com.project.carshar.model.Car;
import com.project.carshar.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CarService {
    private final CarRepository repository;

    public Iterable<Car> findAll() {
        return repository.findAll();
    }

    public Car findById(long id) {
        return repository.findById(id);
    }

    public void deleteById(long id){ repository.deleteById(id);}

    @Transactional
    public void delete(Car object) throws Exception {
            repository.delete(object);
    }

    @Transactional
    public void save(Car object) throws Exception{
        if (repository.findByNumber(object.getNumber())==null||repository.findByNumber(object.getNumber()).getId()==object.getId()) {
            repository.save(object);
            return;
        }
        throw new Exception("Такой номер принадлежит другому автомобилю");
    }
}