package org.example.codealong;

public class LockOrder {
    private final Object Lock1 = new Object();
    private final Object Lock2 = new Object();

    /**
     * Method that corrects lock acquisition order based on system hash codes
     * to prevent deadlocks.
     */
    void correctMethod() {
        int firstId = System.identityHashCode(Lock1);
        int secondId = System.identityHashCode(Lock2);

        // Determine the lock order based on the system identity hash codes
        Object firstLock = firstId < secondId ? Lock1 : Lock2;
        Object secondLock = firstId < secondId ? Lock2 : Lock1;

        // Acquire locks in the determined order
        synchronized (firstLock) {
            synchronized (secondLock) {
                // perform actions within the synchronized block
                System.out.println("Both locks have been acquired in order and actions can be performed safely.");
            }
        }
    }

    public static void main(String[] args) {
        LockOrder lockOrder = new LockOrder();
        lockOrder.correctMethod();
    }
}