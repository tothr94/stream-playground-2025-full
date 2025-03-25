package countries.queries.collector;

import countries.Country;
import countries.Region;
import lombok.NonNull;

import java.util.*;

/**
 * Defines queries that return {@link Map} instances using {@link java.util.stream.Collector} instances.
 */
public interface CollectingToMapQueries {

    /**
     * Returns a dictionary which maps each country code to the corresponding country.
     *
     * @return the dictionary
     */
    Map<String, Country> getCountriesByCodes();

    /**
     * Returns a dictionary which maps each region to the count of its countries.
     *
     * @return the dictionary
     */
    Map<Region, Long> getCountOfCountriesByRegions();

    /**
     * Returns a dictionary that maps the independence values to the list of corresponding countries.
     *
     * @return the dictionary
     */
    Map<Boolean, List<Country>> getCountriesGroupByIndependenceAsList();

    /**
     * Returns a dictionary that maps the independence values to the set of corresponding countries.
     *
     * @return the dictionary
     */
    Map<Boolean, Set<Country>> getCountriesGroupByIndependenceAsSet();

    /**
     * Returns a dictionary which maps each region to its countries.
     *
     * @return the dictionary
     */
    Map<Region, Set<Country>> getCountriesByRegions();

    /**
     * Returns a dictionary which maps each region to its most populous country.
     *
     * @return the dictionary
     */
    Map<Region, Optional<Country>> getMostPopulousCountryByRegions();

    /**
     * Returns a dictionary which maps each region the list of its countries ordered by their capital names to their continent.
     *
     * @return the dictionary
     */
    Map<Region, List<Country>> getCountriesByRegionsOrderByCapitals();

    /**
     * Returns a dictionary that maps the independence values to the list of corresponding countries (ordered by their names in descending order).
     *
     * @return the dictionary
     */
    Map<Boolean, List<Country>> getCountriesGroupByIndependenceAsListOrderByName();

    /**
     * Returns a dictionary which maps each region to the corresponding countries which population is between the given bounds (inclusive) to their continent.
     *
     * @param lowerBound the lower bound
     * @param upperBound the upper bound
     * @return the dictionary
     */
    Map<Region, Set<Country>> getCountriesByRegionsFilterByPopulation(
            long lowerBound,
            long upperBound);

    /**
     * Returns a dictionary which maps each region to the corresponding country codes, then each country code to the corresponding country.
     *
     * @return the dictionary
     */
    Map<Region, Map<String, Country>> getCountriesByRegionsAndCodes();

    /**
     * Returns a dictionary which maps region to the letters that occur in the first place of corresponding
     * country names, then each letter to the corresponding countries.
     *
     * @return the dictionary
     */
    Map<Region, Map<String, Set<Country>>> getCountriesByRegionsAndFirstLetters();

    /**
     * Returns a dictionary which maps the letters that occur in the first place of corresponding
     * country names to regions, then each letter to the corresponding countries.
     *
     * @return the dictionary
     */
    Map<String, Map<Region, Set<Country>>> getCountriesByFirstLettersAndRegions();

    /**
     * Returns a dictionary which maps each region to the set of country names, using the given locale.
     *
     * @param locale the locale
     * @return the dictionary
     */
    Map<Region, Set<String>> getLocalizedCountryNamesByRegions(
            @NonNull String locale);

    /**
     * Returns a dictionary which maps each locale to the corresponding country names.
     *
     * @return the dictionary
     */
    Map<String, Set<String>> getCountryNamesByLocales();

    /**
     * Returns a dictionary which maps each locale to the corresponding country names which countries are located in the given region.
     *
     * @param region the region
     * @return the dictionary
     */
    Map<String, Set<String>> getCountryNamesByLocales(
            @NonNull Region region);

    /**
     * Returns a dictionary which maps each region to the country name which is the first in the given locale.
     *
     * @param locale the locale
     * @return the dictionary
     */
    Map<Region, Optional<String>> getFirstLocalizedCountryNamesByRegions(
            @NonNull String locale);

    /**
     * Returns a dictionary which maps each region to the country which name is the first in the given locale.
     *
     * @param locale the locale
     * @return the dictionary
     */
    Map<Region, Optional<Country>> getFirstLocalizedCountriesByRegions(
            @NonNull String locale);
}
