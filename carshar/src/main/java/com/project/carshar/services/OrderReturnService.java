package com.project.carshar.services;

import com.project.carshar.model.OrderReturn;
import com.project.carshar.repositories.OrderReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderReturnService {

    private final OrderReturnRepository orderReturnRepository;

    @Autowired
    public OrderReturnService(OrderReturnRepository orderReturnRepository) {
        this.orderReturnRepository = orderReturnRepository;
    }

    @Transactional
    public void save(OrderReturn orderReturn){
        orderReturnRepository.save(orderReturn);
    }

    @Transactional
    public List<OrderReturn> findAllByUserId(Long userId){
        return orderReturnRepository.findAllByUserId(userId);
    }
}
