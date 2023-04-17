package com.project.carshar.services;

import com.project.carshar.exception.CarAlreadyException;
import com.project.carshar.model.Firm;
import com.project.carshar.model.Order;
import com.project.carshar.model.User;
import com.project.carshar.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository repository;

    public List<Order> findAll() {
        return repository.findAll();
    }
    public List<Order> findAllByUser(User user) {
        return (List<Order>) repository.findAllByUser(user);
    }

    public Order findById(long id) {
        return repository.findById(id);
    }

    @Transactional
    public void deleteById(long id){
        repository.deleteById(id);
    }

    @Transactional
    public void delete(Order order) {
        repository.delete(order);
    }

    @Transactional
    public void save(Order order)throws Exception{

        /*
        List<Order> conflictBooking = repository.findByCar_IdAndDateBeforeAndReturnedAfter(order.getCar().getId(),
                order.getDate(), order.getReturned());
        if (!conflictBooking.isEmpty()){
            throw new CarAlreadyException("Эта машина уже была забронирована");
        }

         */
        /*
        LocalDate date = order.getDate();
        LocalDate returned = order.getReturned();
        long id = order.getCar().getId();
        List<Order> conflictBooking = repository.findConflictingBookings(date, returned, id);
        if (!conflictBooking.isEmpty()){
            throw new CarAlreadyException("Эта машина уже была забронирована");
        }
         */
        /*
        boolean isAvailable = repository.findByCar_IdAndDateBeforeAndReturnedAfter(order.getCar().getId(),
                order.getDate(), order.getReturned()).isEmpty();
        if (!isAvailable){
            throw new CarAlreadyException("Эта машина уже была забронирована");
        }else {

         */
            int sum = order.getCar().getCostPerDay() * order.getDays();
            order.setSum(sum);
            repository.save(order);

    }
    public int getSum(long id){
        return repository.getSum(id);
    }
    public long getId(){
        return repository.getId();
    }

    public boolean isCarAvailable(long id, LocalDate pickupDate, LocalDate returnDate){
        List<Order> orders = repository.findByCarIdAndReturnedAfter(id, pickupDate);
        for (Order order: orders){
            if (order.getDate().isBefore(returnDate)){
                return false;
            }
        }
        return true;
    }

}