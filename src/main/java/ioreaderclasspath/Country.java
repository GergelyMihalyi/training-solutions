package ioreaderclasspath;

public class Country {
    private String name;
    private int neighboringCountries;

    public Country(String name, int neighboringCountries) {
        this.name = name;
        this.neighboringCountries = neighboringCountries;
    }

    public int getNeighboringCountries() {
        return neighboringCountries;
    }

    public String getName() {
        return name;
    }
}
