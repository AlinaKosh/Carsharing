package com.project.carshar.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(name = "OrderCard")
@Entity
@Data
public class OrderCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "order")
    private Order order;
    @OneToOne
    @JoinColumn(name = "card_id")
    private Card card;
}
