package org.example.codealong;

class Chef implements Runnable {
    private final String name;
    private static final String[] dishes = {"salad", "soup", "steak", "pasta", "dessert"};
    private volatile boolean interrupted = false; // Use volatile for thread safety

    public Chef(String name) {
        this.name = name;
    }

    public void interruptChef() {
        interrupted = true;
    }

    @Override
    public void run() {
        for (String dish : dishes) {
            if (interrupted) {
                System.out.println(name + " received an urgent call from the supplier and must step away immediately.");
                return; // Exit the loop and terminate the thread's task
            }
            System.out.println(name + " is preparing " + dish);
            try {
                Thread.sleep(1000);  // Simulate time taken to prepare a dish
            } catch (InterruptedException e) {
                System.out.println(name + " was interrupted while sleeping and will now halt the task.");
                return;  // This line cleanly exits the thread if it's interrupted during sleep
            }
        }
    }
}

public class Restaurant {
    public static void main(String[] args) throws InterruptedException {
        Chef chef1 = new Chef("Alice");
        Thread chef1Thread = new Thread(chef1, "Alice");
        Chef chef2 = new Chef("Bob");
        Thread chef2Thread = new Thread(chef2, "Bob");
        Chef chef3 = new Chef("Charlie");
        Thread chef3Thread = new Thread(chef3, "Charlie");
        Chef chef4 = new Chef("Suresh");
        Thread chef4Thread = new Thread(chef4, "Suresh");

        chef1Thread.start();  // Start Alice's thread
        chef2Thread.start();  // Start Bob's thread
        chef3Thread.start();  // Start Charlie's thread
        chef4Thread.start();  // Start Suresh's thread

        Thread.sleep(2500);  // Wait a bit to ensure Bob is in the middle of his tasks

        // Interrupt Bob's thread using the custom method
        chef2.interruptChef();

        chef1Thread.join();  // Wait for Alice to finish
    }
}
