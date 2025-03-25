import countries.*;

var countries = new CountryRepository().getAll();
var hungary = countries.get(101);
var turkey = countries.get(231);

// Creating a Comparator to compare Country objects using an anonymous class:
Comparator<Country> comparator = new Comparator<Country>() {
    @Override
    public int compare(Country c1, Country c2) {
        return Long.compare(c1.population(), c2.population());
    }
};

comparator.compare(hungary, turkey);
comparator.compare(hungary, hungary);
comparator.compare(turkey, hungary);

Comparator<Country> reversedComparator = comparator.reversed(); // reversed() is a default method of Comparator

// Creating a Comparator to compare Country objects using a lambda expression (1):
Comparator<Country> comparator = (Country c1, Country c2) -> {
    return Long.compare(c1.population(), c2.population());
};

comparator.compare(hungary, turkey);
comparator.compare(hungary, hungary);
comparator.compare(turkey, hungary);

// Creating a Comparator to compare Country objects using a lambda expression (2):
Comparator<Country> comparator = (c1, c2) -> Long.compare(c1.population(), c2.population());

comparator.compare(hungary, turkey);
comparator.compare(hungary, hungary);
comparator.compare(turkey, hungary);

// Creating a Comparator to compare Country objects with the static Comparator.comparingLong() method and a ToLongFunction object:

ToLongFunction<Country> populationExtractor = new ToLongFunction<Country>() {
    @Override
    public long applyAsLong(Country c) {
        return c.population();
    }
};

Comparator<Country> comparator = Comparator.comparingLong(populationExtractor);

comparator.compare(hungary, turkey);
comparator.compare(hungary, hungary);
comparator.compare(turkey, hungary);

// Creating a Comparator to compare Country objects with the static Comparator.comparingLong() method and a lambda expression:

Comparator<Country> comparator = Comparator.comparingLong(country -> country.population());

comparator.compare(hungary, turkey);
comparator.compare(hungary, hungary);
comparator.compare(turkey, hungary);

// Creating a Comparator to compare Country objects with the static Comparator.comparingLong() method and a method reference:

Comparator<Country> populationComparator = Comparator.comparingLong(Country::population);

populationComparator.compare(hungary, turkey);
populationComparator.compare(hungary, hungary);
populationComparator.compare(turkey, hungary);

// Working with the Comparator:

countries.sort(populationComparator); // throws UnsupportedOperationException

countries.stream().max(populationComparator); // returns an Optional
Optional<Country> mostPopulousCountry = countries.stream().max(populationComparator);
mostPopulousCountry.get();
mostPopulousCountry.ifPresent(System.out::println)

var sortedCountries = countries.stream().sorted(populationComparator).toList();

Comparator<Country> nameComparator = Comparator.comparing(Country::name);
var sortedCountries = countries.stream().sorted(populationComparator.reversed().thenComparing(nameComparator)).toList();
