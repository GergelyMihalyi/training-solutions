package covid.Models;


public class City {
    private String zip;
    private String name;

    public City(String zip, String name) {
        this.zip = zip;
        this.name = name;
    }

    public String getZip() {
        return zip;
    }

    public String getName() {
        return name;
    }
}
