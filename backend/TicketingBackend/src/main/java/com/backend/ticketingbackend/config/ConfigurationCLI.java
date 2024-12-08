package com.backend.ticketingbackend.config;

import java.util.Scanner;

public class ConfigurationCLI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Configuration config = new Configuration();

        boolean validInput = false;
        while (!validInput) {
            try {
            System.out.print("Enter total number of tickets: ");
            config.setTotalTickets(scanner.nextInt());
            validInput = true;
            } catch (Exception e) {
            System.out.println("Error. Please enter a valid number.");
            scanner.nextLine(); // Clear the invalid input
            }
        }

        validInput = false;
        while (!validInput) {
            try {
            System.out.print("Enter ticket release rate per second: ");
            config.setTicketReleaseRate(scanner.nextInt());
            validInput = true;
            } catch (Exception e) {
            System.out.println("Error. Please enter the correct rate.");
            scanner.nextLine(); // Clear the invalid input
            }
        }

        validInput = false;
        while (!validInput) {
            try {
            System.out.print("Enter customer retrieval rate per second: ");
            config.setCustomerRetrievalRate(scanner.nextInt());
            validInput = true;
            } catch (Exception e) {
            System.out.println("Error. Please enter the correct rate.");
            scanner.nextLine(); // Clear the invalid input
            }
        }

        validInput = false;
        while (!validInput) {
            try {
            System.out.print("Enter maximum ticket capacity: ");
            config.setMaxTicketCapacity(scanner.nextInt());
            validInput = true;
            } catch (Exception e) {
            System.out.println("Error. Please enter a valid number.");
            scanner.nextLine(); // Clear the invalid input
            }
        }

        System.out.println("Configuration Completed Successfully");
        System.out.println(config);
    }
}
