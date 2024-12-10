package com.backend.ticketingbackend.model;

import com.backend.ticketingbackend.config.Configuration;
import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private Queue<Ticket> ticketQueue;
    private int maximumCapacity;

    public TicketPool(Configuration config) {
        this.maximumCapacity = config.getMaxTicketCapacity();
        this.ticketQueue = new LinkedList<>();
    }

    //Add Ticket method which is used by vendors to addTickets
    public synchronized void addTicket(Ticket ticket) {
        while (ticketQueue.size() >= maximumCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
        this.ticketQueue.add(ticket);
        notifyAll();  //notify the waiting threads
        System.out.println(Thread.currentThread().getName() + " has added a ticket to the pool. Current size is " +
                ticketQueue.size());
    }

    //Buy Ticket method for the customer when buyingTickets
    public synchronized Ticket buyTicket() {
        while (ticketQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        Ticket ticket = ticketQueue.poll(); //remove ticket form the front
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " has bought a ticket from the pool. Current size is " +
                ticketQueue.size() + ". Ticket is " + ticket);

        return ticket;
    }
}
