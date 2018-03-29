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

    public Civil(final String pName, final PlaneTypes pPlaneTypes,
                 final int pCapacity, final double pLoadCapacity,
                 final double pFuelConsumption, final double pFlightRange,
                 final int pPassengers, final int id) {
        super(pName, pCapacity, pLoadCapacity, pFuelConsumption, pFlightRange, id);
        setPlaneTypes(pPlaneTypes);
        setPassengers(pPassengers);
    }

    private void setPlaneTypes(final PlaneTypes pPlaneTypes) {
        this.planeTypes = pPlaneTypes;
    }


    private void setPassengers(final int pPassengers) {
        this.passengers = pPassengers;
    }

    @Override
    public final String getHeaders() {
        return super.getHeaders() + ",passengers\n";
    }

    @Override
    public final String toCSV() {
        return super.getName() + ", " + planeTypes + ", " + super.toCSV() + ", " + passengers + "\n";
    }
}
