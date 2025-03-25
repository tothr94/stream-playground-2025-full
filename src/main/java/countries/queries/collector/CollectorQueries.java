package countries.queries.collector;

import java.util.StringJoiner;


/**
 * Defines queries that are related to basic {@link java.util.stream.Collector} instances.
 */
public interface CollectorQueries {
    StringJoiner getCommaSeparatedNamesAsStringJoiner();

    String getCommaSeparatedNamesAsString();
}
