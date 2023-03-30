package com.project.carshar.repositories;

import com.project.carshar.model.Sum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SumRepository extends JpaRepository<Sum, Long> {
    Sum findById(long id);
}

