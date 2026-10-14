package system;

import java.io.Serializable;
import dataStructures.Comparator;

public class CityComparator implements Comparator<City>, Serializable {
    private static final long serialVersionUID = 1L;

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
