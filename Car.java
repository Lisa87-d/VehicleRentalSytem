/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.vehiclerentalsytem;

/**
 *
 * @author air
 */
class Car {

    private String name;
    private double pricePerDay;

    public Car(String name, double pricePerDay) {
        this.name = name;
        this.pricePerDay = pricePerDay;
    }

    public String getName() {
        return name;
    }

    public double getPricePerDay() {
        return pricePerDay; 
    }
    
}
