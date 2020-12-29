package week06d01;

public class SumResult {

    private final double positive;

    private final double negative;

    public SumResult(double positive, double negative) {
        this.positive = positive;
        this.negative = negative;
    }

    public double getPositive() {
        return positive;
    }

    public double getNegative() {
        return negative;
    }

    @Override
    public String toString() {
        return "SumResult{" +
                "positive=" + positive +
                ", negative=" + negative +
                '}';
    }
}
