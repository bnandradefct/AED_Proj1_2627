import dataStructures.Iterator;
import dataStructures.SortedLinkedList;

public class CityDirectory {

  
  private final SortedLinkedList<City> cities;

    public CityDirectory() {
        this.cities = new SortedLinkedList<>(new CityComparator());
    }
        
   /*
   Complexidade temporal: Melhor caso= O(1), se a cidade procurada for a primeira do iterador
                            Pior caso= O(n), sendo n o número de cidades, se a cidade procurada for a última do iterador ou então se ela não existir
   Complexidade espacial =
    */
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

    /*
   Complexidade temporal: Melhor caso= O(1), se a cidade já existir e se for a primeira do iterator
                            Pior caso= O(n), se a cidade ainda não existir e se a cidade for a última a ser adicionada
   Complexidade espacial =
    */
   public boolean addCity(String name, String country, int population) {

      if (findCity(name, country) != null) {
            return false;
        }
        City newCity = new City(name, country, population);
        cities.add(newCity);
        return true;
   }

    /*
   Complexidade temporal: Melhor caso= O(n), se a cidade não existir
                            Pior caso= O(n), se a cidade existir e é a última do iterador
   Complexidade espacial =
    */
   public boolean removeCity(String name, String country) {
    City existing = findCity(name, country);
        if (existing == null) {
            return false;
        }
        return cities.remove(existing) != null;
   }

    /*
   Complexidade temporal:O(1)
   Complexidade espacial =
    */
  public boolean isEmpty() {
    return !cities.iterator().hasNext();
  }


    /*
     Complexidade temporal: O(1)
     Complexidade espacial =
      */
  public Iterator<City> iterator() {
      return cities.iterator();
  }
        
    


}
