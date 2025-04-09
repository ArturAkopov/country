package simple.springboots.service.country.service.impl;

import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import simple.springboots.service.country.model.CountryJson;
import simple.springboots.service.country.service.CountryClient;
import xml.country.*;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class CountrySoupClient extends WebServiceGatewaySupport implements CountryClient {

    private static final Logger log = LoggerFactory.getLogger(CountryClient.class);

    private static final String countryBaseUri = "http://127.0.0.1:8282";

    @Override
    @NonNull
    public CountryResponse addCountry(CountryJson country) {

        Country addedCountry = new Country();
        addedCountry.setCountryName(country.countryName());
        addedCountry.setCountryCode(country.countryCode());

        AddCountryInputRequest request = new AddCountryInputRequest();
        request.setCountry(addedCountry);

        log.info("Requesting location for {}", country);

        return (CountryResponse) getWebServiceTemplate()
                .marshalSendAndReceive(countryBaseUri+"/ws/country", request,
                        new SoapActionCallback(
                                ""));
    }

    @Override
    @NonNull
    public CountryResponse editCountry(CountryJson country) {

        Country editedCountry = new Country();
        editedCountry.setCountryName(country.countryName());
        editedCountry.setCountryCode(country.countryCode());

        EditCountryInputRequest request = new EditCountryInputRequest();
        request.setCountry(editedCountry);

        log.info("Requesting location for {}", country);

        return (CountryResponse) getWebServiceTemplate()
                .marshalSendAndReceive(countryBaseUri+"/ws/country", request,
                        new SoapActionCallback(
                                ""));
    }

    @Override
    @NonNull
    public CountriesResponse countries(int page, int size) {

        PageRequest request = new PageRequest();
        request.setPage(page);
        request.setSize(size);

        return (CountriesResponse) getWebServiceTemplate()
                .marshalSendAndReceive(countryBaseUri+"/ws/country", request,
                        new SoapActionCallback(
                                ""));
    }
}
