package ua.lviv.iot;

public class Plane{
    private String name ;
    private double loadCapacity;
    private int capacity;
    private double fuelConsumption;
    private double flightRange;
    private int id;

    public Plane(){}


    public Plane(final String pName, final int pCapacity,
                 final double pLoadCapacity, final double pFuelConsumption,
                 final double pFlightRange) {
        setName(pName);
        setCapacity(pCapacity);
        setLoadCapacity(pLoadCapacity);
        setFuelConsumption(pFuelConsumption);
        setFlightRange(pFlightRange);
    }

    public Plane(final String pName, final int pCapacity,
                 final double pLoadCapacity, final double pFuelConsumption,
                 final double pFlightRange, final int id) {
        setName(pName);
        setCapacity(pCapacity);
        setLoadCapacity(pLoadCapacity);
        setFuelConsumption(pFuelConsumption);
        setFlightRange(pFlightRange);
        setId(id);
    }



    protected String getHeaders() {
        return "name,type,capacity,loadCapacity,fuelConsumption,flightRange";
    }

    protected String toCSV() {
        return capacity + ", " + loadCapacity + ", " + fuelConsumption + ", " + flightRange;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
