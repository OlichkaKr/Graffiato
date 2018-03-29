package ua.lviv.iot;

import java.util.*;

public class View {
    private Map<String, String> menu;
    private Map<Integer, Plane> planeList = new HashMap<>();
    private static Scanner input = new Scanner(System.in, "UTF-8");
    private Scanner scanner = new Scanner(System.in, "UTF-8");
    private CompanyManager companyManager = new CompanyManager(planeList);
    private PlaneWriter planeWriter = new PlaneWriter();
    private PlaneReader planeReader = new PlaneReader();

    public static final void main(final String[] args) {
        View view = new View();
        view.show();
    }

    View() {
        menu = new LinkedHashMap<>();

        menu.put("1", "  1 - Create Manager");
        menu.put("2", "  2 - Print List Of Planes");
        menu.put("3", "  3 - Sort By Flight Range");
        menu.put("4", "  4 - Find Plane");
        menu.put("5", "  5 - Total Capacity");
        menu.put("6", "  6 - Total Load Capacity");
        menu.put("E", "  E - exit");

    }

    public final void outputMenu() {
        System.out.print("\nMENU:\n");
        for (String str : menu.values()) {
            System.out.print(str + "\n");
        }
    }

    public final void manager(final String num) {
        switch (num) {
            case "1": {
                companyManager.setPlaneList(planeList);
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
                        300, 1500, 100));

                planeWriter.writeToFile(planeList);
                break;
            }
            case "2": {
                for (Map.Entry<Integer, Plane> plane : planeList.entrySet()) {
                    System.out.print(plane.getValue().getName() + "; ");
                }

                System.out.print("\n");

                System.out.print("\nPlanes from csv file:\n");
                Map<Integer, Plane> planes = new LinkedHashMap<>(planeReader.readFromCSV(PlaneTypes.MILITARY));
                for (Map.Entry<Integer, Plane> planeEntry: planes.entrySet()){
                    System.out.print(planeEntry.getValue().getName() + "; ");
                }
                planes.clear();
                break;
            }
            case "3": {
                System.out.print("How do you want to sort planes? (increase/decrease) ");
                String comp = scanner.next().toLowerCase();
                Comparison comparison;
                if (comp.equals("increase")) {
                    comparison = Comparison.INCREASE;
                } else {
                    comparison = Comparison.DECREASE;
                }
                CompanyManager.sortByFlightRange(planeList, comparison);
                break;
            }
            case "4": {
                double fuelConsumption = scanner.nextDouble();
                Map<Integer, Plane> planes;
                planes = companyManager.searchFuelConsumption(fuelConsumption);
                for (Map.Entry<Integer, Plane> plane : planes.entrySet()) {
                    System.out.print(plane.getValue().getName() + "; ");
                }
                break;
            }
            case "5": {
                System.out.print("Capacity of all planes is "
                        + companyManager.totalCapacity(planeList) + " tones.\n");
                break;
            }
            case "6": {
                System.out.print("Load capacity of all planes is "
                        + companyManager.totalLoadCapacity(planeList) + " tones.\n");
                break;
            }
            case "E": {
                System.out.print("  Goodbye!!!\n");
                return;
            }
            default: {
                System.out.print("Error! Menu has not this point\n");
            }
        }
    }

    public final void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point");
            keyMenu = input.nextLine().toUpperCase();
            manager(keyMenu);
            do {
                System.out.println("\n  M - return menu\n  E - exit");
                keyMenu = input.nextLine().toUpperCase();
                if (keyMenu.equalsIgnoreCase("E")) {
                    manager(keyMenu);
                    return;
                }
            } while (!keyMenu.equalsIgnoreCase("M"));

        } while (!keyMenu.equalsIgnoreCase("E"));
    }
}
