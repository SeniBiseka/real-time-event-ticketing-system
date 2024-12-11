package com.backend.ticketingbackend.config;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ConfigurationCLI {

    public static void main(String[] args) {
        Configuration config = ConfigureSystem(); // Call the method to configure the system
        // use the 'config' object to pass the configuration to Spring Boot if needed
    }

    public static Configuration ConfigureSystem() {
        Scanner scanner = new Scanner(System.in);
        Configuration config = null;

        // Ask user if they want to load an existing configuration or create a new one
        System.out.println("Welcome to the Ticketing System Configuration.");
        System.out.println("Choose an option:");
        System.out.println("1. Load previous configuration");
        System.out.println("2. Create new configuration");

        int choice = getValidInput(scanner, "Enter your choice (1 or 2): ");

        if (choice == 1) {
            // Load existing configuration
            config = loadConfiguration();
            if (config != null) {
                System.out.println("Previous Configuration loaded.");
            } else {
                System.out.println("No configuration file found or error reading configuration.");
                choice = 2; // Proceed to create new configuration
            }
        }

        if (choice == 2) {
            // Create new configuration
            System.out.println("Creating a new configuration...");
            config = new Configuration();

            // Input and validation for configuration parameters
            config.setTotalTickets(getValidInput(scanner, "Enter total number of tickets: "));
            config.setTicketReleaseRate(getValidInput(scanner, "Enter ticket release rate per second: "));
            config.setCustomerRetrievalRate(getValidInput(scanner, "Enter customer retrieval rate per second: "));
            config.setMaxTicketCapacity(getValidInput(scanner, "Enter maximum ticket capacity: "));

            // Save the new configuration
            saveConfiguration(config);
            System.out.println("New configuration saved.");
        }

        // Display the final configuration
        System.out.println("Configuration Completed Successfully:");
        System.out.println(config);
        return config;
    }

    /**
     * Reusable method to get valid integer input from the user
     */
    private static int getValidInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int input = scanner.nextInt(); // Read integer input
                if (input > 0) {
                    return input;
                } else {
                    System.out.println("Error. Please enter a number greater than 0.");
                }
            } catch (Exception e) {
                System.out.println("Error. Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    /**
     * Saves the configuration to a JSON file.
     */
    private static void saveConfiguration(Configuration config) {
        try (FileWriter writer = new FileWriter("config.json")) {
            Gson gson = new Gson();
            gson.toJson(config, writer);
        } catch (IOException e) {
            System.out.println("Error saving configuration: " + e.getMessage());
        }
    }

    /**
     * Loads the configuration from a JSON file.
     */
    private static Configuration loadConfiguration() {
        try (FileReader reader = new FileReader("config.json")) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Configuration.class);
        } catch (IOException e) {
            System.out.println("No configuration file found or error reading configuration.");
            return null;
        }
    }
}


