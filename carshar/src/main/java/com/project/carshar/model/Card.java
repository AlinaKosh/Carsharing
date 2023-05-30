package com.project.carshar.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "Card")
@Entity
@Data
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private State salonState = State.GOOD;
    @Enumerated(EnumType.STRING)
    private State kuzovState = State.GOOD;
    @Column(name = "fuel")
    private double fuel;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column(name = "time")
    private LocalDate timeWatch;
}
