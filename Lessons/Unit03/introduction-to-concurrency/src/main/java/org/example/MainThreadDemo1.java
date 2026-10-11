package org.example;

public class MainThreadDemo1 {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Thread currentThread = Thread.currentThread();

        // Print out thread information
        System.out.println("Thread Name: " + currentThread.getName());
        System.out.println("Thread ID: " + currentThread.getId());
        System.out.println("Thread Priority: " + currentThread.getPriority());
        System.out.println("Thread State: " + currentThread.getState());
        System.out.println("Thread Group: " + currentThread.getThreadGroup().getName());
    }
}
