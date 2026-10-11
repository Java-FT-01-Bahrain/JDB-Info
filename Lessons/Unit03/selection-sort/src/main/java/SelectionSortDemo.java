public class SelectionSortDemo {
    public static void main(String[] args) {
        SelectionSort sorter = new SelectionSort();
        int[] array = {64, 25, 12, 22, 11};

        System.out.println("Original array:");
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();

        // Sort the array
        sorter.sort(array);

        System.out.println("Sorted array:");
        for (int value : array) {
            System.out.print(value + " ");
        }
    }
}
