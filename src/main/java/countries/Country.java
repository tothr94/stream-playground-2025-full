package countries;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

public record Country(@JsonProperty("alpha2Code") String code,
                      String name,
                      String capital,
                      Region region,
                      long population,
                      BigDecimal area,
                      Map<String, String> translations,
                      List<ZoneId> timezones,
                      boolean independent) {
}
