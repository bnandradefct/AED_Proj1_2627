import dataStructures.Iterator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import system.*;

/**
 * Main class for the Persistent City Directory application.
 * Handles user interaction, command processing, and directory persistence.
 * 
 * Space complexity: O(n), where n is the number of cities stored in the directory.
 */
public class Main {
    private static final String DATA_FILE = "directory.dat";

    /**
     * Entry point of the application. Restores the directory if available and
     * processes user commands in a loop until the quit command is issued.
     * 
     * @param args Command-line arguments (not used).
     * 
     * Time complexity:
     * - Best case: O(1) — when the directory is empty and the first command is quit.
     * - Worst case: O(k * n) — where k is the number of executed commands and n
     *   is the number of cities in the directory.
     */
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

    /**
     * Attempts to load and deserialize the city directory from the serialization file.
     * If the file does not exist, creates and returns a new empty city directory.
     * 
     * @return The restored CityDirectory instance, or a new empty one if not found.
     * 
     * Time complexity:
     * - Best case: O(1) — when the serialization file does not exist or is empty.
     * - Worst case: O(n) — where n is the number of cities deserialized from the file.
     */
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

    /**
     * Serializes and saves the current state of the city directory to a file.
     * 
     * @param directory The CityDirectory instance to be saved.
     * 
     * Time complexity:
     * - Best case: O(1) — when the directory is empty.
     * - Worst case: O(n) — where n is the number of cities to serialize.
     */
    private static void saveDirectory(CityDirectory directory) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            out.writeObject(directory);
        } catch (IOException e) {
            // Mostra o erro se falhar ao serializar
            e.printStackTrace();
        }
    }

    /**
     * Displays the list of available commands and their descriptions.
     * 
     * Time complexity:
     * - Best case: O(1)
     * - Worst case: O(1)
     */
    private static void executeHelp() {
        System.out.println("addCity: Adds a new city to the directory.");
        System.out.println("removeCity: Removes an existing city from the directory.");
        System.out.println("population: Returns the number of inhabitants of a city.");
        System.out.println("listCities: Lists all cities currently stored in the directory.");
        System.out.println("help: Displays the list of available commands.");
        System.out.println("quit: Terminates the application.");
    }

    /**
     * Reads the city details from the input and adds a new city to the directory.
     * 
     * @param in        The Scanner used to read user input.
     * @param directory The CityDirectory instance.
     * 
     * Time complexity:
     * - Best case: O(1) — when the city already exists at the head of the list
     *   or the directory is empty.
     * - Worst case: O(n) — where n is the number of cities in the directory.
     */
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

    /**
     * Reads the city name and country from the input and removes the corresponding
     * city from the directory.
     * 
     * @param in        The Scanner used to read user input.
     * @param directory The CityDirectory instance.
     * 
     * Time complexity:
     * - Best case: O(1) — when the city to remove is at the head of the list.
     * - Worst case: O(n) — where n is the number of cities in the directory.
     */
    private static void executeRemoveCity(Scanner in, CityDirectory directory) {
        String cityName = in.nextLine().trim();
        String countryName = in.nextLine().trim();

        if (directory.removeCity(cityName, countryName)) {
            System.out.println("City successfully removed.");
        } else {
            System.out.println("City not found.");
        }
    }

    /**
     * Reads the city name and country from the input and displays its population.
     * 
     * @param in        The Scanner used to read user input.
     * @param directory The CityDirectory instance.
     * 
     * Time complexity:
     * - Best case: O(1) — when the target city is at the head of the list.
     * - Worst case: O(n) — where n is the number of cities in the directory.
     */
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

    /**
     * Lists all cities currently stored in the directory in sorted order.
     * 
     * @param directory The CityDirectory instance.
     * 
     * Time complexity:
     * - Best case: O(1) — when the directory is empty.
     * - Worst case: O(n) — where n is the number of cities in the directory.
     */
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

    /**
     * Displays the termination messages upon exiting the application.
     * 
     * Time complexity:
     * - Best case: O(1)
     * - Worst case: O(1)
     */
    private static void executeQuit() {
        System.out.println("City directory saved.");
        System.out.println("Goodbye.");
    }
}
