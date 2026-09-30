

public class City {

  private String name;
  private String country;
  private int population;

  public City(String name, String country, int population) {
    this.name = name;
    this.country = country;
    this.population = population;
  }

  public String getName() {
    return name;
  }

  public String getCountry() {
    return country;
  }

  public int getPopulation() {
    return population;
  }

}
