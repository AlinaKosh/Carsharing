package com.project.carshar.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_return")
@Data
public class OrderReturn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "statement")
    private double statement;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
