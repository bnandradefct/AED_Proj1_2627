import dataStructures.Iterator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import system.*;

public class Main {
    private static final String DATA_FILE = "directory.dat";

    public static void main(String[] args) {
        CityDirectory directory = loadDirectory();

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
                    saveDirectory(directory);
                    executeQuit();
                    running = false;
                    break;
                default:
                    break;
            }
        }
        in.close();
    }

        private static CityDirectory loadDirectory() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                System.out.println("City directory restored successfully.");
                return (CityDirectory) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                // Se falhar a leitura, mostra o motivo no terminal durante os testes:
                e.printStackTrace();
            }
        }

        System.out.println("City directory not found.");
        System.out.println("Creating an empty city directory.");
        return new CityDirectoryClass();
    }

    private static void saveDirectory(CityDirectory directory) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            out.writeObject(directory);
        } catch (IOException e) {
            // Mostra o erro se falhar ao serializar
            e.printStackTrace();
        }
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
