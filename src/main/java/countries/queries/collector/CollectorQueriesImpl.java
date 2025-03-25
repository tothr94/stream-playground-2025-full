package countries.queries.collector;

import countries.Country;
import countries.CountryRepository;

import java.util.StringJoiner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectorQueriesImpl
        extends CountryRepository
        implements CollectorQueries {

    @Override
    public StringJoiner getCommaSeparatedNamesAsStringJoiner() {
        return this.getAll()
                .stream()
                .map(Country::name)
                .sorted()
                .collect(Collector.of(
                        () -> new StringJoiner(","),
                        StringJoiner::add,
                        StringJoiner::merge
                ));
    }

    @Override
    public String getCommaSeparatedNamesAsString() {
        this.getAll()
                .stream()
                .map(Country::name)
                .sorted()
                .collect(Collector.of(
                        () -> new StringJoiner(","),
                        StringJoiner::add,
                        StringJoiner::merge,
                        StringJoiner::toString
                ));

        return this.getAll()
                .stream()
                .map(Country::name)
                .sorted()
                .collect(Collectors.joining(","));
    }
}
