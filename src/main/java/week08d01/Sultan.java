package week08d01;

import java.util.ArrayList;
import java.util.List;

public class Sultan {
    private boolean[] arrayOfDoors = new boolean[100];

    public List<Integer> openDoors(int day) {
        checkDay(day);
        List<Integer> openedDoor = new ArrayList<>();
        for (int x = 1; x < day + 1; x++) {
            for (int i = 0; i < arrayOfDoors.length; i += x) {
                arrayOfDoors[i] = !arrayOfDoors[i];
            }
        }
        for (int y = 0; y < arrayOfDoors.length; y++) {
            if (arrayOfDoors[y]) {
                openedDoor.add((y));
            }
        }
        return openedDoor;
    }

    private void checkDay(int day) {
        if (day < 1) {
            throw new IllegalArgumentException("The number of days cannot be less than 0");
        }
    }
}
