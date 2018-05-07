package ua.lviv.iot;

import ua.lviv.iot.persistence.dao.PlaneDao;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class CompanyManager implements Serializable{
    private int totalCapacity = 0;
    private double totalLoadCapacity = 0;

    private Map<Integer, Plane> planeList = new HashMap<>();

    @Inject
    private PlaneDao dao;

    public CompanyManager() {
    }

    public CompanyManager(final Map<Integer, Plane> pPlaneList) {
        setPlaneList(pPlaneList);
    }

    public final void addPlane(final Plane plane) {
        dao.persist(plane);
//        planeList.put(plane.getId(), plane);
    }

    public Plane findById(Integer id){
        return dao.findById(id);
    }

    public void deletePlane(Integer id){
        dao.deleteById(id);
    }

    public boolean updatePlane(Integer id, Plane plane){
        return dao.update(plane) != null;
    }

    public final Map<Integer, Plane> searchFuelConsumption(final double fuelCons) {
        Map<Integer, Plane> result = new LinkedHashMap<>();
        for (Map.Entry<Integer, Plane> aPlaneList : planeList.entrySet()) {
            if (aPlaneList.getValue().getFuelConsumption() == fuelCons) {
                result.entrySet().add(aPlaneList);
            }
        }
        return result;
    }

    public static void sortByFlightRange(Map<Integer, Plane> planeMap,
                                         final Comparison comp) {

        Map planes;
        List<Plane> pPlaneList = new LinkedList<>(planeMap.values());
        if (comp == Comparison.INCREASE) {
            planes = pPlaneList.stream()
                    .sorted(Comparator.comparingDouble(Plane::getFlightRange))
                    .collect(Collectors.toMap(
                            Plane::getId, Plane -> Plane,
                            (oldKey, newKey) -> oldKey,
                            HashMap::new));
        } else {
            planes = pPlaneList.stream()
                    .sorted(Comparator.comparingDouble(Plane::getFlightRange).reversed())
                    .collect(Collectors.toMap(
                            Plane::getId, Plane -> Plane,
                            (oldKey, newKey) -> oldKey,
                            HashMap::new));
        }
        planeMap.putAll(planes);
    }

    public final int totalCapacity(final Map<Integer, Plane> pPlaneList) {
        for (Map.Entry<Integer, Plane> aPlaneList : pPlaneList.entrySet()) {
            totalCapacity += aPlaneList.getValue().getCapacity();
        }
        return totalCapacity;
    }

    public final double totalLoadCapacity(final Map<Integer, Plane> pPlaneList) {
        for (Map.Entry<Integer, Plane> aPlaneList : pPlaneList.entrySet()) {
            totalLoadCapacity += aPlaneList.getValue().getLoadCapacity();
        }
        return totalLoadCapacity;
    }

    public final void setPlaneList(final Map<Integer, Plane> pPlaneList) {
        planeList = pPlaneList;
    }

    public Map<Integer, Plane> getPlaneList() {
        return planeList;
    }
}
