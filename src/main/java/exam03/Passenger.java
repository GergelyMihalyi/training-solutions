package exam03;

public class Passenger {

    private String name;
    private CruiseClass cruiseClass;

    public Passenger(String name, CruiseClass cruiseClass) {
        isNotNull(name);
        isNotNull(cruiseClass);
        this.name = name;
        this.cruiseClass = cruiseClass;
    }

    public String getName() {
        return name;
    }

    public CruiseClass getCruiseClass() {
        return cruiseClass;
    }

    private boolean isNotNull(Object object) {
        if (object == null) {
            throw new NullPointerException("Cannot be null!");
        }
        return true;
    }
}
