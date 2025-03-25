package simple.springboots.service.country.domain.graphql;

import java.util.UUID;

public record CountryGql(
        UUID id,
        String countryName,
        String countryCode) {
}
