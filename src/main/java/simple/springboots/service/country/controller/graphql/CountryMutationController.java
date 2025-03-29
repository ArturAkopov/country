package simple.springboots.service.country.controller.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import simple.springboots.service.country.domain.graphql.CountryGql;
import simple.springboots.service.country.domain.graphql.CountryInputGql;
import simple.springboots.service.country.service.CountryService;


@Controller
public class CountryMutationController {

    private final CountryService countryService;

    @Autowired
    public CountryMutationController(CountryService countryService) {
        this.countryService = countryService;
    }

    @MutationMapping
    public CountryGql addGqlCountry(@Argument CountryInputGql input) {
        return countryService.addGqlCountry(input);
    }

    @MutationMapping
    public CountryGql editGqlCountryName(@Argument CountryInputGql input) {
        return countryService.editGqlCountryName(input);
    }
}
