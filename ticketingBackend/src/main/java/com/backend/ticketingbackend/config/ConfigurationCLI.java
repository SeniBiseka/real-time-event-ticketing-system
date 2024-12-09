package com.backend.ticketingbackend.config;

import java.util.Scanner;

public class ConfigurationCLI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Configuration config = new Configuration();

        // input validation using a getValidateInput method
        config.setTotalTickets(getValidInput(scanner, "Enter total number of tickets: "));
        config.setTicketReleaseRate(getValidInput(scanner, "Enter ticket release rate per second: "));
        config.setCustomerRetrievalRate(getValidInput(scanner, "Enter customer retrieval rate per second: "));
        config.setMaxTicketCapacity(getValidInput(scanner, "Enter maximum ticket capacity: "));

        // Display the completed configuration
        System.out.println("Configuration Completed Successfully:");
        System.out.println(config);
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
}


