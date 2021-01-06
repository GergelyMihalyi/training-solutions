package week08d05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Plane {

    public int flyOverMapWithMaxOcean(String path) {
        int max = 0;
        try (FileInputStream fis = new FileInputStream(path)) {
            int input;
            int counter = 0;
            while ((input = fis.read()) != -1) {
                if (input == 48) {
                    counter++;
                } else {
                    if (max < counter) {
                        max = counter;
                    }
                    counter = 0;
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.printf("File not found");
        } catch (IOException ioe) {
            System.out.println("Can not read the file");
        }

        return max;
    }

}
