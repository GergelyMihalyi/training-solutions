package exam03;

public class Boat {

    private String name;
    private int maxPassengers;

    public Boat(String name, int maxPassengers) {
        isNotNull(name);
        isNotNull(maxPassengers);
        this.name = name;
        this.maxPassengers = maxPassengers;
    }

    public String getName() {
        return name;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    private boolean isNotNull(Object object) {
        if (object == null) {
            throw new NullPointerException("Cannot be null!");
        }
        return true;
    }
}
