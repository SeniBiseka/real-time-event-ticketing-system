package com.backend.ticketingbackend.model;

import java.math.BigDecimal;

public class Vendor implements Runnable{

    private TicketPool ticketPool;
    private int totalTickets; //Tickets vendor will sell
    private int ticketReleaseRate; // Ticket adding frequency

    @Override
    public void run() {
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
