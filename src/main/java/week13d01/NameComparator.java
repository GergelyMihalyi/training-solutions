package week13d01;

import java.util.Comparator;

public class NameComparator implements Comparator<City> {

    @Override
    public int compare(City city1, City city2) {
        return city1.getName().compareTo(city2.getName());
    }
}
