package com.backend.ticketingbackend.controller;

import com.backend.ticketingbackend.config.Configuration;
import com.backend.ticketingbackend.model.TicketPool;
import com.backend.ticketingbackend.model.Vendor;
import com.backend.ticketingbackend.model.Customer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.io.IOException;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private Configuration config;

    @Autowired
    private TicketPool ticketPool;

    @PostMapping("/config")
    public String updateConfig(@RequestBody Configuration newConfig) {
        this.config = newConfig;
        return "Configuration updated successfully!";
    }

    // Start the ticketing system
    @PostMapping("/start")
    public String startSystem(@RequestParam int customerQuantity) {
        // Load the configuration if it's not already set
        if (config.getTotalTickets() == 0) {
            Configuration loadedConfig = loadConfigurationFromFile();
            if (loadedConfig != null) {
                this.config = loadedConfig;
            }
        }

//        // Start Vendor in a new thread
//        Vendor vendor = new Vendor(ticketPool, config);
//        Thread vendorThread = new Thread(vendor);
//        vendorThread.start();

        // Start Customer threads
        for (int i = 0; i < 3; i++) { //3 Vendors start adding ticket to the ticketpool
            Vendor vendor = new Vendor(ticketPool, config);
            Thread vendorThread = new Thread(vendor, "Vendor-" + (i+1));//Used 3rd Constructor of the Thread class
            vendorThread.start();
        }

        // Start Customer threads
        for (int i = 0; i < customerQuantity; i++) {
            Customer customer = new Customer(ticketPool, config); // No need for quantity
            Thread customerThread = new Thread(customer, "Customer-" + (i + 1));
            customerThread.start();
        }

        return "Ticketing system started with " + config.getTotalTickets() + " tickets.";
    }

    @GetMapping("/available")
    public int getAvailableTickets() {
        return ticketPool.getAvailableTickets();
    }

    // Get the current configuration
    @GetMapping("/config")
    public Configuration getConfig() {
        return config;
    }

    private Configuration loadConfigurationFromFile() {
        try (FileReader reader = new FileReader("config.json")) {
            Gson gson = new Gson();
            return gson.fromJson(reader, Configuration.class);
        } catch (IOException e) {
            System.out.println("Error loading configuration: " + e.getMessage());
            return null;
        }
    }
}
