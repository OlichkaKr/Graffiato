package ua.lviv.iot;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class CompanyManager {
    private int totalCapacity = 0;
    private double totalLoadCapacity = 0;

    private List<Plane> planeList;

    public CompanyManager(final List<Plane> pPlaneList) {
        setPlaneList(pPlaneList);
    }

    public final List<Plane> searchFuelConsumption(final double fuelCons) {
        List<Plane> result = new LinkedList<>();
        for (Plane aPlaneList : planeList) {
            if (aPlaneList.getFuelConsumption() == fuelCons) {
                result.add(aPlaneList);
            }
        }
        return result;
    }

    public final void sortByFlightRange(final List<Plane> pPlaneList,
                                        final Comparison comp) {

        if (comp == Comparison.INCREASE) {
            pPlaneList.sort(Comparator.comparing(Plane::getFlightRange));
        } else {
            pPlaneList.sort(Comparator.comparing(Plane::getFlightRange)
                    .reversed());
        }
    }

    public final int totalCapacity(final List<Plane> pPlaneList) {
        for (Plane aPlaneList : pPlaneList) {
            totalCapacity += aPlaneList.getCapacity();
        }
        return totalCapacity;
    }

    public final double totalLoadCapacity(final List<Plane> pPlaneList) {
        for (Plane aPlaneList : pPlaneList) {
            totalLoadCapacity += aPlaneList.getLoadCapacity();
        }
        return totalLoadCapacity;
    }

    public final void setPlaneList(final List<Plane> pPlaneList) {
        this.planeList = pPlaneList;
    }

}
