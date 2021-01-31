package week10d02;

public class Stop {
    private String name;
    private int stopNumber;

    public Stop(String name, int stopNumber) {
        this.name = name;
        this.stopNumber = stopNumber;
    }

    public String getName() {
        return name;
    }

    public int getStopNumber() {
        return stopNumber;
    }
}
