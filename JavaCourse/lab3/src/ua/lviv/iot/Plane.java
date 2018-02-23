package ua.lviv.iot;

public class Plane {
    private String name = "no name";
    private double loadCapacity;
    private int capacity;
    private double fuelConsumption;
    private double flightRange;

    public Plane(){}

    public Plane(String name, int capacity, double loadCapacity, double fuelConsumption, double flightRange){
        setName(name);
        setCapacity(capacity);
        setLoadCapacity(loadCapacity);
        setFuelConsumption(fuelConsumption);
        setFlightRange(flightRange);
    }

    public void resetValues(String name, int capacity, double loadCapacity, double fuelConsumption, double flightRange){
        setName(name);
        setCapacity(capacity);
        setLoadCapacity(loadCapacity);
        setFuelConsumption(fuelConsumption);
        setFlightRange(flightRange);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getFlightRange() {
        return flightRange;
    }

    public void setFlightRange(double flightRange) {
        this.flightRange = flightRange;
    }
}
