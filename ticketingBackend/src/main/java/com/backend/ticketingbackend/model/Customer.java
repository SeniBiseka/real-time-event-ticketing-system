package com.backend.ticketingbackend.model;

public class Customer implements Runnable{

    private TicketPool ticketPool;
    private int customerRetrievalRate; //Ticket removal Frequency from the pool
    private int quantity; //Quantity customer willing to purchase

    public Customer(TicketPool ticketPool, int customerRetrievalRate, int quantity) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.quantity = quantity;
    }

    @Override
    public void run() {
        for (int i = 0; i < quantity; i++) {
            Ticket ticket = ticketPool.buyTicket(); //Remove ticket from the ticketPool
            if (ticket == null) {
                System.out.println("No more tickets available for " + Thread.currentThread().getName());
                break;
            }
            System.out.println("Ticket bought by " + Thread.currentThread().getName() + ". Ticket is " +
                    ticket);

            try{
                Thread.sleep(customerRetrievalRate * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
