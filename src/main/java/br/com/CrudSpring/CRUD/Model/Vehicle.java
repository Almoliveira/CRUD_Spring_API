package br.com.CrudSpring.CRUD.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vehicle {

    @GeneratedValue
    @Id
    private int id;

    @Column(name="licensePlate")
    private String licensePlate;

    @Column(name="model")
    private String model;

    @Column(name="maxPassengers")
    private int maxPassengers;

    @Column(name="lastKm")
    private int lastKm;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public void setMaxPassengers(int maxPassengers) {
        this.maxPassengers = maxPassengers;
    }

    public int getLastKm() {
        return lastKm;
    }

    public void setLastKm(int lastKm) {
        this.lastKm = lastKm;
    }
}
