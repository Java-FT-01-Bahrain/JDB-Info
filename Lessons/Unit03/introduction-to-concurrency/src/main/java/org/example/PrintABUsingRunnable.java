package org.example;

public class PrintABUsingRunnable implements Runnable {
    private String letter;

    public PrintABUsingRunnable(String letter) {
        this.letter = letter;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.print(letter);
            try {
                Thread.sleep(500); // Sleep for 500 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new PrintABUsingRunnable("A"));
        Thread thread2 = new Thread(new PrintABUsingRunnable("B"));

        thread1.start();
        thread2.start();
    }
}
