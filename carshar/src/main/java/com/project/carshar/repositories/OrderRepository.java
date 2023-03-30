package com.project.carshar.repositories;

import com.project.carshar.model.Order;
import com.project.carshar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findById(long id);
    Iterable<Order> findAllByUser(User user);
    @Query(
            value = "SELECT sum FROM order_sum where order_id=?",
            nativeQuery = true)
    int getSum(long id);
    @Query(
            value = "select test()",
            nativeQuery = true)
    long getId();
}
