package finalmodifier;

public class PiMain {
    public static void main(String[] args) {
        CircleCalculator circleCalculator = new CircleCalculator();
        CylinderCalculator cylinderCalculator = new CylinderCalculator();
        double r = 2.3;
        double h = 3.0;
        System.out.println("Area: " + circleCalculator.calculateArea(r) );
        System.out.println("Perimeter: " + circleCalculator.calculatePerimeter(r) );
        System.out.println("Surface: " + cylinderCalculator.calculateSurfaceArea(r,h) );
        System.out.println("Volume: " + cylinderCalculator.calculateVolume(r,h) );
        System.out.println("Pi: " + circleCalculator.PI);

    }
}
