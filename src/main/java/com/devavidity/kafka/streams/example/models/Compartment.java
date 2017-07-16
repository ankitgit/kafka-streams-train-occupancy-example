package com.devavidity.kafka.streams.example.models;

public class Compartment {

    private int vehicleId;
    private int in;
    private int out;
    private int capacity;

    public Compartment(int vehicleId, int in, int out, int capacity) {
        this.vehicleId = vehicleId;
        this.in = in;
        this.out = out;
        this.capacity = capacity;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


}
