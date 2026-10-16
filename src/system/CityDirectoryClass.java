package system;

import dataStructures.Iterator;
import dataStructures.SortedLinkedList;

/**
 * Implementation of the persistent CityDirectory, managing a collection of cities
 * kept sorted using a sorted linked list.
 * 
 * Space complexity: O(n), where n is the number of cities stored in the directory.
 */
public class CityDirectoryClass implements CityDirectory {

    private final SortedLinkedList<City> cities;

    /**
     * Initializes an empty city directory configured with the CityComparator.
     * 
     * Time complexity:
     * - Best case: O(1)
     * - Worst case: O(1)
     */
    public CityDirectoryClass() {
        this.cities = new SortedLinkedList<>(new CityComparator());
    }

    /**
     * Searches for and returns a city in the directory matching the given city name
     * and country name (case-insensitive).
     * 
     * @param cityName    The name of the city to search for.
     * @param countryName The name of the country to search for.
     * @return The matching City object if found, or null otherwise.
     * 
     * Time complexity:
     * - Best case: O(1) — when the target city is at the head of the list.
     * - Worst case: O(n) — when the target city is at the end of the list or does not exist,
     *   requiring a traversal of all n cities.
     */
    @Override
    public City findCity(String cityName, String countryName) {
        Iterator<City> it = cities.iterator();
        while (it.hasNext()) {
            City c = it.next();
            if (c.matches(cityName, countryName)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Adds a new city to the directory if a city with the same name and country
     * does not already exist.
     * 
     * @param name       The name of the city.
     * @param country    The country to which the city belongs.
     * @param population The number of inhabitants of the city.
     * @return true if the city was successfully added, false if it already exists.
     * 
     * Time complexity:
     * - Best case: O(1) — when the city already exists at the head of the list
     * - Worst case: O(n) — when the city does not exist (findCity traverses all n elements)
     *   and the sorted insertion into the SortedLinkedList takes up to O(n) steps.
     */
    @Override
    public boolean addCity(String name, String country, int population) {
        if (findCity(name, country) != null) {
            return false;
        }
        City newCity = new CityClass(name, country, population);
        cities.add(newCity);
        return true;
    }

    /**
     * Removes an existing city from the directory given its name and country.
     * 
     * @param name    The name of the city to remove.
     * @param country The name of the country of the city to remove.
     * @return true if the city was found and removed, false if it does not exist.
     * 
     * Time complexity:
     * - Best case: O(1) — when the city to remove is located at the head of the list.
     * - Worst case: O(n) — when the city is at the end of the list or does not exist,
     *   requiring a traversal of all n elements.
     */
    @Override
    public boolean removeCity(String name, String country) {
        City existing = findCity(name, country);
        if (existing == null) {
            return false;
        }
        return cities.remove(existing) != null;
    }

    /**
     * Checks whether the city directory is empty.
     * 
     * @return true if there are no cities in the directory, false otherwise.
     * 
     * Time complexity:
     * - Best case: O(1)
     * - Worst case: O(1)
     */
    @Override
    public boolean isEmpty() {
        return !cities.iterator().hasNext();
    }

    /**
     * Returns an iterator over all cities currently stored in the directory,
     * sorted according to the criteria defined in CityComparator.
     * 
     * @return An Iterator of City objects.
     * 
     * Time complexity:
     * - Best case: O(1)
     * - Worst case: O(1)
     */
    @Override
    public Iterator<City> iterator() {
        return cities.iterator();
    }

}
