import java.io.Serializable;
import dataStructures.Iterator;

public interface CityDirectory extends Serializable {

    City findCity(String cityName, String countryName);

    boolean addCity(String name, String country, int population);

    boolean removeCity(String name, String country);

    boolean isEmpty();

    Iterator<City> iterator();
}
