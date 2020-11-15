package finalmodifier;

import static finalmodifier.CircleCalculator.PI;

public class CylinderCalculator {

    public double calculateVolume(double r, double h) {
        double volume = PI * r * r * h;
        return volume;
    }

    public double calculateSurfaceArea(double r, double h) {
        double area = 2 * PI * r * (r + h);
        return area;
    }
}
