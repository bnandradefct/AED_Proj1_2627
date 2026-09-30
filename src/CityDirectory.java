import dataStructures.Iterator;
import dataStructures.SortedLinkedList;

public class CityDirectory {

  
  private final SortedLinkedList<City> cities;

    public CityDirectory() {
        this.cities = new SortedLinkedList<>(new CityComparator());
    }
        
   
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

   public boolean addCity(String name, String country, int population) {

      if (findCity(name, country) != null) {
            return false;
        }
        City newCity = new City(name, country, population);
        cities.add(newCity);
        return true;
   }

   public boolean removeCity(String name, String country) {
    City existing = findCity(name, country);
        if (existing == null) {
            return false;
        }
        return cities.remove(existing) != null;
   }

  
  public boolean isEmpty() {
        return cities.isEmpty();
    }

    
    public Iterator<City> iterator() {
        return cities.iterator();
    } 
        
    


}
