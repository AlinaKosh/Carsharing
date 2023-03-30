package com.project.carshar.repositories;

import com.project.carshar.model.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransmissionRepository extends JpaRepository<Transmission, Integer> {

    Transmission findById(int id);
    Transmission findByNameIgnoreCase(String name);
}

