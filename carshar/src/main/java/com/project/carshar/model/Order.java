package com.project.carshar.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="\"user\"")
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="car")
    private Car car;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Column(name = "days")
    private int days;

    @Column(name = "returned")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returned;
    @Column(name = "sum")
    private Integer sum;


    public Order(){}
    public LocalDate getReturned() {
        return returned;
    }

    public void setReturned(LocalDate returned) {
        this.returned = returned;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", car=" + car +
                ", date=" + date +
                ", days=" + days +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}
