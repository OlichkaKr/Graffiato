package ua.lviv.iot;

public class Plane {
    private String name = "no name";
    private double loadCapacity;
    private int capacity;
    private double fuelConsumption;
    private double flightRange;

    public Plane(final String pName, final int pCapacity,
                 final double pLoadCapacity, final double pFuelConsumption,
                 final double pFlightRange) {
        setName(pName);
        setCapacity(pCapacity);
        setLoadCapacity(pLoadCapacity);
        setFuelConsumption(pFuelConsumption);
        setFlightRange(pFlightRange);
    }

    protected String getHeaders() {
        return "name,type,capacity,loadCapacity,fuelConsumption,flightRange";
    }

    protected String toCSV() {
        return capacity + ", " + loadCapacity + ", " + fuelConsumption + ", " + flightRange;
    }

    public final String getName() {
        return name;
    }

    private void setName(final String pName) {
        this.name = pName;
    }

    public final double getLoadCapacity() {
        return loadCapacity;
    }

    private void setLoadCapacity(final double pLoadCapacity) {
        this.loadCapacity = pLoadCapacity;
    }

    public final int getCapacity() {
        return capacity;
    }

    private void setCapacity(final int pCapacity) {
        this.capacity = pCapacity;
    }

    public final double getFuelConsumption() {
        return fuelConsumption;
    }

    private void setFuelConsumption(final double pFuelConsumption) {
        this.fuelConsumption = pFuelConsumption;
    }

    public final double getFlightRange() {
        return flightRange;
    }

    private void setFlightRange(final double pFlightRange) {
        this.flightRange = pFlightRange;
    }
}
