package system;

import java.io.Serializable;

/**
 * Interface representing a city entity within the directory.
 * A city is uniquely identified by its name and the country to which it belongs.
 */
public interface City extends Serializable {

    /**
     * Returns the name of the city, preserving original capitalization.
     * 
     * @return The name of the city.
     */
    String getName();

    /**
     * Returns the name of the country to which the city belongs.
     * 
     * @return The country name.
     */
    String getCountry();

    /**
     * Returns the population (number of inhabitants) of the city.
     * 
     * @return The number of inhabitants.
     */
    int getPopulation();

    /**
     * Checks if this city matches the given city name and country name,
     * ignoring case differences.
     * 
     * @param cityName    The city name to compare.
     * @param countryName The country name to compare.
     * @return true if both names match, false otherwise.
     */
    boolean matches(String cityName, String countryName);
}
