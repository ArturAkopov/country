package simple.springboots.service.country.controller.graphql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import simple.springboots.service.country.domain.graphql.CountryGql;
import simple.springboots.service.country.service.CountryService;


@Controller
public class CountryQueryController {

    private final CountryService countryService;

    @Autowired
    public CountryQueryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @QueryMapping
    public Slice<CountryGql> allGqlCountries(@Argument int page, @Argument int size) {
        return countryService.allGqlCountries(PageRequest.of(page, size));
    }

}
