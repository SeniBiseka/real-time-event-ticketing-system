//1st try
//package com.backend.ticketingbackend.controller;
//
//import com.backend.ticketingbackend.config.Configuration;
//import com.backend.ticketingbackend.model.Ticket;
//import com.backend.ticketingbackend.model.TicketPool;
//import org.springframework.web.bind.annotation.*;
//
//import java.math.BigDecimal;
//
//@RestController
//@RequestMapping("/api/tickets")
//public class TicketController {
//    private final Configuration configuration;
//    private final TicketPool ticketPool;
//
//    // Constructor to inject Configuration and TicketPool
//    public TicketController(Configuration configuration) {
//        this.configuration = configuration;
//        this.ticketPool = new TicketPool(configuration); // Initialize the TicketPool
//    }
//
//    // GET endpoint to fetch configuration
//    @GetMapping("/config")
//    public Configuration getConfig() {
//        return configuration;
//    }
//
//    // GET endpoint to fetch available tickets
//    @GetMapping("/available")
//    public int getAvailableTickets() {
//        return ticketPool.getAvailableTickets(); // Method to be added in TicketPool
//    }
//
//    // POST endpoint to add tickets (used by vendors)
//    @PostMapping("/add")
//    public String addTickets(@RequestParam int quantity) {
//        for (int i = 1; i <= quantity; i++) {
//            Ticket ticket = new Ticket(i, "Event Name", BigDecimal.valueOf(100));
//            ticketPool.addTicket(ticket);
//        }
//        return "Successfully added " + quantity + " tickets.";
//    }
//
//    // POST endpoint to buy tickets (used by customers)
//    @PostMapping("/buy")
//    public String buyTickets(@RequestParam int quantity) {
//        for (int i = 0; i < quantity; i++) {
//            Ticket ticket = ticketPool.buyTicket();
//            if (ticket == null) {
//                return "No tickets available.";
//            }
//        }
//        return "Successfully bought " + quantity + " tickets.";
//    }
//
//    @GetMapping("/test")
//    public String testEndpoint() {
//        return "Spring Boot is working!";
//    }
//}
//

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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private Configuration config;

    @Autowired
    private TicketPool ticketPool;

    private final ExecutorService executor = Executors.newCachedThreadPool();

//    // Configuration endpoint
//    @PostMapping("/configure")
//    public String configureSystem(@RequestParam int totalTickets,
//                                  @RequestParam int ticketReleaseRate,
//                                  @RequestParam int customerRetrievalRate,
//                                  @RequestParam int maxTicketCapacity) {
//        config.setTotalTickets(totalTickets);
//        config.setTicketReleaseRate(ticketReleaseRate);
//        config.setCustomerRetrievalRate(customerRetrievalRate);
//        config.setMaxTicketCapacity(maxTicketCapacity);
//
//        return "System configured with " + totalTickets + " tickets, release rate of " + ticketReleaseRate + " and max capacity of " + maxTicketCapacity + ".";
//    }

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

        // Start Vendor
        Vendor vendor = new Vendor(ticketPool, config);
        executor.submit(vendor);

        // Start Customer
        Customer customer = new Customer(ticketPool, config, customerQuantity);
        executor.submit(customer);

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
