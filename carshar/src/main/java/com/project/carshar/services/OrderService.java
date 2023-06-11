package com.project.carshar.services;

import com.project.carshar.model.Order;
import com.project.carshar.model.User;
import com.project.carshar.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository repository;
    private final OrderReturnService orderReturnService;

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
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void delete(Order order) {
        repository.delete(order);
    }

    @Transactional
    public void save(Order order) throws Exception {

        /*
        int sum = order.getCar().getCostPerDay() * order.getDays();
        order.getUser().setOrderReturns(orderReturnService.findAllByUserId(order.getUser().getId()));
        System.out.println(orderReturnService.findAllByUserId(order.getUser().getId()));
        order.setSum(sum * order.getUser().getCof());
        System.out.println(order.getUser().getCof());
        repository.save(order);

        //NEW
        int sum = order.getCar().getCostPerDay() * order.getDays();
        order.setSum((double) sum);
        repository.save(order);

         */

        int sum = order.getCar().getCostPerDay() * order.getDays();

        if (countGoodStatement(order.getUser().getId()) > countBadStatement(order.getUser().getId())) {
            order.setSum(Math.abs(sum - (sum * findBySumCoefficient(order.getUser().getId()))));
            order.getUser().setOrderReturns(orderReturnService.findAllByUserId(order.getUser().getId()));

            System.out.println(countGoodStatement(order.getUser().getId()));
            System.out.println(countBadStatement(order.getUser().getId()));
            System.out.println(sum - (sum * findBySumCoefficient(order.getUser().getId())));
            System.out.println(findBySumCoefficient(order.getUser().getId()));
        } else if (countGoodStatement(order.getUser().getId()) <= countBadStatement(order.getUser().getId())) {
            order.setSum(Math.abs(sum + (sum * findBySumCoefficient(order.getUser().getId()))));
            order.getUser().setOrderReturns(orderReturnService.findAllByUserId(order.getUser().getId()));

            System.out.println(countGoodStatement(order.getUser().getId()));
            System.out.println(countBadStatement(order.getUser().getId()));
            System.out.println(countGoodStatement(order.getUser().getId()) <= countBadStatement(order.getUser().getId()));
            System.out.println(sum + (sum * findBySumCoefficient(order.getUser().getId())));
            System.out.println(findBySumCoefficient(order.getUser().getId()));
        }

        repository.save(order);
    }

    public double findBySumCoefficient(long id) {
        OptionalDouble optionalDouble = OptionalDouble.of(repository.findBySumCoefficient(id)).stream().average();
        return 1 / optionalDouble.orElse(1.0);
    }

    public int countGoodStatement(long id) {
        OptionalInt optionalInt = OptionalInt.of(repository.countGoodStatement(id));
        return optionalInt.orElse(1);
    }

    public int countBadStatement(long id) {
        OptionalInt optionalInt = OptionalInt.of(repository.countBadStatement(id));
        return optionalInt.orElse(1);
    }

    public User findUserByOrderId(long id) {
        return repository.findUserByOrderId(id);
    }

    public int sale(long userId){
        if (countGoodStatement(userId) > countBadStatement(userId)){
            double round = Math.round(findBySumCoefficient(userId) * 100.0);
            return (int) round;
        } else return 0;
    }

    public Double getSum(long id) {
        return repository.getSum(id);
    }

    /*
    public long getId() {
        return repository.getId();
    }
     */

}