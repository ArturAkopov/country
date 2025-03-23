package simple.springboots.service.country.domain;


import jakarta.annotation.Nonnull;
import simple.springboots.service.country.data.CountryEntity;

public record Country(
        String countryName,
        String countryCode) {

    public static @Nonnull Country fromEntity(@Nonnull CountryEntity entity) {
        return new Country(
                entity.getCountryName(),
                entity.getCountryCode()
        );
    }

}
