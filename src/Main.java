import java.util.Scanner;
import dataStructures.Iterator;

public class Main {
  public static void main(String[] args) {
    
      CityDirectory directory = new CityDirectory();

        Scanner in = new Scanner(System.in);
        boolean running = true;

        while (running && in.hasNextLine()) {
            String command = in.nextLine().trim();
            if (command.isEmpty()) {
                continue;
            }

            switch (command) {
                case "help":
                    executeHelp();
                    break;
                case "addCity":
                    executeAddCity(in, directory);
                    break;
                case "removeCity":
                    executeRemoveCity(in, directory);
                    break;
                case "population":
                    executePopulation(in, directory);
                    break;
                case "listCities":
                    executeListCities(directory);
                    break;
                case "quit":
                    executeQuit();
                    running = false;
                    break;
                default:
                    break;
            }
        }
        in.close();
    }

      private static void executeHelp() {
        System.out.println("addCity: Adds a new city to the directory.");
        System.out.println("removeCity: Removes an existing city from the directory.");
        System.out.println("population: Returns the number of inhabitants of a city.");
        System.out.println("listCities: Lists all cities currently stored in the directory.");
        System.out.println("help: Displays the list of available commands.");
        System.out.println("quit: Terminates the application.");
    }


    private static void executeAddCity(Scanner in, CityDirectory directory) {
        String cityName = in.nextLine().trim();
        String countryName = in.nextLine().trim();
        int population = Integer.parseInt(in.nextLine().trim());

        if (directory.addCity(cityName, countryName, population)) {
            System.out.println("City successfully added.");
        } else {
            System.out.println("City already exists.");
        }
    }

    private static void executeRemoveCity(Scanner in, CityDirectory directory) {
        String cityName = in.nextLine().trim();
        String countryName = in.nextLine().trim();

        if (directory.removeCity(cityName, countryName)) {
            System.out.println("City successfully removed.");
        } else {
            System.out.println("City not found.");
        }
    }

    private static void executePopulation(Scanner in, CityDirectory directory) {
        String cityName = in.nextLine().trim();
        String countryName = in.nextLine().trim();

        City city = directory.findCity(cityName, countryName);
        if (city != null) {
            System.out.println("Population: " + city.getPopulation());
        } else {
            System.out.println("City not found.");
        }
    }

    private static void executeListCities(CityDirectory directory) {
        if (directory.isEmpty()) {
            System.out.println("No cities available.");
            return;
        }

        Iterator<City> it = directory.iterator();
        while (it.hasNext()) {
            City c = it.next();
            System.out.println(c.getCountry() + " - " + c.getPopulation() + " - " + c.getName());
        }
    }


    private static void executeQuit() {
        System.out.println("City directory saved.");
        System.out.println("Goodbye.");
    }


}