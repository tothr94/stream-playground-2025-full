stream-playground
=================

Playground for playing with [streams](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/stream/package-summary.html) in Java.

The `countries.CountryRepository` class provides access to 250 `Country` objects representing the countries of the world. Country data is taken from the [REST Countries](https://restcountries.com/) API from [here](https://restcountries.com/v2/all?fields=alpha2Code,name,capital,region,area,population,translations,timezones,independent).

The `brickset.LegoSetRepository` class provides access to 938 `LegoSet` objects representing the [LEGO](https://www.lego.com/) sets released in 2024. LEGO set data is taken from [Brickset](https://brickset.com/) via the [Brickset API](https://brickset.com/article/52664/api-version-3-documentation).

Usage:
```console
$ mvn compile jshell:run
jshell> import countries.*;
jshell> import brickset.*;
jshell> var countries = new CountryRepository().getAll(); // The list of all countries
jshell> var legoSets = new LegoSetRepository().getAll();  // The list of all LEGO sets
```

Building and running the project requires JDK 17 or later.
