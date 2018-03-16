package ua.lviv.iot;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PlaneWriter {
    public void writeToFile(final List<Plane> pPlaneList) {

        try (FileWriter fileWriter = new FileWriter("..\\work\\src\\main\\Plane.csv", false)) {
            for (Plane aPlaneList : pPlaneList) {
                fileWriter.append(aPlaneList.getHeaders());
                fileWriter.append(aPlaneList.toCSV());
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