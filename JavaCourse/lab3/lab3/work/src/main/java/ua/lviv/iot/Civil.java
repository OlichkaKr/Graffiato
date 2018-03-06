package ua.lviv.iot;

public class Civil extends Plane {

    private PlaneTypes planeTypes;

    private int passengers;

    public Civil(final String pName, final PlaneTypes pPlaneTypes,
                 final int pCapacity, final double pLoadCapacity,
                 final double pFuelConsumption, final double pFlightRange,
                 final int pPassengers) {
        super(pName, pCapacity, pLoadCapacity, pFuelConsumption, pFlightRange);
        setPlaneTypes(pPlaneTypes);
        setPassengers(pPassengers);
    }

    private void setPlaneTypes(final PlaneTypes pPlaneTypes) {
        this.planeTypes = pPlaneTypes;
    }


    private void setPassengers(final int pPassengers) {
        this.passengers = pPassengers;
    }
}
