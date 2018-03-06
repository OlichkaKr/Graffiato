package ua.lviv.iot;

public class Military extends Plane {

    private PlaneTypes planeTypes;
    private int ammunition;

    public Military(final String pName, final PlaneTypes pPlaneTypes,
                    final int pCapacity, final double pLoadCapacity,
                    final double pFuelConsumption, final double pFlightRange,
                    final int pAmmunition) {
        super(pName, pCapacity, pLoadCapacity, pFuelConsumption, pFlightRange);
        setPlaneTypes(pPlaneTypes);
        setAmmunition(pAmmunition);
    }

    private void setPlaneTypes(final PlaneTypes pPlaneTypes) {
        this.planeTypes = pPlaneTypes;
    }

    private void setAmmunition(final int pAmmunition) {
        this.ammunition = pAmmunition;
    }
}
