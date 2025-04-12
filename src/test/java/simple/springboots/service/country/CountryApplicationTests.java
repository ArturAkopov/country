package simple.springboots.service.country;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import simple.springboots.service.country.model.CountryJson;
import simple.springboots.service.country.service.CountryClient;
import xml.country.CountriesResponse;
import xml.country.CountryResponse;


class CountryApplicationTests {

    private static final CountryClient countryClient = CountryClient.getInstance();

	@Order(1)
	@DisplayName("Проверка добавления страны")
    @Test
    void checkAddCountry() {
        CountryJson countryJson = new CountryJson("QWERTY", "QWE");
        CountryResponse countryResponse = countryClient.addCountry(countryJson);
        Assertions.assertEquals(countryJson.countryName(), countryResponse.getCountry().getCountryName());
        Assertions.assertEquals(countryJson.countryCode(), countryResponse.getCountry().getCountryCode());
        Assertions.assertFalse(countryResponse.getCountry().getId().isEmpty());
    }

	@Order(2)
	@DisplayName("Проверка изменения кода страны")
	@Test
	void checkEditCountry() {
		CountryJson countryJson = new CountryJson("QWERTY", "EWQ");
		CountryResponse countryResponse = countryClient.editCountry(countryJson);
		Assertions.assertEquals(countryJson.countryName(), countryResponse.getCountry().getCountryName());
		Assertions.assertEquals(countryJson.countryCode(), countryResponse.getCountry().getCountryCode());
		Assertions.assertFalse(countryResponse.getCountry().getId().isEmpty());
	}

	@DisplayName("Проверка получения страницы со странами")
	@Test
	void checkGetPageCountries() {
		CountriesResponse countriesResponse = countryClient.countries(1,10);
		Assertions.assertEquals(1,countriesResponse.getTotalPages());
		Assertions.assertEquals(10,countriesResponse.getTotalElements());
	}

}
