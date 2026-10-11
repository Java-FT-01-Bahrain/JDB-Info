public class DoublyLinkedList {

    DoublyNode head;
    DoublyNode tail;
    int size;

    public void create(int value) {
        DoublyNode node = new DoublyNode(value);
        node.next = null;
        node.prev = null;
        head = node;
        tail = node;
        size = 1;
    }

    public void insert(int value, int index) {
        DoublyNode newNode = new DoublyNode(value);
        if (head == null) {
            create(value);
            return;
        }
        if (index == 0) {
            newNode.next = head;
            newNode.prev = null;
            head.prev = newNode;
            head = newNode;
        } else if (index >= size) {
            newNode.next = null;
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            DoublyNode tmpNode = head;
            int i = 0;
            while (i < index - 1) {
                tmpNode = tmpNode.next;
                i++;
            }
            newNode.prev = tmpNode;
            newNode.next = tmpNode.next;
            tmpNode.next = newNode;
            newNode.next.prev = newNode;
        }
        size++;
    }

    public void traverse() {
        if (head == null) {
            System.out.println("DLL does not exist.");
            return;
        }
        DoublyNode tmpNode = head;
        for (int i = 0; i < size; i++) {
            System.out.print(tmpNode.value);
            if (i < size - 1) {
                System.out.print("->");
            }
            tmpNode = tmpNode.next;
        }
        System.out.println();
    }

    public void reverseTraverse() {
        if (head != null) {
            DoublyNode tmpNode = tail;
            for (int i = 0; i < size; i++) {
                System.out.print(tmpNode.value);
                if (i < size - 1) {
                    System.out.print("<-");
                }
                tmpNode = tmpNode.prev;
            }
        } else {
            System.out.println("DLL does not exist!");
        }
        System.out.println();
    }

    public boolean searchNode(int value) {
        if (head != null) {
            DoublyNode tmpNode = head;
            for (int i = 0; i < size; i++) {
                if (tmpNode.value == value) {
                    System.out.println("Found node at location - " + i);
                    return true;
                }
                tmpNode = tmpNode.next;
            }
        }
        System.out.println("Node not found!");
        return false;
    }

    public void deleteNode(int index) {
        if (head == null) {
            System.out.println("Single Linked List does no exist.");
        } else if (index == 0) {
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.prev = null;
            }
            size--;
        } else if (index >= size) {
            DoublyNode tempNode = tail.prev;
            if (size == 1) {
                head = null;
                tail = null;
            } else {
                tempNode.next = null;
                tail = tempNode;
            }
            size--;
        } else {
            DoublyNode tempNode = head;
            for (int i = 0; i < index - 1; i++) {
                tempNode = tempNode.next;
            }
            tempNode.next = tempNode.next.next;
            tempNode.next.prev = tempNode;
            size--;
        }
    }

    public void deleteDLL() {
        DoublyNode tempNode = head;
        for (int i = 0; i < size; i++) {
            tempNode.prev = null;
            tempNode = tempNode.next;
        }
        head = null;
        tail = null;
        System.out.println("DLL has been deleted!");
    }
}
