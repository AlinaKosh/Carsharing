package com.project.carshar.model;

import javax.persistence.*;

@Entity
@Table(name = "order_sum")
public class Sum {
    @Id
    @Column(name = "order_id")
    private int id;

    @Column(name = "sum")
    private int sum;

    public Sum(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
