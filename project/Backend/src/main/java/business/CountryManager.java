package business;

import data.movieverse.Country;
import data.movieverse.CountryDAO;
import org.orm.PersistentException;

public class CountryManager {

    public static Country getCountryByCode(String code) {
        try {
            return (Country) CountryDAO.queryCountry("alphacode='" + code.toUpperCase() + "'", "id").get(0);
        }
        catch (PersistentException e) {
            return null;
        }
    }
}
