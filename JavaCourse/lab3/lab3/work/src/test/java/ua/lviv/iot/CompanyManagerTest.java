package ua.lviv.iot;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyManagerTest {
    private List<Plane> planeList = new LinkedList<>();
    private List<Plane> result = new LinkedList<>();
    private CompanyManager companyManager = new CompanyManager(planeList);

    void setList() {
        planeList.add(new Military("Su-27", PlaneTypes.MILITARY, 15, 10,
                150, 20, 20));
        planeList.add(new Military("Il-2", PlaneTypes.MILITARY, 20, 50,
                100, 30, 15));
        planeList.add(new Military("Tu-16", PlaneTypes.MILITARY, 100, 20,
                100, 100, 50));
        planeList.add(new Civil("Boeing 747", PlaneTypes.CIVIL, 10, 0.4,
                1000, 1500, 1000));
        planeList.add(new Civil("Airbus A300", PlaneTypes.CIVIL, 50, 10,
                500, 2000, 1500));
        planeList.add(new Civil("Gulfstream G150", PlaneTypes.CIVIL, 20, 50,
                300, 1600, 100));
    }

    @Test
    void searchFuelConsumption() {
        setList();
        result.add(planeList.get(5));
        assertEquals(result, companyManager.searchFuelConsumption(300.0));
        result.remove(0);
        result.add(planeList.get(1));
        result.add(planeList.get(2));
        assertEquals(result, companyManager.searchFuelConsumption(100));

    }

    @Test
    void sortByFlightRange() {
        setList();
        result.addAll(planeList);
        result.add(planeList.get(4));
        result.remove(4);
        companyManager.sortByFlightRange(planeList, Comparison.INCREASE);
        for (int i = 0; i < result.size(); i++){
            assertEquals(result.get(i), planeList.get(i));
        }
        companyManager.sortByFlightRange(planeList, Comparison.DECREASE);
        int count = 0;
        for (int i = 5; i >= 0; i--){
            assertEquals(result.get(i).getName(), planeList.get(count).getName());
            count++;
        }
    }

    @Test
    void totalCapacity() {
        setList();
        assertTrue(companyManager.totalCapacity(planeList) ==  215);
    }

    @Test
    void totalLoadCapacity() {
        setList();
        assertTrue(companyManager.totalLoadCapacity(planeList) ==  140.4);
    }

}