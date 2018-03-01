package ua.lviv.iot;

public class Military extends Plane {

    private PlaneTypes planeTypes;
    private int ammunition;

    public Military() {
    }

    public Military(final String pName, final PlaneTypes pPlaneTypes,
                    final int pCapacity, final double pLoadCapacity,
                    final double pFuelConsumption, final double pFlightRange,
                    final int pAmmunition) {
        super(pName, pCapacity, pLoadCapacity, pFuelConsumption, pFlightRange);
        setPlaneTypes(pPlaneTypes);
        setAmmunition(pAmmunition);
    }

    public final PlaneTypes getPlaneTypes() {
        return planeTypes;
    }

    private void setPlaneTypes(final PlaneTypes pPlaneTypes) {
        this.planeTypes = pPlaneTypes;
    }

    public final int getAmmunition() {
        return ammunition;
    }

    private void setAmmunition(final int pAmmunition) {
        this.ammunition = pAmmunition;
    }
}
