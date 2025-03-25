package countries.queries;

import lombok.NonNull;

import java.time.ZoneId;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * Defines queries that use the {@link java.util.stream.Stream#flatMap(Function)} method.
 */
public interface FlatMapQueries {
    /**
     * Returns the sorted list of all the timezones. Each element must appear only once.
     *
     * @return list
     */
    List<ZoneId> getAllEuropeanZoneIdsAsList();

    /**
     * Returns the set of all the timezones.
     *
     * @return list
     */
    Set<ZoneId> getAllEuropeanZoneIdsAsSet();

    /**
     * Returns the sorted list of all the Italian translations.
     *
     * @return the list
     */
    List<String> getItalianTranslations();

    /**
     * {@return all the translations in the given {@param language}}
     *
     * @param language the language
     */
    List<String> getTranslations(@NonNull String language);
}
