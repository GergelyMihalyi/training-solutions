package week13d01;

public class City {
    private String number;
    private String name;
    private String part;

    public City(String number, String name, String part) {
        this.number = number;
        this.name = name;
        this.part = part;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getPart() {
        return part;
    }

    @Override
    public String toString() {
        return "City{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", part='" + part + '\'' +
                '}';
    }
}
