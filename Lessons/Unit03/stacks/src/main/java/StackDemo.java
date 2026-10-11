public class StackDemo {
    public static void main(String[] args) {
        Stack stack = new Stack(10);
        stack.push(10);
        stack.push(20);
        System.out.println(stack.peek());
        stack.push(30);
        System.out.println(stack.pop());
        System.out.println(stack.peek());
    }
}
