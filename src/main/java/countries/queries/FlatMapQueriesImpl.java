package countries.queries;

import countries.Country;
import countries.CountryRepository;
import countries.Region;
import lombok.NonNull;

import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public class FlatMapQueriesImpl
        extends CountryRepository
        implements FlatMapQueries {

    @Override
    public List<ZoneId> getAllEuropeanZoneIdsAsList() {
        return getAll().stream()
                .filter(country -> country.region() == Region.EUROPE)
                .map(Country::timezones)
                .flatMap(Collection::stream)
                .distinct()
                .sorted()
                .toList();
    }

    @Override
    public Set<ZoneId> getAllEuropeanZoneIdsAsSet() {
        return getAll().stream()
                .filter(country -> country.region() == Region.EUROPE)
                .map(Country::timezones)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public List<String> getItalianTranslations() {
        return getAll()
                .stream()
                .map(Country::translations)
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .filter(entry -> Objects.equals(entry.getKey(), "it"))
                .map(Map.Entry::getValue)
                .sorted()
                .toList();
    }

    @Override
    public List<String> getTranslations(
            @NonNull String language) {
        return getAll()
                .stream()
                .map(Country::translations)
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .filter(entry -> Objects.equals(entry.getKey(), language))
                .map(Map.Entry::getValue)
                .toList();
    }

    public static void main(String[] args) {
        final var repo = new FlatMapQueriesImpl();
        System.out.println(repo.getItalianTranslations());
        System.out.println(repo.getTranslations("hu"));
        System.out.println(repo.getTranslations("xx"));
    }
}
