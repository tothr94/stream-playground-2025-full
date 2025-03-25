package countries.queries;

import countries.Country;
import countries.CountryRepository;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

public class ReducingQueriesImpl
        extends CountryRepository
        implements ReducingQueries {

    @Override
    public Optional<Long> getGreatestPopulationAsOptional() {
        this.getAll()
                .stream()
                .map(Country::population)
                .max(Long::compareTo);

        return this.getAll()
                .stream()
                .map(Country::population)
                .reduce((a, b) -> a > b ? a : b);
    }

    @Override
    public Long getGreatestPopulationAsLong() {
        return this.getAll()
                .stream()
                .map(Country::population)
                .reduce((a, b) -> a > b ? a : b)
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public Optional<BigDecimal> getTotalAreaAsOptional() {
        this.getAll()
                .stream()
                .map(Country::area)
                .filter(Objects::nonNull)
                .reduce((a, b) -> a.add(b));

        return this.getAll()
                .stream()
                .map(Country::area)
                .filter(Objects::nonNull)
                .reduce(BigDecimal::add);
    }

    @Override
    public BigDecimal getTotalAreaAsBigDecimal() {
        return this.getAll()
                .stream()
                .map(Country::area)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Optional<String> getCommaSeparatedNamesAsOptional() {
        return this.getAll()
                .stream()
                .map(Country::name)
                .sorted()
                .reduce((a, b) -> a + "," + b);
    }

    @Override
    public String getCommaSeparatedNames() {
        return this.getAll()
                .stream()
                .map(Country::name)
                .sorted()
                .reduce("", (a, b) -> a.isEmpty() ? b : a + "," + b);
    }
}
