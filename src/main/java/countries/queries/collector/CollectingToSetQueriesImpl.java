package countries.queries.collector;

import countries.Country;
import countries.CountryRepository;
import countries.Region;
import lombok.NonNull;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectingToSetQueriesImpl
        extends CountryRepository
        implements CollectingToSetQueries {

    @Override
    public Set<String> getCountryNames() {
        getAll().stream()
                .map(Country::name)
                .collect(Collectors.toSet());

        getAll().stream()
                .map(Country::name)
                .collect(Collectors.toUnmodifiableSet());

        getAll().stream()
                .map(Country::name)
                .collect(Collectors.toCollection(TreeSet::new));


        getAll().stream()
                .map(Country::name)
                .collect(Collectors.toCollection(HashSet::new));

        return getAll().stream()
                .map(Country::name)
                .collect(Collector.of(
                        HashSet::new,
                        HashSet::add,
                        (a, b) -> {
                            a.addAll(b);
                            return a;
                        }
                ));
    }

    @Override
    public List<String> getCapitalsOrderByName() {
        return getAll().stream()
                .map(Country::capital)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public Set<String> getNamesOfEuropeanCountries() {
        return getAll().stream()
                .filter(country -> country.region() == Region.EUROPE)
                .map(Country::capital)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getNamesOfCountriesFilterByContinent(@NonNull Region region) {
        return getAll().stream()
                .filter(country -> country.region() == region)
                .map(Country::name)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Country> getCountriesBelowPopulationLimit(int limit) {
        return getAll().stream()
                .filter(country -> country.population() < limit)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Long> getPopulationsByRegion(@NonNull Region region) {
        return getAll().stream()
                .filter(country -> country.region() == region)
                .map(Country::population)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Country> getCountriesByPopulation(long population) {
        return getAll().stream()
                .filter(country -> country.population() == population)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Country> getCountriesByPopulation(long lowerBound, long upperBound) {
        Predicate<Country> lowerPredicate = (Country country) -> country.population() >= lowerBound;
        Predicate<Country> upperPredicate = (Country country) -> country.population() <= upperBound;
        Predicate<Country> compoundPredicate = lowerPredicate.and(upperPredicate);

        getAll().stream()
                .filter(compoundPredicate)
                .collect(Collectors.toSet());

        return getAll().stream()
                .filter(country -> country.population() >= lowerBound)
                .filter(country -> country.population() <= upperBound)
                .collect(Collectors.toSet());
    }
}
