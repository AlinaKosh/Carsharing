package com.project.carshar.repositories;

import com.project.carshar.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    Car findById(long id);
    Car findByNumber(String number);
    @Query(value = "select * from free_cars", nativeQuery = true)
    List<Car> findFreeCars();
}
