package week08d02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryStatisticsTest {

    @Test
    void maxPopulationTest() {
        CountryStatistics countryStatistics = new CountryStatistics();
        countryStatistics.readFromFile("countries.txt");
        assertEquals(79, countryStatistics.maxPopulaton().getPopulation());
    }

}