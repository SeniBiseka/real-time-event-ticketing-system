package com.backend.ticketingbackend.model;

import com.backend.ticketingbackend.config.Configuration;
import java.math.BigDecimal;

public class Vendor implements Runnable{

    private TicketPool ticketPool;
    private int totalTickets; //Tickets vendor will sell
    private int ticketReleaseRate; // Ticket adding frequency

    public Vendor(TicketPool ticketPool, Configuration config) {
        this.ticketPool = ticketPool;
        this.totalTickets = config.getTotalTickets();
        this.ticketReleaseRate = config.getTicketReleaseRate();
    }

    @Override
    public void run() {
        System.out.println("Vendor started");
        for (int i = 1; i <= totalTickets; i++) {  //i is used as ID
            Ticket ticket = new Ticket(i, "Event Simple", new BigDecimal(1000));
            ticketPool.addTicket(ticket);

            try {
                Thread.sleep(ticketReleaseRate * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
