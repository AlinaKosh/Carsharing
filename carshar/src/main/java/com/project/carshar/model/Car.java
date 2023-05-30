package com.project.carshar.model;


import jdk.dynalink.linker.LinkerServices;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="firm")
    private Firm firm;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="type")
    private Type type;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="transmission")
    private Transmission transmission;
    @Column(name = "insurance")
    private int insurance;
    @Column(name = "cost_per_day")
    private int costPerDay;

    @OneToMany(mappedBy = "id")
    private List<Card> card;
    public Car(){}

    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(int costPerDay) {
        this.costPerDay = costPerDay;
    }

    public int getInsurance() {
        return insurance;
    }

    public void setInsurance(int insurance) {
        this.insurance = insurance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Firm getFirm() {
        return firm;
    }

    public void setFirm(Firm firm) {
        this.firm = firm;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }


    @Override
    public String toString() {
        return number+", "+firm.name+", "+type.getName()+", "+transmission.getName();
    }
}
