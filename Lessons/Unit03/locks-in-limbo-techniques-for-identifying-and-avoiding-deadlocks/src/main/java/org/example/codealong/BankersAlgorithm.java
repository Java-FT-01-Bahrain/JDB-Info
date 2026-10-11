package org.example.codealong;

public class BankersAlgorithm {
    private final int numberOfResources;
    private final int numberOfProcesses;
    private final int[] available;  // Available amount of each resource
    private int[][] maximum;  // Maximum demand of each process
    private final int[][] allocation;  // Current allocation to each process
    private final int[][] need;  // Remaining needs of each process

    public BankersAlgorithm(int numberOfResources, int numberOfProcesses, int[] available, int[][] maximum, int[][] allocation) {
        this.numberOfResources = numberOfResources;
        this.numberOfProcesses = numberOfProcesses;
        this.available = available;
        this.maximum = maximum;
        this.allocation = allocation;
        this.need = new int[numberOfProcesses][numberOfResources];

        // Initialize the need matrix
        for (int i = 0; i < numberOfProcesses; i++) {
            for (int j = 0; j < numberOfResources; j++) {
                need[i][j] = maximum[i][j] - allocation[i][j];
            }
        }
    }

    // Check if the system is in a safe state
    private boolean checkSafe() {
        boolean[] finish = new boolean[numberOfProcesses];
        int[] work = available.clone();
        boolean found;

        do {
            found = false;
            for (int i = 0; i < numberOfProcesses; i++) {
                if (!finish[i]) {
                    boolean possible = true;
                    for (int j = 0; j < numberOfResources; j++) {
                        if (need[i][j] > work[j]) {
                            possible = false;
                            break;
                        }
                    }
                    if (possible) {
                        for (int k = 0; k < numberOfResources; k++) {
                            work[k] += allocation[i][k];
                        }
                        finish[i] = true;
                        found = true;
                    }
                }
            }
        } while (found);

        // Check if all processes could finish
        for (boolean completed : finish) {
            if (!completed) {
                return false;
            }
        }
        return true;
    }

    // Attempt to request resources for a process
    public boolean requestResources(int processId, int[] request) {
        for (int i = 0; i < numberOfResources; i++) {
            if (request[i] > need[processId][i] || request[i] > available[i]) {
                System.out.println("Request cannot be granted immediately.");
                return false;
            }
        }

        // Try to allocate requested resources temporarily
        for (int i = 0; i < numberOfResources; i++) {
            available[i] -= request[i];
            allocation[processId][i] += request[i];
            need[processId][i] -= request[i];
        }

        // Check if the system is still in a safe state
        if (checkSafe()) {
            System.out.println("Request can be granted safely.");
            return true;
        } else {
            // Rollback the allocation since it's not safe
            for (int i = 0; i < numberOfResources; i++) {
                available[i] += request[i];
                allocation[processId][i] -= request[i];
                need[processId][i] += request[i];
            }
            System.out.println("Request cannot be granted as it leads to an unsafe state.");
            return false;
        }
    }

    public static void main(String[] args) {
        int[] available = {3, 3, 2};  // Example resource instances
        int[][] maximum = {{7, 5, 3}, {3, 2, 2}, {9, 0, 2}, {2, 2, 2}, {4, 3, 3}};
        int[][] allocation = {{0, 1, 0}, {2, 0, 0}, {3, 0, 2}, {2, 1, 1}, {0, 0, 2}};

        BankersAlgorithm banker = new BankersAlgorithm(3, 5, available, maximum, allocation);

        // Example request
        // Example resource request by Process 1
        int[] request1 = {0, 2, 0};  // Process 1 requests 0 units of R0, 2 units of R1, and 0 units of R2
        System.out.println("Process 1 is requesting resources: " + java.util.Arrays.toString(request1));
        boolean requestResult = banker.requestResources(1, request1);
        if (requestResult) {
            System.out.println("The resources were successfully allocated to Process 1.");
        } else {
            System.out.println("The request by Process 1 could not be granted without risking a deadlock.");
        }
    }
}

