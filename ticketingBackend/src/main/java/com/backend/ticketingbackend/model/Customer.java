package com.backend.ticketingbackend.model;

import com.backend.ticketingbackend.config.Configuration;

public class Customer implements Runnable{

    private TicketPool ticketPool;
    private int customerRetrievalRate; //Ticket removal Frequency from the pool

    public Customer(TicketPool ticketPool, Configuration config) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = config.getCustomerRetrievalRate();
    }

    @Override
    public void run() {
        while (true) {
            Ticket ticket = ticketPool.buyTicket(); // Remove ticket from the pool
            if (ticket == null) {
                System.out.println(Thread.currentThread().getName() + ": No more tickets available.");
                break;
            }

            try{
                Thread.sleep(customerRetrievalRate * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
