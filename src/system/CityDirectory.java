package system;

import java.io.Serializable;
import dataStructures.Iterator;

/**
 * Interface representing the city directory that manages a collection
 * of cities and provides operations for searching, adding, removing, and listing.
 */
public interface CityDirectory extends Serializable {

    /**
     * Searches for a city in the directory by name and country (case-insensitive).
     * 
     * @param cityName    The name of the city to find.
     * @param countryName The name of the country.
     * @return The City object if found, or null otherwise.
     */
    City findCity(String cityName, String countryName);

    /**
     * Adds a new city to the directory if it does not already exist.
     * 
     * @param name       The name of the city.
     * @param country    The name of the country.
     * @param population The population count of the city.
     * @return true if added successfully, false if the city already exists.
     */
    boolean addCity(String name, String country, int population);

    /**
     * Removes a city from the directory given its name and country.
     * 
     * @param name    The name of the city to remove.
     * @param country The name of the country.
     * @return true if found and removed, false if not found.
     */
    boolean removeCity(String name, String country);

    /**
     * Checks if the directory contains no cities.
     * 
     * @return true if empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Returns an iterator over all cities stored in the directory, sorted according
     * to the application specification.
     * 
     * @return An Iterator of City elements.
     */
    Iterator<City> iterator();
}
