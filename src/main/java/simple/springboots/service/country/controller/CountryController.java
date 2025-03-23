package simple.springboots.service.country.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import simple.springboots.service.country.domain.Country;
import simple.springboots.service.country.model.CountryJson;
import simple.springboots.service.country.service.CountryService;

import java.util.List;

@RestController
@RequestMapping("api/country")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all")
    public List<Country> allCountries() {
        return countryService.allCountries();
    }

    @PostMapping("/add")
    public void addCountry(@RequestBody CountryJson countryJson) {
    }

    @PatchMapping("/edit")
    public void editCountryName(@RequestBody CountryJson countryJson) {
    }
}
