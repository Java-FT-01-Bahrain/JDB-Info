package org.example.codealong;

public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread taskThread = new Thread(() -> {
            try {
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    if (Thread.interrupted()) {
                        System.out.println("Thread was interrupted, stopping.");
                        break;
                    }
                    // Simulate task processing
                    Math.sin(i);
                }
            } catch (Throwable e) {
                System.out.println("Caught in Thread: " + e);
            }
        });

        taskThread.start();
        // Allow the thread to run for a bit.
        Thread.sleep(1000);
        // Interrupt the thread
        taskThread.interrupt();
    }
}
