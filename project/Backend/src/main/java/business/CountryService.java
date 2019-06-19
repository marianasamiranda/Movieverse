package business;

import data.entities.Country;

public interface CountryService {
    Country getCountryByCode(String code);
}
