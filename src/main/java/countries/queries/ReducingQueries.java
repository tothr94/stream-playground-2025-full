package countries.queries;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.BinaryOperator;

/**
 * Defines queries that use {@link java.util.stream.Stream#reduce(BinaryOperator)} and {@link java.util.stream.Stream#reduce(Object, BinaryOperator)} methods.
 */
public interface ReducingQueries {
    /**
     * {@return the total area of the countries}
     */
    Optional<BigDecimal> getTotalAreaAsOptional();

    /**
     * {@return the total area of the countries}
     */
    BigDecimal getTotalAreaAsBigDecimal();

    /**
     * {@return the string that contains all the country names in ascending order, separated by commas}
     */
    Optional<String> getCommaSeparatedNamesAsOptional();

    /**
     * {@return the string that contains all the country names in ascending order, separated by commas}
     */
    String getCommaSeparatedNames();
}
