package com.backend.ticketingbackend.model;

public class ModelMain {
    public static void main(String[] args) {
        TicketPool ticketPool = new TicketPool(10);

        Vendor[] vendors = new Vendor[10];
        for(int i = 0; i < vendors.length; i++) {
            vendors [i] = new Vendor(ticketPool,5,5);
            Thread vendorThread = new Thread(vendors [i], "Vendor "+ i); //used 3rd constructor of thread class
            vendorThread.start();
        }

        Customer[] customers = new Customer[10];
        for(int i = 0; i < customers.length; i++) {
            customers [i] = new Customer(ticketPool,5,5);
            Thread customerThread = new Thread(customers [i], "Customer "+ i);
            customerThread.start();
        }
    }
}
