package simple.springboots.service.country.service.soup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import simple.springboots.service.country.config.AppConfig;
import simple.springboots.service.country.domain.graphql.CountryGql;
import simple.springboots.service.country.domain.graphql.CountryInputGql;
import simple.springboots.service.country.service.CountryService;
import xml.country.*;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;


@Endpoint
@ParametersAreNonnullByDefault
public class CountryEndpoint {

    private final CountryService countryService;

    @Autowired
    public CountryEndpoint(CountryService countryService) {
        this.countryService = countryService;
    }

    @PayloadRoot(namespace = AppConfig.SOUP_NAMESPACE, localPart = "AddCountryInputRequest")
    @ResponsePayload
    public CountryResponse addCountry(@RequestPayload AddCountryInputRequest request) {
        final CountryInputGql inputGql = new CountryInputGql(
                request.getCountry().getCountryName(),
                request.getCountry().getCountryCode());

        final CountryGql country = countryService.addGqlCountry(inputGql);

        Country xmlCountry = getXmlCountry(country);

        CountryResponse response = new CountryResponse();
        response.setCountry(xmlCountry);
        return response;
    }

    @PayloadRoot(namespace = AppConfig.SOUP_NAMESPACE, localPart = "EditCountryInputRequest")
    @ResponsePayload
    public CountryResponse editCountry(@RequestPayload EditCountryInputRequest request) {
        final CountryInputGql inputGql = new CountryInputGql(
                request.getCountry().getCountryName(),
                request.getCountry().getCountryCode());

        final CountryGql country = countryService.editGqlCountryName(inputGql);

        Country xmlCountry = getXmlCountry(country);

        CountryResponse response = new CountryResponse();
        response.setCountry(xmlCountry);
        return response;
    }

    @PayloadRoot(namespace = AppConfig.SOUP_NAMESPACE, localPart = "PageRequest")
    @ResponsePayload
    public CountriesResponse allCountries(@RequestPayload PageRequest request) {
        Page<CountryGql> countries = (Page<CountryGql>) countryService.allGqlCountries(
                org.springframework.data.domain.PageRequest.of(
                request.getPage(),
                request.getSize()
        ));
        CountriesResponse response = new CountriesResponse();
        response.setTotalPages(countries.getTotalPages());
        response.setTotalElements(countries.getTotalElements());
        response.getCountries().addAll(
                countries.getContent().stream().map(
                        this::getXmlCountry
                ).toList());
        return response;
    }

    @Nonnull
    private Country getXmlCountry(CountryGql countryGql){
        Country result = new Country();
        result.setId(countryGql.id().toString());
        result.setCountryName(countryGql.countryName());
        result.setCountryCode(countryGql.countryCode());
        return result;
    }
}
