public class ArrayQueueDemo {
    public static void main(String[] args) {
        Object ob = new Object();
        ArrayQueue<Integer> queue = new ArrayQueue<>(3);
        System.out.println("Operation\tOutput\tfront <- Q <- rear");
        queue.enqueue(5);
        queue.status("enqueue(5)", null);
        queue.enqueue(3);
        queue.status("enqueue(3)", null);
        ob = queue.dequeue();
        System.out.println(ob);
    }
}