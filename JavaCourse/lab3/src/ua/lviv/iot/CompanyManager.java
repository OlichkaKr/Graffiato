package ua.lviv.iot;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class CompanyManager {
    private int totalCapacity = 0;
    private double totalLoadCapacity = 0;

    private List<Plane> planeList;

    public CompanyManager(List<Plane> planeList) {
        setPlaneList(planeList);
    }

    public List<Plane> searchFuelConsumption(double fuelConsumption) {
        List<Plane> result = new LinkedList<>();
        for (Plane aPlaneList : planeList) {
            if (aPlaneList.getFuelConsumption() == fuelConsumption) {
                result.add(aPlaneList);
            }
        }
        return result;
    }

    public void sortByFlightRange(List<Plane> planeList, Comparison comparison) {

        if (comparison == Comparison.INCREASE){
            planeList.sort(Comparator.comparing(Plane::getFlightRange));
        } else {
            planeList.sort(Comparator.comparing(Plane::getFlightRange).reversed());
        }
    }

    public int totalCapacity(List<Plane> planeList) {
        for (Plane aPlaneList : planeList) {
            totalCapacity += aPlaneList.getCapacity();
        }
        return totalCapacity;
    }

    public double totalLoadCapacity(List<Plane> planeList) {
        for (Plane aPlaneList : planeList) {
            totalLoadCapacity += aPlaneList.getLoadCapacity();
        }
        return totalLoadCapacity;
    }

    public List<Plane> getPlaneList() {
        return planeList;
    }

    public void setPlaneList(List<Plane> planeList) {
        this.planeList = planeList;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public double getTotalLoadCapacity() {
        return totalLoadCapacity;
    }

    public void setTotalLoadCapacity(double totalLoadCapacity) {
        this.totalLoadCapacity = totalLoadCapacity;
    }
}
