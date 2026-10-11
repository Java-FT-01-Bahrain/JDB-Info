package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Thread currentThread = Thread.currentThread();

        // Print out thread information
        System.out.println("Thread Name: " + currentThread.getName());
        System.out.println("Thread ID: " + currentThread.getId());
        System.out.println("Thread Priority: " + currentThread.getPriority());
        System.out.println("Thread State: " + currentThread.getState());
        System.out.println("Thread Group: " + currentThread.getThreadGroup().getName());

        // Simulating TIMED_WAITING state
        try {
            Thread.sleep(1000); // Putting the current thread into TIMED_WAITING state for 1 second
            System.out.println("WAITING state count: " + Thread.getAllStackTraces().keySet().stream()
                    .filter(thread -> thread.getState() == Thread.State.WAITING)
                    .count());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the state of the thread after sleep
        System.out.println("TIMED_WAITING state count: " + Thread.getAllStackTraces().keySet().stream()
                .filter(thread -> thread.getState() == Thread.State.TIMED_WAITING)
                .count());

        System.out.println("TERMINATED state count: " +
                Thread.getAllStackTraces().keySet().stream()
                        .filter(thread -> thread.getState() == Thread.State.TERMINATED)
                        .count());
    }
}
