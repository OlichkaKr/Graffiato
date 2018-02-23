package ua.lviv.iot;

public class Civil extends Plane {

    public PlaneTypes planeTypes;

    private int passengers;

    public Civil(){}
    public Civil(String name, PlaneTypes planeTypes, int capacity, double loadCapacity, double fuelConsumption, double flightRange, int passengers){
        super(name, capacity, loadCapacity, fuelConsumption, flightRange);
        setPlaneTypes(planeTypes);
        setPassengers(passengers);
    }

    public PlaneTypes getPlaneTypes() {
        return planeTypes;
    }

    public void setPlaneTypes(PlaneTypes planeTypes) {
        this.planeTypes = planeTypes;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }
}
