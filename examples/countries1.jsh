import countries.*;
var countries = new CountryRepository().getAll();

// 1. Prints country names:

countries.stream().
  map(Country::name).
  forEach(System.out::println);

// 2. Prints the capital of each country in alphabetical order:

countries.stream().
  map(Country::capital).
  sorted(Comparator.nullsFirst(Comparator.naturalOrder())).
  forEach(System.out::println);

// 3. Prints the capital of each country in reverse alphabetical order:

countries.stream().
  map(Country::capital).
  sorted(Comparator.nullsLast(Comparator.reverseOrder())).
  forEach(System.out::println);

// 4. Returns the maximum population:

countries.stream().
  mapToLong(Country::population).
  max().
  getAsLong();

// 5. Returns population average:

countries.stream().
  mapToLong(Country::population).
  average().
  getAsDouble();

// 6. Returns summary statistics about the population field:

countries.stream().
  mapToLong(Country::population).
  summaryStatistics();

// 7. Prints the names of European countries:

countries.stream().
  filter(country -> country.region() == Region.EUROPE).
  map(Country::name).
  forEach(System.out::println);

// 8. Returns the number of European countries:

countries.stream().
  filter(country -> country.region() == Region.EUROPE).
  map(Country::name).
  count();

// 9. Returns the number of independent countries:

countries.stream().
  filter(country -> country.independent()).
  count();

countries.stream().
  filter(Country::independent).
  count();

// 10. Prints all countries with a population below 100:

countries.stream().
  filter(country -> country.population() < 100).
  forEach(System.out::println);

// 11. Prints the names of countries with a population below 100:

countries.stream().
  filter(country -> country.population() < 100).
  map(Country::name).
  forEach(System.out::println);

// 12. Returns the sum of the population of European countries:

countries.stream().
  filter(country -> country.region() == Region.EUROPE).
  mapToLong(Country::population).
  sum();

// 13. Prints the population of European countries in ascending order:

countries.stream().
  filter(country -> country.region() == Region.EUROPE).
  mapToLong(Country::population).
  sorted().
  forEach(System.out::println)

// 14. Prints the population of European countries in descending order:

countries.stream().
  filter(country -> country.region() == Region.EUROPE).
  mapToLong(Country::population).
  boxed(). // returns a Stream of java.lang.Long objects (required because LongStream has only a no-argument sorted() operation)
  sorted(Comparator.reverseOrder()).
  forEach(System.out::println)

// 15. Returns the European country with the highest population:

Country mostPopulousCountry = countries.stream().
  filter(country -> country.region() == Region.EUROPE).
  max(Comparator.comparingLong(Country::population)).
  get();

// 16. Returns the name of the European country with the highest population:

countries.stream().
  filter(country -> country.region() == Region.EUROPE).
  max(Comparator.comparingLong(Country::population)).
  get().
  name();

// 17. Prints the names of the first five countries:

countries.stream().
  map(Country::name).
  limit(5).
  forEach(System.out::println);

// 18. Returns whether there is at least one country with zero population:

countries.stream().anyMatch(country -> country.population() == 0);

// 19. Returns whether each country has at least one time zone:

countries.stream().allMatch(country -> ! country.timezones().isEmpty());
countries.stream().allMatch(country -> country.timezones().size() > 0);

// 20. Returns the first country whose name starts with ‘H’:

countries.stream().
  filter(country -> country.name().charAt(0) == 'H').
  findFirst();

countries.stream().
  filter(country -> country.name().startsWith("H")).
  findFirst();

// 21. Returns the number of all distinct time zones:

long numberOfTimezones = countries.stream().
  flatMap(country -> country.timezones().stream()).
  distinct().
  count();

// 22. Prints all distinct time zones of European countries:

countries.stream().
  filter(country -> country.region() == Region.EUROPE).
  flatMap(country -> country.timezones().stream()).
  distinct().
  forEach(System.out::println);

// 23. Prints the name and population of each country in descending order of population:

countries.stream().
  sorted(Comparator.comparingLong(Country::population)).
  forEach(country -> System.out.printf("%s: %d\n", country.name(), country.population()));

// 24. Returns the length of the longest country name:

countries.stream().
  map(Country::name).
  max(Comparator.comparingInt(String::length)).
  get();

// 25. Prints the capital of each country in ascending order of length:

countries.stream().
  map(Country::capital).
  sorted(Comparator.nullsFirst(Comparator.comparingInt(String::length))).
  forEach(System.out::println);

// 26. Prints the capital of each country in ascending order of length and then in alphabetical order:

countries.stream().
  map(Country::capital). sorted(Comparator.nullsFirst(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))).
  forEach(System.out::println);
