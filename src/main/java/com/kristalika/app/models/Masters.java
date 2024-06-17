package com.kristalika.app.models;

import jakarta.persistence.*;

@Entity
@Table(name = "masters")
public class Masters {
    public Masters() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int pin;

    public Masters(String name, int pin) {
        this.name = name;
        this.pin = pin;
    }

    public Masters(Long id, String name, int pin) {
        this.id = id;
        this.name = name;
        this.pin = pin;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
