package com.project.carshar.repositories;

import com.project.carshar.model.Car;
import com.project.carshar.model.Firm;
import com.project.carshar.model.Order;
import com.project.carshar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findById(long id);
    Iterable<Order> findAllByUser(User user);
    @Query(
            value = "SELECT o.sum FROM Order o where o.id=?1")
            //value = "SELECT sum FROM order_sum where order_id=?",
            //nativeQuery = true)
    Double getSum(long id);

    @Query("select o.user from Order o where o.id = :orderId")
    User findUserByOrderId(@Param("orderId") long orderId);

    @Query("select SUM(o.statement) from OrderReturn o where o.user.id = :userId")
    double findBySumCoefficient(@Param("userId") long userId);

    @Query("select count(o.statement) from OrderReturn o where o.user.id = :userId and o.statement<1")
    int countGoodStatement(@Param("userId") long userId);

    @Query("select count(o.statement) from OrderReturn o where o.user.id = :userId and o.statement>1")
    int countBadStatement(@Param("userId") long userId);
}
