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

    List<Order> findByCar_IdAndDateBeforeAndReturnedAfter(long id, LocalDate date, LocalDate returned);

    List<Order> findByCarIdAndReturnedAfter(long carId, LocalDate returned);

    /*
    @Query(
            value = "select test()",
            nativeQuery = true)
    long getId();


    @Query("select b from Order b where b.car.id = : id and ((b.date>=:date and b.date<:returned) or (b.returned>:date and b.returned<=:returned))")
    List<Order> findConflictingBookings(@Param("date")LocalDate date, @Param("returned")LocalDate returned, @Param("id") long id);

     */
}
