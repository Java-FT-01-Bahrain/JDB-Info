public class Stack {
    int capacity;
    int[] array;
    int top;

    Stack(int sizeOfStack) {
        capacity = sizeOfStack;
        array = new int[capacity];
        top = -1;
    }
}
