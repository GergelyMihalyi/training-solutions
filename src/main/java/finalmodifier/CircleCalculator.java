package finalmodifier;

public class CircleCalculator {
    public static final double PI = 3.14;

    public double calculatePerimeter(double r) {
        double perimeter = 2 * PI * r;
        return perimeter;
    }

    public double calculateArea(double r) {
        double area = r * r * PI;
        return area;
    }

}
