package ua.lviv.iot;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CompanyManagerTest {
    private Map<Integer, Plane> planeList = new HashMap<>();
    private Map<Integer, Plane> result = new HashMap<>();
    private PlaneWriter planeWriter = new PlaneWriter();
    private PlaneReader planeReader = new PlaneReader();
    private CompanyManager companyManager = new CompanyManager(planeList);

    private void setList() {
        planeList.put(1, new Military("Su-27", PlaneTypes.MILITARY, 15, 10,
                150, 20, 20));
        planeList.put(2, new Military("Il-2", PlaneTypes.MILITARY, 20, 50,
                100, 30, 15));
        planeList.put(3, new Military("Tu-16", PlaneTypes.MILITARY, 100, 20,
                100, 100, 50));
        planeList.put(4, new Civil("Boeing 747", PlaneTypes.CIVIL, 10, 0.4,
                1000, 1500, 1000));
        planeList.put(5, new Civil("Airbus A300", PlaneTypes.CIVIL, 50, 10,
                500, 2000, 1500));
        planeList.put(6, new Civil("Gulfstream G150", PlaneTypes.CIVIL, 20, 50,
                300, 1600, 100));
    }

    @Test
    void searchFuelConsumption() {
        setList();
        result.put(1, planeList.get(5));
        assertEquals(result, companyManager.searchFuelConsumption(300.0));
        result.clear();
        result.put(1, planeList.get(1));
        result.put(2, planeList.get(2));
        assertEquals(result, companyManager.searchFuelConsumption(100));

    }

    @Test
    void sortByFlightRange() {
        setList();
        result.putAll(planeList);
        result.put(1, planeList.get(4));
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

    @Test
    void csvTest() {
        setList();
        planeWriter.writeToFile(planeList);
        result = planeReader.readFromCSV(PlaneTypes.MILITARY);
        for (Map.Entry<Integer, Plane> aResult : result.entrySet()) {
            System.out.print(aResult.getValue().toCSV());
        }
    }

}