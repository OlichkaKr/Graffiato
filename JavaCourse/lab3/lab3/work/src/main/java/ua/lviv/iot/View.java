package ua.lviv.iot;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.LinkedHashMap;

public class View {
    private Map<String, String> menu;
    private List<Plane> planeList = new LinkedList<>();
    private static Scanner input = new Scanner(System.in, "UTF-8");
    private Scanner scanner = new Scanner(System.in, "UTF-8");
    private CompanyManager companyManager = new CompanyManager(planeList);

    public static void main(String[] args) {
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

    public void outputMenu() {
        System.out.print("\nMENU:\n");
        for (String str : menu.values()) {
            System.out.print(str + "\n");
        }
    }

    public void manager(final String num) {
        switch (num) {
            case "1": {
                companyManager.setPlaneList(planeList);

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
                break;
            }
            case "2": {
                for (Plane plane : planeList) {
                    System.out.print(plane.getName() + "; ");
                }
                System.out.print("\n");
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
                companyManager.sortByFlightRange(planeList, comparison);
                break;
            }
            case "4": {
                double fuelConsumption = scanner.nextDouble();
                List<Plane> planes;
                planes = companyManager.searchFuelConsumption(fuelConsumption);
                for (Plane plane : planes) {
                    System.out.print(plane.getName() + "; ");
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
            System.out.println("  M - return menu\n  E - exit");
            keyMenu = input.nextLine().toUpperCase();
        } while (!keyMenu.equalsIgnoreCase("E"));
    }
}
