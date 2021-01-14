package ioreaderclasspath;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryStatisticsTest {


    private CountryStatistics countryStatistics = new CountryStatistics();


    @Test
    public void createListTest() {
        assertEquals(0, countryStatistics.getCountries().size());

        countryStatistics.getCountries().add(new Country("Test", 3));

        assertEquals(0, countryStatistics.getCountries().size());
    }


    @Test
    public void readFromFileTest() {
        assertEquals(countryStatistics.getCountries().size(), 0);
        countryStatistics.readFile("ioreaderclasspath/country.txt");

        assertEquals(8, countryStatistics.getCountries().size());

        assertEquals("Austria", countryStatistics.getCountries().get(1).getName());
        assertEquals(1, countryStatistics.getCountries().get(5).getNeighboringCountries());
    }

    @Test
    public void numberOfCountriesTest() {
        assertEquals(0, countryStatistics.numberOfCountries());
        countryStatistics.readFile("ioreaderclasspath/country.txt");

        assertEquals(8, countryStatistics.numberOfCountries());
    }


    @Test
    public void mostBorderCountriesTest() {
        countryStatistics.readFile("ioreaderclasspath/country.txt");

        assertEquals("Germany", countryStatistics.mostNeighboringCountries().getName());
        assertEquals(8, countryStatistics.mostNeighboringCountries().getNeighboringCountries());

    }
}
