package week10d01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Hiking {

    public Altitude getPlusElevation() {
        Altitude altitude = new Altitude();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Hiking.class.getResourceAsStream("coordinates.txt")))) {
            String line;
            List<Integer> altitudeL = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String[] arrOfPart = line.split(", ");
                altitudeL.add(Integer.parseInt(arrOfPart[2]));
            }
            altitude.altitude(altitudeL);
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
        return altitude;
    }

}
