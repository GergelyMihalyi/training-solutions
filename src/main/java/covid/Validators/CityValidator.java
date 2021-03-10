package covid.Validators;

import covid.Dao.CityDao;
import covid.Models.City;

public class CityValidator {

    public static String searchValidCityByZip(String zip) {
        City city = new CityDao().findCityByZip(zip);
        String name = city != null ? city.getName() : null;
        return name;
    }
}
