package ua.lviv.iot;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

class ViewTest extends Output{

    private List<Plane> planeList = new LinkedList<>();
    private View view = new View();

    @Test
    void testOutputMenu() {
        view.outputMenu();
        String menuExpected = "\nMENU:\n  1 - Create Manager\n  2 - Print List Of Planes\n" +
                "  3 - Sort By Flight Range\n  4 - Find Plane\n  5 - Total Capacity\n  6 - Total Load Capacity\n" +
                "  E - exit\n";
        assertEquals(menuExpected, output.toString());
    }

    @Test
    void managerMenu1() {
        String actuallyOut = "";
        String expectedOut = "";
        assertEquals(expectedOut,actuallyOut);
        view.manager("1");
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
                300, 1500, 100));
        for (Plane aPlane : planeList) {
            actuallyOut = actuallyOut.concat(aPlane.getName() + "; ");
        }
        expectedOut = "Su-27; Il-2; Tu-16; Boeing 747; Airbus A300; Gulfstream G150; ";
        assertEquals(expectedOut, actuallyOut);
        assertEquals("CSV file was created successfully\n", output.toString());
    }

    @Test
    void managerMenu2() {
        String expectedOut = "CSV file was created successfully\n" +
                "Su-27; Il-2; Tu-16; Boeing 747; Airbus A300; Gulfstream G150; \n\n" +
                "Planes from csv file:\n" +
                "Su-27, MILITARY, 15, 10.0, 150.0, 20.0, 20\n" +
                "Il-2, MILITARY, 20, 50.0, 100.0, 30.0, 15\n" +
                "Tu-16, MILITARY, 100, 20.0, 100.0, 100.0, 50\n" +
                "Boeing 747, MILITARY, 10, 0.4, 1000.0, 1500.0, 1000\n" +
                "Airbus A300, MILITARY, 50, 10.0, 500.0, 2000.0, 1500\n" +
                "Gulfstream G150, MILITARY, 20, 50.0, 300.0, 1500.0, 100\n";
        view.manager("1");
        view.manager("2");
        assertEquals(expectedOut, output.toString());
    }

    @Test
    void managerMenu5() {
        view.manager("1");
        view.manager("5");
        String expectedOut = "CSV file was created successfully\nCapacity of all planes is 215 tones.\n";
        assertEquals(expectedOut, output.toString());
    }

    @Test
    void managerMenu6() {
        String expectedOut = "CSV file was created successfully\nLoad capacity of all planes is 140.4 tones.\n";
        view.manager("1");
        view.manager("6");
        assertEquals(expectedOut, output.toString());
    }

    @Test
    void managerMenuE(){
        view.manager("E");
        assertEquals("  Goodbye!!!\n", output.toString());
    }
    @Test
    void managerDefault() {
        String expectedOut = "Error! Menu has not this point\n";
        view.manager("8");
        assertEquals(expectedOut, output.toString());
    }

    @Test
    void show() {
    }
}