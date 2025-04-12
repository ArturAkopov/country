package simple.springboots.service.country.service;

import lombok.NonNull;
import simple.springboots.service.country.model.CountryJson;
import simple.springboots.service.country.service.impl.CountrySoupClient;
import xml.country.CountriesResponse;
import xml.country.CountryResponse;

import javax.annotation.Nonnull;

public interface CountryClient {

    @Nonnull
    static CountryClient getInstance() {
        return new CountrySoupClient();
    }

    @NonNull
    CountryResponse addCountry(CountryJson country);

    @NonNull
    CountryResponse editCountry(CountryJson country);

    @NonNull
    CountriesResponse countries(int page, int size);
}
