package com.project.carshar.services;

import com.project.carshar.model.Car;
import com.project.carshar.repositories.CarRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
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
    public void delete(Car car) throws Exception {
            repository.delete(car);
    }

    @Transactional
    public void save(Car car) throws Exception{
        if (repository.findByNumber(car.getNumber())==null||repository.findByNumber(car.getNumber()).getId()==car.getId()) {
            repository.save(car);
            return;
        }
        throw new Exception("Такой номер принадлежит другому автомобилю");
    }
}