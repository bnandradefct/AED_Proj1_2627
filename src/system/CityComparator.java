package system;

import java.io.Serializable;
import dataStructures.Comparator;

/**
 * Comparator for City objects, defining the sorting order by:
 * 1. Country name (ascending alphabetical order);
 * 2. Population (descending order);
 * 3. City name (ascending case-insensitive alphabetical order).
 * 
 * Space complexity: O(1) — stateless comparator occupying a constant amount of memory.
 */
public class CityComparator implements Comparator<City>, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Compares two cities for order according to country (ascending),
     * population (descending), and city name (ascending, case-insensitive).
     * 
     * @param c1 The first city to be compared.
     * @param c2 The second city to be compared.
     * @return A negative integer, zero, or a positive integer as the first city
     *         is less than, equal to, or greater than the second city.
     * 
     * Time complexity:
     * - Best case: O(1)
     * - Worst case: O(1) (assuming country and city string lengths are bounded by a constant).
     */
    @Override
    public int compare(City c1, City c2) {
        
        int countryCmp = c1.getCountry().compareTo(c2.getCountry());
        if (countryCmp != 0) {
            return countryCmp;
        }

        if (c1.getPopulation() != c2.getPopulation()) {
            return Integer.compare(c2.getPopulation(), c1.getPopulation());
        }

        return c1.getName().compareToIgnoreCase(c2.getName());
    }
}
