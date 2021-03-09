package covid;

public class City {
    private String zip;
    private String name;

    public City(String zip, String name) {
        this.zip = zip;
        this.name = name;
    }

    public static String searchValidCityByZip(String zip){
        City city = new CityDao(new DatabaseConnection().dataSource).findCityByZip(zip);
        String name = city != null ? city.getName() : null;
        return name;
    }

    public String getZip() {
        return zip;
    }

    public String getName() {
        return name;
    }
}
