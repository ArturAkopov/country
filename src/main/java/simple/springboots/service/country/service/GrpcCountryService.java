package simple.springboots.service.country.service;

import com.google.protobuf.Empty;
import country.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import simple.springboots.service.country.model.CountryJson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class GrpcCountryService extends CountryServiceGrpc.CountryServiceImplBase {

    private final CountryService countryService;

    private static final Random random = new Random();

    public GrpcCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void allCountries(Empty request, StreamObserver<CountryResponse> responseObserver) {
        try {
            List<CountryJson> countries = countryService.allCountries();
            CountryResponse response = CountryResponse.newBuilder()
                    .addAllCountries(countries.stream()
                            .map(this::convertToProto)
                            .toList())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Failed to get countries: " + e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void addCountry(CountryRequest request, StreamObserver<CountryResponse> responseObserver) {
        try {
            CountryJson addedCountry = countryService.addCountry(
                    request.getCountryName(),
                    request.getCountryCode()
            );

            CountryResponse response = CountryResponse.newBuilder()
                    .addCountries(convertToProto(addedCountry))
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Failed to add country: " + e.getMessage())
                    .asRuntimeException());
        }
    }

    @Override
    public void randomCountry(CountRequest request, StreamObserver<CountryResponse> responseObserver) {
        final List<CountryJson> allCountries = countryService.allCountries();
        try {
            for (int i = 0; i < request.getCount(); i++) {
                CountryJson randomCountry = allCountries.get(random.nextInt(allCountries.size()));
                CountryResponse response = CountryResponse.newBuilder()
                        .addCountries(convertToProto(randomCountry))
                        .build();
                responseObserver.onNext(response);
            }
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Failed to get random countries: " + e.getMessage())
                    .asRuntimeException());
        }
    }

    @NonNull
    private country.Country convertToProto(@NonNull CountryJson json) {
        return country.Country.newBuilder()
                .setCountryName(json.countryName())
                .setCountryCode(json.countryCode())
                .build();
    }

    @Override
    public StreamObserver<CountryRequest> addCountries(StreamObserver<CountResponse> responseObserver) {
        return new StreamObserver<CountryRequest>() {
            final AtomicInteger counter = new AtomicInteger(0);
            final AtomicInteger errorCounter = new AtomicInteger(0);
            final List<CompletableFuture<CountryJson>> futures = new ArrayList<>();
            final List<String> errorMessages = Collections.synchronizedList(new ArrayList<>());

            @Override
            public void onNext(CountryRequest request) {
                CompletableFuture<CountryJson> future = CompletableFuture.supplyAsync(() -> {
                    try {
                        CountryJson result = countryService.addCountry(
                                request.getCountryName(),
                                request.getCountryCode()
                        );
                        counter.incrementAndGet();
                        return result;
                    } catch (Exception e) {
                        errorCounter.incrementAndGet();
                        errorMessages.add("Failed to add country: " + request.getCountryName()
                                          + " (" + request.getCountryCode() + "). Error: " + e.getMessage());
                        throw new CompletionException(e);
                    }
                });
                futures.add(future);
            }

            @Override
            public void onError(Throwable t) {
                responseObserver.onError(Status.INTERNAL
                        .withDescription("Stream error: " + t.getMessage())
                        .asRuntimeException());
            }

            @Override
            public void onCompleted() {
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                        .whenComplete((result, throwable) -> {
                            if (errorCounter.get() > 0) {
                                // Частичный успех - некоторые страны добавлены
                                String errorDetails = String.format(
                                        "Added %d countries, failed %d. Errors: %s",
                                        counter.get(),
                                        errorCounter.get(),
                                        String.join("; ", errorMessages)
                                );

                                responseObserver.onNext(CountResponse.newBuilder()
                                        .setCount(counter.get())
                                        .build());
                                responseObserver.onError(Status.INTERNAL
                                        .withDescription(errorDetails)
                                        .asRuntimeException());
                            } else {
                                // Все страны успешно добавлены
                                responseObserver.onNext(CountResponse.newBuilder()
                                        .setCount(counter.get())
                                        .build());
                                responseObserver.onCompleted();
                            }
                        });
            }
        };
    }
}
