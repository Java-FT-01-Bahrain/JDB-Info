import exception.EmptyQueueException;
import exception.FullQueueException;

public class ArrayQueue<E> implements Queue<E> {
    public static final int CAPACITY = 1000;
    protected int capacity;
    protected E[] queue;
    protected int frontIndex;
    protected int rearIndex;

    public ArrayQueue() {
        this(CAPACITY);
    }

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        queue = (E[]) new Object[capacity];
        frontIndex = rearIndex = 0;
    }

    @Override
    public int size() {
        return (capacity - frontIndex + rearIndex) % capacity;
    }

    @Override
    public boolean isEmpty() {
        return (frontIndex == rearIndex);
    }

    @Override
    public E front() throws EmptyQueueException {
        if (isEmpty()) throw new EmptyQueueException("The queue is empty.");
        return queue[frontIndex];
    }

    @Override
    public void enqueue(E element) throws FullQueueException {
        if (size() == capacity - 1) throw new FullQueueException("The queue is full.");
        queue[rearIndex] = element;
        rearIndex = (rearIndex + 1) % capacity;
    }

    @Override
    public E dequeue() throws EmptyQueueException {
        if (isEmpty()) throw new EmptyQueueException("The queue is empty.");
        E temp = queue[frontIndex];
        queue[frontIndex] = null;
        frontIndex = (frontIndex + 1) % capacity;
        return temp;
    }

    @Override
    public String toString() {
        int index = frontIndex;
        StringBuilder str = new StringBuilder("[");
        if (size() > 0) str.append(queue[index].toString());
        if (size() > 1) {
            index++;
            while (index != rearIndex) {
                str.append(", ").append(queue[index].toString());
                index = (index + 1) % capacity;
            }
        }
        return str + "]";
    }

    public void status(String op, Object ob) {
        if (ob == null) {
            System.out.println(op + "\t" + ob + "\t" + toString());
        } else {
            System.out.println(op + "\t" + ob + "\t\t" + toString());
        }
    }
}