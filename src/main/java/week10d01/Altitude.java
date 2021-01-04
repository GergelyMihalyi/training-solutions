package week10d01;

import java.util.List;

public class Altitude {

    private double downHill;
    private double uphHill;

    public double getDownHill() {
        return downHill;
    }

    public double getUphHill() {
        return uphHill;
    }

    public void setDownHill(int downHill) {
        this.downHill = downHill;
    }

    public void setUphHill(int uphHill) {
        this.uphHill = uphHill;
    }

    private void altitudeData(int x, int y){
        if (x > y) {
            downHill += x - y;
        } else {
            uphHill += y - x;
        }
    }

    public void altitude(List<Integer> altitudes) {
        for (int i = 1; i < altitudes.size(); i++) {
            altitudeData(altitudes.get(i - 1) , altitudes.get(i));
        }
    }

    @Override
    public String toString() {
        return "Altitude{" +
                "downHill=" + downHill +
                ", uphHill=" + uphHill +
                '}';
    }
}
