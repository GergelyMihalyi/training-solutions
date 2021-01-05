package week08d02;

public class Country {
    private String name;
    private int population;
    private int colorsOfFlag;
    private int numberOfNeighboringCountries;

    public Country(String name, int population, int colorsOfFlag, int numberOfNeighboringCountries) {
        this.name = name;
        this.population = population;
        this.colorsOfFlag = colorsOfFlag;
        this.numberOfNeighboringCountries = numberOfNeighboringCountries;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public int getColorsOfFlag() {
        return colorsOfFlag;
    }

    public int getNumberOfNeighboringCountries() {
        return numberOfNeighboringCountries;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", population=" + population +
                ", colorsOfFlag=" + colorsOfFlag +
                ", numberOfNeighboringCountries=" + numberOfNeighboringCountries +
                '}';
    }
}
