package com.project.carshar.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(name = "OrderCard")
@Entity
@Data
public class OrderCard {
    @Id
    private long id;
    @OneToOne
    @JoinColumn(name = "order")
    private Order order;
    @OneToMany(mappedBy = "id")
    private List<Card> card;
}
