package brickset;

import java.time.Year;
import java.util.Set;

public record LegoSet(String number,
                      String name,
                      Year year,
                      String theme,
                      String subtheme,
                      PackagingType packagingType,
                      int pieces,
                      String bricksetURL,
                      Set<String> tags,
                      Dimensions dimensions) {
}
