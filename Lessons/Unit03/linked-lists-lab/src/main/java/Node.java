public class Node {
    /**
     * The value of the node
     */
    public int value;

    /**
     * Point to the next node
     */
    public Node next;

    Node() {
    }

    /**
     * Constructor
     *
     * @param value Value to be put in the node
     */
    public Node(int value) {
        this(value, null);
    }

    /**
     * Constructor
     *
     * @param value Value to be put in the node
     * @param next  Reference to the next node
     */
    Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
}