import exception.EmptyQueueException;
import exception.FullQueueException;

public interface Queue<E> {
    /**
     * Returns the number of elements in the queue.
     *
     * @return number of elements in the queue.
     */
    public int size();

    /**
     * Returns whether the queue is empty.
     *
     * @return true if the queue is empty, false otherwise.
     */
    public boolean isEmpty();

    /**
     * Inspects the element at the front of the queue.
     *
     * @return element  at the front ofthe queue.
     * @throws EmptyQueueException if the queue is empty.
     */
    public E front() throws EmptyQueueException;

    /**
     * Inserts an element at the rear of the queue.
     *
     * @param element new element to be inserted.
     */
    public void enqueue(E element) throws FullQueueException;

    /**
     * Removes the element at the front of the queue.
     *
     * @return element removed.
     * @throws EmptyQueueException if the queue is empty.
     */
    public E dequeue() throws EmptyQueueException;
}
