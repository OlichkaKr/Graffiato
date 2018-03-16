package ua.lviv.iot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class PlaneReader {
    private List<Plane> planeList = new LinkedList<>();

    public final List<Plane> readFromCSV(PlaneTypes planeTypes) {
        String line;
        try (BufferedReader fileReader = new BufferedReader(new FileReader("..\\work\\src\\main\\Plane.csv"))) {

            while ((fileReader.readLine()) != null) {
                line = fileReader.readLine();
                String[] tokens = line.split(", ");
                if (tokens[1].equalsIgnoreCase(planeTypes.toString()) && planeTypes == PlaneTypes.MILITARY) {
                    planeList.add(new Military(tokens[0], PlaneTypes.MILITARY, Integer.parseInt(tokens[2]),
                            Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]),
                            Double.parseDouble(tokens[5]), Integer.parseInt(tokens[6])));
                } else if (tokens[1].equalsIgnoreCase(planeTypes.toString()) && planeTypes == PlaneTypes.CIVIL) {
                    planeList.add(new Civil(tokens[0], PlaneTypes.CIVIL, Integer.parseInt(tokens[2]),
                            Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]),
                            Double.parseDouble(tokens[5]), Integer.parseInt(tokens[6])));
                }
                fileReader.readLine();
            }

        } catch (IOException e) {
            System.out.print("Error with reading\n");
            e.printStackTrace();
        }
        return planeList;
    }
}