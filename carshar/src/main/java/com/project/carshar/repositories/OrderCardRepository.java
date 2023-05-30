package com.project.carshar.repositories;

import com.project.carshar.model.OrderCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderCardRepository extends JpaRepository<OrderCard, Long> {
}
