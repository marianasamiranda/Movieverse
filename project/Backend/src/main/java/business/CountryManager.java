package business;

import data.daos.CountryDAO;
import data.entities.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryManager {

    @Autowired
    private CountryDAO countryDAO;

    public  Country getCountryByCode(String code) {
        return countryDAO.loadEntity("alphacode='" + code.toUpperCase() + "'", "id");

    }
}
