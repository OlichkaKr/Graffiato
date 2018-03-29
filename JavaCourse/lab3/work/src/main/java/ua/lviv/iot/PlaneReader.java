package ua.lviv.iot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlaneReader {
    private List<Plane> planeList = new LinkedList<>();

    public final Map<Integer, Plane> readFromCSV(PlaneTypes planeTypes) {
        String line;
        Map planes = null;
        try (BufferedReader fileReader = new BufferedReader(new FileReader("..\\lab3\\work\\src\\main\\Planes.csv"))) {

            int i = 0;
            while ((fileReader.readLine()) != null) {
                i++;
                line = fileReader.readLine();
                String[] tokens = line.split(", ");
                if (tokens[1].equalsIgnoreCase(planeTypes.toString()) && planeTypes == PlaneTypes.MILITARY) {
                    planeList.add(new Military(tokens[0], PlaneTypes.MILITARY, Integer.parseInt(tokens[2]),
                            Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]),
                            Double.parseDouble(tokens[5]), Integer.parseInt(tokens[6]), i));
                } else if (tokens[1].equalsIgnoreCase(planeTypes.toString()) && planeTypes == PlaneTypes.CIVIL) {
                    planeList.add(new Civil(tokens[0], PlaneTypes.CIVIL, Integer.parseInt(tokens[2]),
                            Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]),
                            Double.parseDouble(tokens[5]), Integer.parseInt(tokens[6]), i));
                }
                fileReader.readLine();
            }

            planes = planeList.stream().collect(Collectors.toMap(Plane::getId, Plane -> Plane,
                    (oldValue, newValue)->oldValue));

        } catch (IOException e) {
            System.out.print("Error with reading\n");
            e.printStackTrace();
        }
        return planes;
    }
}