package controller2;

import data.daos.CountryDAO;
import data.entities.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class CountryManager {

    @Autowired
    private CountryDAO countryDAO;


    @RequestMapping(method = GET, value = "/countries")
    private void listCountries() {
        List<Country> countries = countryDAO.findAll();
        for (Country e : countries) {
            System.out.println(e.getName());
        }
    }


    @RequestMapping(method = GET, value = "/countries2")
    private void newCountry() {
        Country country = new Country();
        country.setAlphaCode("oi");
        country.setName("AJJJA");
        countryDAO.save(country);
        //countryDAO.flush();
    }

    @RequestMapping(method = GET, value = "/countries3")
    private void finCountry() {
        Country country =  (Country) countryDAO.loadEntity("alphacode='oo'", "id");
        System.out.println(country.getName());
    }




}