package simple.springboots.service.country.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import simple.springboots.service.country.data.CountryEntity;
import simple.springboots.service.country.data.CountryRepository;
import simple.springboots.service.country.domain.Country;

import java.util.List;

@Component
public class DbCountryService implements CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public DbCountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> allCountries() {
        return countryRepository.findAll()
                .stream()
                .map(ce -> new Country(
                                ce.getCountryName(),
                                ce.getCountryCode()
                        )
                )
                .toList();
    }

    @Override
    public void addCountry(String countryName, String countryCode) {
        CountryEntity countryEntity = countryRepository.save(
                new CountryEntity(
                        null,
                        countryName,
                        countryCode
                )
        );

    }

    @Override
    public void editCountryName(String countryCode, String countryName) {
        CountryEntity country = countryRepository.findByCountryCode(countryCode);
        countryRepository.save(
                new CountryEntity(
                        country.getId(),
                        countryName,
                        countryCode)
        );
    }
}
