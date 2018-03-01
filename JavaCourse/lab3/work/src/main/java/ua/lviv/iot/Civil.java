package ua.lviv.iot;

public class Civil extends Plane {

    private PlaneTypes planeTypes;

    private int passengers;

    public Civil() {
    }

    public Civil(final String pName, final PlaneTypes pPlaneTypes,
                 final int pCapacity, final double pLoadCapacity,
                 final double pFuelConsumption, final double pFlightRange,
                 final int pPassengers) {
        super(pName, pCapacity, pLoadCapacity, pFuelConsumption, pFlightRange);
        setPlaneTypes(pPlaneTypes);
        setPassengers(pPassengers);
    }

    public final PlaneTypes getPlaneTypes() {
        return planeTypes;
    }

    private void setPlaneTypes(final PlaneTypes pPlaneTypes) {
        this.planeTypes = pPlaneTypes;
    }

    public final int getPassengers() {
        return passengers;
    }

    private void setPassengers(final int pPassengers) {
        this.passengers = pPassengers;
    }
}
