package simple.springboots.service.country.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import simple.springboots.service.country.data.CountryEntity;
import simple.springboots.service.country.data.CountryRepository;
import simple.springboots.service.country.domain.graphql.CountryGql;
import simple.springboots.service.country.domain.graphql.CountryInputGql;
import simple.springboots.service.country.model.CountryJson;

import java.util.List;

@Component
public class DbCountryService implements CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public DbCountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<CountryJson> allCountries() {
        return countryRepository.findAll()
                .stream()
                .map(ce -> new CountryJson(
                                ce.getCountryName(),
                                ce.getCountryCode()
                        )
                )
                .toList();
    }

    @Override
    public Page<CountryGql> allGqlCountries(Pageable pageable) {
        return countryRepository.findAll(pageable)
                .map(ce -> new CountryGql(
                        ce.getId(),
                        ce.getCountryName(),
                        ce.getCountryCode()
                ));
    }

    @Override
    public CountryJson addCountry(String countryName, String countryCode) {
        return countryRepository.save(
                new CountryEntity(
                        null,
                        countryName,
                        countryCode
                )
        ).toJson();
    }

    @Override
    public CountryGql addGqlCountry(CountryInputGql input) {
        return countryRepository.save(
                new CountryEntity(
                        null,
                        input.countryName(),
                        input.countryCode()
                )
        ).toCountryGql();
    }

    @Override
    public CountryJson editCountryName(String countryCode, String countryName) {
        CountryEntity country = countryRepository.findByCountryCode(countryCode);
        return countryRepository.save(
                new CountryEntity(
                        country.getId(),
                        countryName,
                        countryCode)
        ).toJson();
    }

    @Override
    public CountryGql editGqlCountryName(CountryInputGql input) {
        CountryEntity country = countryRepository.findByCountryCode(input.countryCode());
        return countryRepository.save(
                new CountryEntity(
                        country.getId(),
                        input.countryName(),
                        input.countryCode())
        ).toCountryGql();
    }
}
