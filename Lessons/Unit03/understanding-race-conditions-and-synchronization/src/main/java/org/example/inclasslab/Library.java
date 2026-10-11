package org.example.inclasslab;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Library {
    private static final int MAX_AVAILABLE_COPIES = 5;
    private Semaphore semaphore = new Semaphore(MAX_AVAILABLE_COPIES, true);
    private Lock lock = new ReentrantLock();
    private String[] availableBooks = {"Book A", "Book B", "Book C", "Book D", "Book E"};

    public void borrowBook(String borrowerName, String bookTitle) {
        // TODO: Ensure the semaphore is declared and available for locking mechanisms

        try {
            // TODO: Acquire the semaphore to ensure exclusive access to the book borrowing process

            // Initialize bookIndex to -1 to signify that the book has not been found yet
            int bookIndex = -1;

            // TODO: Loop through the availableBooks array to find the book by title
            for (int i = 0; i < availableBooks.length; i++) {
                // This loop iterates through each book in the availableBooks array.
                // It checks if the current book's title matches the bookTitle parameter passed to the method.
                // If a match is found, bookIndex is set to the current index of the loop, indicating the position of the found book in the array.
                // The loop breaks immediately after finding the first match to avoid unnecessary iterations,
                // as we assume each book title is unique or we are only interested in borrowing the first match.
            }

            // TODO: Check if the book was found (bookIndex != -1)
            if (bookIndex != -1) {
                // TODO: Print the message that the borrower has borrowed the book

                // Simulate the process of borrowing the book (e.g., delay for 1 second)
                Thread.sleep(1000);

                // TODO: Print the message that the borrower has returned the book
            } else {
                // TODO: Print the message that the book was not found
            }
        } catch (InterruptedException e) {
            // TODO: Handle the InterruptedException
        } finally {
            // TODO: Release the semaphore to allow other processes to proceed
        }
    }

    public void borrowBookWithLock(String borrowerName, String bookTitle) {
        // TODO: Lock the process to ensure exclusive access during the book borrowing

        try {
            // Initialize bookIndex to -1 to indicate that the book hasn't been found yet
            int bookIndex = -1;

            // TODO: Loop through the availableBooks array to find the book by its title
            // In this loop, iterate over each book in the availableBooks array.
            // Check if the title of the current book matches the 'bookTitle' parameter.
            // If a match is found, 'bookIndex' is updated to the index of the found book, indicating its position.
            // This loop exits after finding the first match to optimize performance,
            // assuming each title is unique or only the first match is needed.
            for (int i = 0; i < availableBooks.length; i++) {
                if (availableBooks[i].equals(bookTitle)) {
                    bookIndex = i;
                    break;
                }
            }

            // TODO: Check if the book was found based on bookIndex
            if (bookIndex != -1) {
                // TODO: If a match is found, indicate that the borrower has borrowed the book.
                System.out.println(borrowerName + " has borrowed " + bookTitle);
                // Simulate the book borrowing by pausing the execution for a set duration (e.g., 1 second).
                Thread.sleep(1000);
                // TODO: After the pause, indicate that the book has been returned.
            } else {
                // TODO: If no match is found, inform that the book is not available.
            }
        } catch (InterruptedException e) {
            // TODO: Handle any interruptions during the process
        } finally {
            // TODO: Unlock the process to allow other operations to proceed
        }
    }


    public static void main(String[] args) {
        Library library = new Library();
        // Create multiple borrower threads
        for (int i = 0; i < 10; i++) {
            int borrowerNumber = i + 1;
            new Thread(() -> library.borrowBook("Borrower " + borrowerNumber, "Book A")).start();
        }

        // Create multiple borrower threads using lock
        for (int i = 0; i < 10; i++) {
            int borrowerNumber = i + 1;
            new Thread(() -> library.borrowBookWithLock("Borrower " + borrowerNumber, "Book B")).start();
        }
    }
}

