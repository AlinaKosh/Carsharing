package com.project.carshar.repositories;

import com.project.carshar.model.OrderReturn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderReturnRepository extends JpaRepository<OrderReturn, Long> {

    List<OrderReturn> findAllByUserId(Long userId);
}
