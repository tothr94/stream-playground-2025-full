package countries.queries.collector;

import countries.Country;
import countries.CountryRepository;
import countries.Region;
import lombok.NonNull;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectingToMapQueriesImpl
        extends CountryRepository
        implements CollectingToMapQueries {

    @Override
    public Map<String, Country> getCountriesByCodes() {

        /*
        Map<String, Country> countries = new HashMap<>();
        for (Country country : getAll()) {
            countries.put(country.getCode(), country);
        }
        return countries;
         */

        getAll().stream()
                .collect(
                        Collector.of(
                                HashMap::new,
                                (HashMap<String, Country> res, Country c) -> res.put(c.code(), c),
                                (a, b) -> {
                                    a.putAll(b);
                                    return a;
                                })
                );


        getAll().stream()
                .collect(
                        Collectors.toMap(Country::code, c -> c)
                );


        getAll().stream()
                .collect(
                        Collectors.toMap(
                                Country::code,
                                Function.identity())
                );


        getAll().stream()
                .collect(
                        Collectors.toUnmodifiableMap(
                                Country::code,
                                Function.identity()
                        )
                );


        return getAll().stream()
                .collect(
                        Collector.of(
                                HashMap::new,
                                (HashMap<String, Country> res, Country c) -> res.put(c.code(), c),
                                (a, b) -> {
                                    a.putAll(b);
                                    return a;
                                },
                                Collections::unmodifiableMap
                        )
                );
    }

    @Override
    public Map<Region, Long> getCountOfCountriesByRegions() {
        getAll().stream()
                .collect(Collectors.groupingBy(
                        Country::region,
                        Collectors.counting())
                );

        getAll().stream()
                .collect(Collectors.groupingBy(
                        Country::region,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                group -> (long) group.size()
                        )
                ));

        return getAll().stream()
                .collect(Collector.of(
                        HashMap::new,
                        (HashMap<Region, Long> res, Country c) -> res.put(c.region(), res.getOrDefault(c.region(), 0L) + 1),
                        (a, b) -> {
                            for (Map.Entry<Region, Long> entry : b.entrySet()) {
                                a.put(entry.getKey(), a.getOrDefault(entry.getKey(), 0L) + entry.getValue());
                            }
                            return a;
                        }
                ));
    }

    @Override
    public Map<Boolean, List<Country>> getCountriesGroupByIndependenceAsList() {
        return getAll().stream()
                .collect(
                        Collectors.partitioningBy(Country::independent)
                );
    }

    @Override
    public Map<Boolean, Set<Country>> getCountriesGroupByIndependenceAsSet() {
        return getAll().stream()
                .collect(
                        Collectors.partitioningBy(
                                Country::independent,
                                Collectors.toSet()
                        )
                );
    }

    @Override
    public Map<Region, Set<Country>> getCountriesByRegions() {
        return getAll().stream()
                .collect(Collectors.groupingBy(
                        Country::region,
                        Collectors.toSet()
                ));
    }

    @Override
    public Map<Region, Optional<Country>> getMostPopulousCountryByRegions() {

        getAll().stream()
                .collect(Collectors.groupingBy(
                        Country::region,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                countries -> countries.stream()
                                        .sorted(Comparator.comparing(Country::population, Comparator.reverseOrder()))
                                        .findFirst()
                        )
                ));

        getAll().stream()
                .collect(Collectors.groupingBy(
                        Country::region,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                countries -> countries.stream()
                                        .min(Comparator.comparing(Country::population, Comparator.reverseOrder()))
                        )
                ));

        getAll().stream()
                .collect(Collectors.groupingBy(
                        Country::region,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                countries -> countries.stream()
                                        .max(Comparator.comparing(Country::population))
                        )
                ));

        return getAll().stream()
                .collect(Collectors.groupingBy(
                        Country::region,
                        Collectors.maxBy(Comparator.comparing(Country::population))
                ));
    }

    @Override
    public Map<Region, List<Country>> getCountriesByRegionsOrderByCapitals() {
        return getAll().stream()
                .collect(Collectors.groupingBy(
                        Country::region,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                countries -> countries.stream()
                                        .sorted(Comparator.comparing(Country::capital))
                                        .toList()
                        )
                ));
    }


    @Override
    public Map<Boolean, List<Country>> getCountriesGroupByIndependenceAsListOrderByName() {
        return getAll().stream()
                .collect(
                        Collectors.partitioningBy(
                                Country::independent,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        countries -> countries.stream()
                                                .sorted(Comparator.comparing(Country::name))
                                                .toList()
                                )
                        )
                );
    }

    @Override
    public Map<Region, Set<Country>> getCountriesByRegionsFilterByPopulation(long lowerBound, long upperBound) {
        return getAll().stream()
                .filter(country -> country.population() >= lowerBound && country.population() <= upperBound)
                .collect(Collectors.groupingBy(
                        Country::region,
                        Collectors.toUnmodifiableSet()
                ));
    }

    @Override
    public Map<Region, Map<String, Country>> getCountriesByRegionsAndCodes() {
        return getAll().stream()
                .collect(Collectors.groupingBy(
                        Country::region,
                        Collectors.toMap(
                                Country::code,
                                Function.identity()
                        )
                ));
    }

    @Override
    public Map<Region, Map<String, Set<Country>>> getCountriesByRegionsAndFirstLetters() {
        return getAll().stream()
                .collect(Collectors.groupingBy(
                        Country::region,
                        Collectors.groupingBy(
                                country -> country.name().substring(0, 1),
                                Collectors.toSet()
                        )
                ));
    }

    @Override
    public Map<String, Map<Region, Set<Country>>> getCountriesByFirstLettersAndRegions() {
        return getAll().stream()
                .collect(
                        Collectors.groupingBy(
                                country -> country.name().substring(0, 1),
                                Collectors.groupingBy(
                                        Country::region,
                                        Collectors.toSet())

                        ));
    }

    @Override
    public Map<Region, Set<String>> getLocalizedCountryNamesByRegions(@NonNull String locale) {
        return getAll().stream()
                .collect(
                        Collectors.groupingBy(
                                Country::region,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        countries -> countries.stream()
                                                .map(country -> country.translations().get(locale))
                                                .filter(Objects::nonNull)
                                                .collect(Collectors.toSet())
                                )
                        ));
    }

    @Override
    public Map<String, Set<String>> getCountryNamesByLocales() {
        return getAll().stream()
                .map(Country::translations)
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                entries -> entries.stream()
                                        .map(Map.Entry::getValue)
                                        .collect(Collectors.toSet())
                        )
                ));
    }

    @Override
    public Map<String, Set<String>> getCountryNamesByLocales(@NonNull Region region) {
        return getAll().stream()
                .filter(country -> country.region() == region)
                .map(Country::translations)
                .map(Map::entrySet)
                .flatMap(Collection::stream)
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                entries -> entries.stream()
                                        .map(Map.Entry::getValue)
                                        .collect(Collectors.toSet())
                        )
                ));
    }

    @Override
    public Map<Region, Optional<String>> getFirstLocalizedCountryNamesByRegions(@NonNull String locale) {
        return getAll().stream()
                .collect(Collectors.groupingBy(
                        Country::region,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                countries -> countries.stream()
                                        .map(Country::translations)
                                        .map(Map::entrySet)
                                        .flatMap(Collection::stream)
                                        .filter(entry -> Objects.equals(entry.getKey(), locale))
                                        .map(Map.Entry::getValue)
                                        .findFirst()
                        )
                ));
    }

    @Override
    public Map<Region, Optional<Country>> getFirstLocalizedCountriesByRegions(@NonNull String locale) {
        return getAll().stream()
                .collect(Collectors.groupingBy(
                        Country::region,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                countries -> countries.stream()
                                        .filter(country -> country.translations().containsKey(locale))
                                        .findFirst()
                        )
                ));
    }
}
