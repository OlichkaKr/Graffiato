package ua.lviv.iot;

import java.util.LinkedList;
import java.util.List;

public class CompanyManager {

    private int totalCapacity = 0;
    private double totalLoadCapacity = 0;

    private List<Plane> planeList;

    public CompanyManager(List<Plane> planeList){
        setPlaneList(planeList);
    }

    public List<Plane> seachFuelConsumption(double fuelConsumption){
        List<Plane> result = new LinkedList<>();
        for (Plane aPlaneList : planeList) {
            if (aPlaneList.getFuelConsumption() == fuelConsumption) {
                result.add(aPlaneList);
            }
        }
        return result;
    }

    public void sortByFlightRange(List<Plane> planeList, Comparison comparison){
        for (int j = 0; j < planeList.size(); j++){
            for (int i = 0; i < planeList.size() - 1; i++){
                if (comparison == Comparison.INCREASE){
                    if (planeList.get(i).getFlightRange() > planeList.get(i+1).getFlightRange()){
                        Plane temp = planeList.get(i);
                        planeList.set(i, planeList.get(i+1));
                        planeList.set(i+1, temp);
                    }
                } else {
                    if (planeList.get(i).getFlightRange() < planeList.get(i+1).getFlightRange()){
                        Plane temp = planeList.get(i);
                        planeList.set(i, planeList.get(i+1));
                        planeList.set(i+1, temp);
                    }
                }
            }
        }
    }

    public int totalCapacity(List<Plane> planeList){
        for (Plane aPlaneList : planeList) {
            totalCapacity += aPlaneList.getCapacity();
        }
        return totalCapacity;
    }

    public double totalLoadCapacity(List<Plane> planeList){
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
