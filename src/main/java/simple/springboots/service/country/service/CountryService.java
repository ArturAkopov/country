package simple.springboots.service.country.service;

import simple.springboots.service.country.domain.Country;

import java.util.List;

public interface CountryService {

    List<Country> allCountries();

    void addCountry(String countryName, String countryCode);

    void editCountryName(String countryCode, String countryName);
}
