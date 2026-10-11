package org.example;

public class PrintABUsingThread extends Thread {
    private String letter;

    public PrintABUsingThread(String letter) {
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
        Thread thread1 = new PrintABUsingThread("A");
        Thread thread2 = new PrintABUsingThread("B");

        thread1.start();
        thread2.start();
    }
}
