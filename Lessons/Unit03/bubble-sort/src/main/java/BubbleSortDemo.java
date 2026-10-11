public class BubbleSortDemo {
    public static void main(String[] args) {
        int[] arr = {9, 7, 5, 3, 1};
        System.out.println("\nArray elements before sorting: ");
        BubbleSort.printArray(arr);
        BubbleSort.bubbleSort(arr);
        System.out.println("\nArray elements after sorting: ");
        BubbleSort.printArray(arr);
    }
}
