package com.project.carshar.repositories;

import com.project.carshar.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRepository extends JpaRepository<Tax, Long> {
    Tax findById(long id);
}
