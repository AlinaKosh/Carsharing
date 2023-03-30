package com.project.carshar.services;

import com.project.carshar.model.Order;
import com.project.carshar.model.User;
import com.project.carshar.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
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
    public void delete(Order object) {
        repository.delete(object);
    }

    @Transactional
    public void save(Order object)throws Exception{
        repository.save(object);
    }
    public int getSum(long id){
        return repository.getSum(id);
    }
    public long getId(){
        return repository.getId();
    }
}