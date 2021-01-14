package ioreaderclasspath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CountryStatistics {
    private List<Country> countries = new ArrayList<>();

    public void readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                CountryStatistics.class.getResourceAsStream(fileName)
        ))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                Country country = new Country(parts[0], Integer.parseInt(parts[1]));
                countries.add(country);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
    }

    public int numberOfCountries() {
        return countries.size();
    }

    public Country mostNeighboringCountries() {
        Country mostNeighboringCountry = countries.get(0);
        for (Country country : countries) {
            if (country.getNeighboringCountries() > mostNeighboringCountry.getNeighboringCountries()) {
                mostNeighboringCountry = country;
            }
        }
        return mostNeighboringCountry;
    }

    public List<Country> getCountries() {
        return countries;
    }


    public static void main(String[] args) {
        CountryStatistics countryStatistic = new CountryStatistics();
        countryStatistic.readFile("country.txt");
        System.out.println(countryStatistic.numberOfCountries());
    }
}
