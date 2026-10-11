package org.example.codealong;

import java.util.concurrent.locks.*;

class TicketSystem {
    private final boolean[] seats;
    private final ReentrantLock lock;

    public TicketSystem(int numSeats) {
        seats = new boolean[numSeats];
        lock = new ReentrantLock();
        // Assume all seats are initially available
        for (int i = 0; i < numSeats; i++) {
            seats[i] = true;
        }
    }

    public void printSeatsStatus() {
        System.out.println("Seats Status:");
        for (int i = 0; i < seats.length; i++) {
            System.out.println("Seat " + i + ": " + (seats[i] ? "Available" : "Sold"));
        }
    }

    public void buyTicket(int seatNumber) {
        lock.lock();
        try {
            System.out.println("Current thread [" + Thread.currentThread().getName() + "] holds the lock.");
            if (seatNumber < 0 || seatNumber >= seats.length) {
                System.out.println("Invalid seat number.");
                return;
            }
            if (seats[seatNumber]) {
                seats[seatNumber] = false; // Mark seat as sold
                System.out.println("Seat " + seatNumber + " sold by thread [" + Thread.currentThread().getName() + "].");
                printSeatsStatus();
            } else {
                System.out.println("Seat " + seatNumber + " is already sold.");
                printSeatsStatus();
            }
        } finally {
            lock.unlock();
        }
    }
}

class User extends Thread {
    private final TicketSystem ticketSystem;

    public User(TicketSystem ticketSystem) {
        this.ticketSystem = ticketSystem;
    }

    @Override
    public void run() {
        int seatToBuy = (int) (Math.random() * 10); // Assume 10 seats
        ticketSystem.buyTicket(seatToBuy);
    }
}

public class ReentrantLockTicketSystemDemo {
    public static void main(String[] args) {
        TicketSystem ticketSystem = new TicketSystem(10); // Assume 10 seats
        ticketSystem.printSeatsStatus();
        User user1 = new User(ticketSystem);
        User user2 = new User(ticketSystem);

        user1.start();
        user2.start();
    }
}