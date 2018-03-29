package ua.lviv.iot;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PlaneWriter {
    public void writeToFile(final Map<Integer, Plane> pPlaneList) {

        try (FileWriter fileWriter = new FileWriter("..\\lab3\\work\\src\\main\\Planes.csv", false)) {
            for (Map.Entry<Integer, Plane> plane: pPlaneList.entrySet()) {
                fileWriter.append(plane.getValue().getHeaders());
                fileWriter.append(plane.getValue().toCSV());
                fileWriter.append("\n");
            }
            fileWriter.close();
            System.out.print("CSV file was created successfully\n");
        } catch (IOException e) {
            System.out.print("Error!!!\n");
            e.printStackTrace();
        }
    }
}