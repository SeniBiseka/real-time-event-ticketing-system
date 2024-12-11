package com.backend.ticketingbackend.model;

import com.backend.ticketingbackend.config.Configuration;
import com.backend.ticketingbackend.logging.Logger;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;

@Component
public class TicketPool {
    private Queue<Ticket> ticketQueue;
    private int maximumCapacity;

    public TicketPool(Configuration config) {
        this.maximumCapacity = config.getMaxTicketCapacity();
        this.ticketQueue = new LinkedList<>();
    }

    public int getAvailableTickets() { //will use this in the controller class
        return ticketQueue.size(); // Returns the current size of the ticket pool
    }

    //Add Ticket method which is used by vendors to addTickets
    public synchronized void addTicket(Ticket ticket) {
        while (ticketQueue.size() >= maximumCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Logger.log("Error: Thread interrupted while waiting to add ticket - " + e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        }
        this.ticketQueue.add(ticket);
        notifyAll();  //notify the waiting threads
        Logger.log(Thread.currentThread().getName() + " has added a ticket to the pool. Current size is " +
                ticketQueue.size());
    }

    //Buy Ticket method for the customer when buyingTickets
    public synchronized Ticket buyTicket() {
        while (ticketQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Logger.log("Error: Thread interrupted while waiting to buy ticket - " + e.getMessage());
                throw new RuntimeException(e.getMessage());
            }
        }
        Ticket ticket = ticketQueue.poll(); //remove ticket form the front
        notifyAll();
        Logger.log(Thread.currentThread().getName() + " has bought a ticket from the pool. Current size is " +
                ticketQueue.size() + ". Ticket is " + ticket);

        return ticket;
    }
}
