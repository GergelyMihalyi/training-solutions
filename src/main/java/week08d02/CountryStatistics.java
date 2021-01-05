package week08d02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CountryStatistics {
    private List<Country> countries = new ArrayList<>();

    public void readFromFile(String path) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                CountryStatistics.class.getResourceAsStream(path)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] countryData = line.split(" ");
                String name = countryData[0];
                int population = Integer.parseInt(countryData[1]);
                int colorsOfFlag = Integer.parseInt(countryData[2]);
                int numberOfNeighboringCountries = Integer.parseInt(countryData[3]);
                countries.add(new Country(name, population, colorsOfFlag, numberOfNeighboringCountries));
            }
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Can not read the file", ioe);
        }
    }

    public Country maxPopulaton() {
        int population = 0;
        Country maxPopulationCounty = countries.get(0);
        for (Country country : countries) {
            if (country.getPopulation() > maxPopulationCounty.getPopulation()) {
                maxPopulationCounty = country;
            }
        }
        return maxPopulationCounty;
    }

}
