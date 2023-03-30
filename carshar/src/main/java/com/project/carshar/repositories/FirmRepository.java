package com.project.carshar.repositories;

import com.project.carshar.model.Firm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirmRepository extends JpaRepository<Firm, Integer> {
    Firm findById(int id);

    Firm findByNameIgnoreCase(String name);
}
