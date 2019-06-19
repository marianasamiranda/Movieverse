package business.impl;

import business.CountryService;
import data.daos.CountryDAO;
import data.entities.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryDAO countryDAO;

    @Override
    public  Country getCountryByCode(String code) {
        return countryDAO.loadEntity("alphacode='" + code.toUpperCase() + "'", "id");

    }
}
