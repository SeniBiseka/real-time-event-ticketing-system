package com.backend.ticketingbackend.config;

import java.util.Scanner;

public class ConfigurationCLI {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Configuration config = new Configuration();

        try {
            System.out.print("Enter total number of tickets: ");
            config.setTotalTickets(scanner.nextInt());

            System.out.print("Enter ticket release rate: ");
            config.setTicketReleaseRate(scanner.nextInt());

            System.out.print("Enter customer retrieval rate: ");
            config.setCustomerRetrievalRate(scanner.nextInt());

            System.out.print("Enter maximum ticket capacity: ");
            config.setMaxTicketCapacity(scanner.nextInt());

            System.out.print("Configuration Completed Successfully");
            System.out.println(config);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + " Please enter valid values");
        }
    }
}
