package com.backend.ticketingbackend.controller;

import com.backend.ticketingbackend.config.Configuration;
import com.backend.ticketingbackend.model.Ticket;
import com.backend.ticketingbackend.model.TicketPool;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final Configuration configuration;
    private final TicketPool ticketPool;

    // Constructor to inject Configuration and TicketPool
    public TicketController(Configuration configuration) {
        this.configuration = configuration;
        this.ticketPool = new TicketPool(configuration); // Initialize the TicketPool
    }

    // GET endpoint to fetch configuration
    @GetMapping("/config")
    public Configuration getConfig() {
        return configuration;
    }

    // GET endpoint to fetch available tickets
    @GetMapping("/available")
    public int getAvailableTickets() {
        return ticketPool.getAvailableTickets(); // Method to be added in TicketPool
    }

    // POST endpoint to add tickets (used by vendors)
    @PostMapping("/add")
    public String addTickets(@RequestParam int quantity) {
        for (int i = 1; i <= quantity; i++) {
            Ticket ticket = new Ticket(i, "Event Name", BigDecimal.valueOf(100));
            ticketPool.addTicket(ticket);
        }
        return "Successfully added " + quantity + " tickets.";
    }

    // POST endpoint to buy tickets (used by customers)
    @PostMapping("/buy")
    public String buyTickets(@RequestParam int quantity) {
        for (int i = 0; i < quantity; i++) {
            Ticket ticket = ticketPool.buyTicket();
            if (ticket == null) {
                return "No tickets available.";
            }
        }
        return "Successfully bought " + quantity + " tickets.";
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "Spring Boot is working!";
    }
}

