package system;

import java.io.Serializable;

public interface City extends Serializable {
    String getName();
    String getCountry();
    int getPopulation();
    boolean matches(String cityName, String countryName);
}
