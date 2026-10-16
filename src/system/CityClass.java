package system;

/**
 * Implementation of the City entity, storing the city name, the country to which 
 * it belongs, and its number of inhabitants (population).
 * 
 * Space complexity: O(1) — each instance occupies a constant amount of memory.
 */
public class CityClass implements City {

    private String name;
    private String country;
    private int population;

    /**
     * Initializes a new city with its name, country, and population.
     * 
     * @param name       The name of the city.
     * @param country    The country to which the city belongs.
     * @param population The number of inhabitants of the city.
     */
    public CityClass(String name, String country, int population) {
        this.name = name;
        this.country = country;
        this.population = population;
    }

    /**
     * Returns the name of the city (preserving its original capitalization).
     * 
     * @return The name of the city.
     * 
     * Time complexity:
     * - Best case: O(1)
     * - Worst case: O(1)
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the name of the country to which the city belongs.
     * 
     * @return The name of the country.
     * 
     * Time complexity:
     * - Best case: O(1)
     * - Worst case: O(1)
     */
    @Override
    public String getCountry() {
        return country;
    }

    /**
     * Returns the number of inhabitants of the city.
     * 
     * @return The population of the city.
     * 
     * Time complexity:
     * - Best case: O(1)
     * - Worst case: O(1)
     */
    @Override
    public int getPopulation() {
        return population;
    }

    /**
     * Checks whether this city matches the given city name and country name,
     * ignoring case differences.
     * 
     * @param cityName    The city name to compare with.
     * @param countryName The country name to compare with.
     * @return true if both the city name and country match, false otherwise.
     * 
     * Time complexity:
     * - Best case: O(1)
     * - Worst case: O(1) (assuming string lengths are bounded by a constant).
     */
    @Override
    public boolean matches(String cityName, String countryName) {
        return this.name.equalsIgnoreCase(cityName) && 
               this.country.equalsIgnoreCase(countryName);
    }

}
