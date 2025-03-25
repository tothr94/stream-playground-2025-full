import countries.*;
import java.time.ZoneId;
import static java.util.stream.Collectors.*;

var countries = new CountryRepository().getAll();

// 1. Returns the longest country name translation:

countries.stream().
  flatMap(country -> country.translations().values().stream()).
  max(Comparator.comparingInt(String::length));

// 2. Returns the longest Farsi (i.e., `"fa"`) country name translation:

countries.stream().
  map(country -> country.translations().get("fa")).
  filter(Objects::nonNull).
  max(Comparator.comparingInt(String::length));

// 3. Prints the longest country name translation together with its language code in the form language=translation:

countries.stream().
  flatMap(country -> country.translations().entrySet().stream()).
  max(Comparator.comparingInt(entry -> entry.getValue().length())).
  ifPresent(System.out::println);

// 4. Prints single-word country names (i.e., country names that do not contain any space characters):

countries.stream().
  map(Country::name).
  filter(s -> s.matches("^[^\\s]+$")).
  sorted().
  forEach(System.out::println);

// 5. Returns the country name with the most number of words:

countries.stream().
  map(Country::name).
  max(Comparator.comparingInt(s -> s.split("[\\s\\p{Punct}]+").length));

// 6. Returns whether at least one capital that is a palindrome exists: 

countries.stream().
  map(Country::capital).
  filter(Objects::nonNull).
  anyMatch(capital -> capital.toLowerCase().equals(new StringBuilder(capital.toLowerCase()).reverse().toString()));

// 7. Returns the country name with the most number of `'e'` characters ignoring case:

int charCount(String s, char c) {
  int count = 0;
  for (int i = 0; i < s.length(); i++) {
    if (s.charAt(i) == c) {
      count++;
    }
  }
  return count;
}

countries.stream().
  map(Country::name).
  max(Comparator.comparingInt(s -> charCount(s.toLowerCase(), 'e')));

// 8. Returns the capital with the most number of English vowels (i.e., `'a'`, `'e'`, `'i'`, `'o'`, `'u'`):

int vowelCount(String s) {
  s = s.toLowerCase();
  int count = 0;
  for (int i = 0; i < s.length(); i++) {
    switch(s.charAt(i)) {
      case 'a':
      case 'e':
      case 'i':
      case 'o':
      case 'u':
        count++;
    }
  }
  return count;
}

countries.stream().
  map(Country::capital).
  filter(Objects::nonNull).
  max(Comparator.comparingInt(s -> vowelCount(s)));

// 9.  Returns a map that contains for each character the number of occurrences in country names ignoring case:

countries.stream().
  map(Country::name).
  map(String::toLowerCase).
  flatMapToInt(s -> s.chars()).
  mapToObj(codePoint -> (char) codePoint).
  collect(groupingBy(Function.identity(), counting()));

// 10. Returns a map that contains the number of countries for each possible time zone:

countries.stream().
  flatMap(country -> country.timezones().stream()).
  collect(groupingBy(Function.identity(), counting()));

// 11. Returns the number of country names by region that starts with their two-letter country code ignoring case:

countries.stream().
  filter(country -> country.name().toLowerCase().startsWith(country.code().toLowerCase())).
  forEach(country -> System.out.printf("%s: %s\n", country.code(), country.name()));

countries.stream().collect(groupingBy(Country::region, filtering(country -> country.name().toLowerCase().startsWith(country.code().toLowerCase()), counting())));

// 12. Returns a map containing the number of countries whose population is greater or equal to the population average versus the number of countries with a population below the average:

double avgPopulation = countries.stream().
  mapToDouble(Country::population).
  average().
  getAsDouble();

countries.stream().collect(partitioningBy(country -> country.population() >= avgPopulation, counting()));

// 13. Returns a map that contains for each country code the name of the corresponding country in Portuguese ("pt"):

countries.stream().collect(toMap(Country::code, country -> country.translations().get("pt")));

// 14. Returns the list of capitals by region whose name is the same as the name of their country:

countries.stream().
  filter(country -> country.name().equals(country.capital())).
  map(Country::name).
  forEach(System.out::println);

countries.stream().collect(groupingBy(Country::region, filtering(country -> country.name().equals(country.capital()), mapping(Country::name, toList()))));

// 15. Returns a map of country name-population density pairs:

countries.stream().collect(toMap(Country::name, country -> country.area() == null ? Double.NaN : country.population() / country.area().doubleValue()));
