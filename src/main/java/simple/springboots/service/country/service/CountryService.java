package simple.springboots.service.country.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import simple.springboots.service.country.domain.graphql.CountryGql;
import simple.springboots.service.country.domain.graphql.CountryInputGql;
import simple.springboots.service.country.model.CountryJson;

import java.util.List;

public interface CountryService {

    List<CountryJson> allCountries();

    Slice<CountryGql> allGqlCountries(Pageable pageable);

    CountryJson addCountry(String countryName, String countryCode);

    CountryGql addGqlCountry(CountryInputGql input);

    CountryJson editCountryName(String countryCode, String countryName);

    CountryGql editGqlCountryName(CountryInputGql input);
}
