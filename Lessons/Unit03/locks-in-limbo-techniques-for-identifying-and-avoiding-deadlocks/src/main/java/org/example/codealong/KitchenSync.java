package org.example.codealong;

public class KitchenSync {
    private static final Object kitchenLock = new Object();
    private static boolean isMainCourseReady = false;

    public static void main(String[] args) {
        Thread chefA = new Thread(() -> {
            synchronized (kitchenLock) {
                try {
                    System.out.println("Chef A: Preparing the main course.");
                    Thread.sleep(2000);  // Simulate the cooking time
                    isMainCourseReady = true;
                    kitchenLock.notify();  // Chef A notifies that the main course is ready
                    System.out.println("Chef A: Main course ready, notifying Chef B.");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        Thread chefB = new Thread(() -> {
            synchronized (kitchenLock) {
                while (!isMainCourseReady) {
                    try {
                        System.out.println("Chef B: Waiting for the main course to be ready.");
                        kitchenLock.wait();  // Chef B waits for the main course to be ready
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                System.out.println("Chef B: Notified that the main course is ready, starting the sauce.");
            }
        });

        chefB.start();  // Start Chef B's thread first
        chefA.start();  // Start Chef A's thread
    }
}

