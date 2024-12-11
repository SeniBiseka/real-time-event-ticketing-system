//package com.backend.ticketingbackend;
//
//import com.backend.ticketingbackend.config.Configuration;
//import com.backend.ticketingbackend.config.ConfigurationCLI;
//import com.backend.ticketingbackend.model.Customer;
//import com.backend.ticketingbackend.model.TicketPool;
//import com.backend.ticketingbackend.model.Vendor;
//
//public class Main {
//    public static void main(String[] args) {
//        // Step 1: Configure the system using the ConfigurationCLI
//        Configuration config = ConfigurationCLI.ConfigureSystem();
//
//        // Step 2: Initialize the TicketPool using the configuration
//        TicketPool ticketPool = new TicketPool(config);
//
//        // Step 3: Create Vendor and Customer threads
//        Vendor vendor = new Vendor(ticketPool, config);
//        Customer customer1 = new Customer(ticketPool, config, 1); // Customer wants 5 tickets
//        Customer customer2 = new Customer(ticketPool, config, 1); // Another customer wants 7 tickets
//
//        // Step 4: Start the threads
//        Thread vendorThread = new Thread(vendor, "Vendor-1");
//        Thread customerThread1 = new Thread(customer1, "Customer-1");
//        Thread customerThread2 = new Thread(customer2, "Customer-2");
//
//        vendorThread.start();
//        customerThread1.start();
//        customerThread2.start();
//
//        // Step 5: Wait for all threads to complete
//        try {
//            vendorThread.join();
//            customerThread1.join();
//            customerThread2.join();
//        } catch (InterruptedException e) {
//            System.out.println("Thread interrupted: " + e.getMessage());
//        }
//
//        // Step 6: Simulation complete
//        System.out.println("Simulation completed successfully!");
//    }
//}
