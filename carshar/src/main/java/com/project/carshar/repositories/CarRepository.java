package com.project.carshar.repositories;

import com.project.carshar.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
    Car findById(long id);
    Car findByNumber(String number);
}
