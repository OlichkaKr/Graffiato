package ua.lviv.iot;

public class Military extends Plane {

    PlaneTypes planeTypes;
    private int ammunition;

    public Military(){}

    public Military(String name, PlaneTypes planeTypes, int capacity, double loadCapacity,
                    double fuelConsumption, double flightRange, int ammunition){
        super(name, capacity, loadCapacity, fuelConsumption, flightRange);
        setPlaneTypes(planeTypes);
        setAmmunition(ammunition);
    }

    public PlaneTypes getPlaneTypes() {
        return planeTypes;
    }

    public void setPlaneTypes(PlaneTypes planeTypes) {
        this.planeTypes = planeTypes;
    }

    public int getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(int ammunition) {
        this.ammunition = ammunition;
    }
}
