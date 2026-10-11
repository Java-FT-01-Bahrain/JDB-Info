public class BinarySearchDemo {
    public static void main(String[] args) {
        int[] array = {2, 4, 6, 8, 10, 12};
        BinarySearch binarySearch = new BinarySearch();
        int result = binarySearch.binarySearch(array, 4, 0, array.length - 1); // Corrected the upper index limit
        if (result == -1) {
            System.out.println("The Element is not found in the array");
        } else {
            System.out.println("The Element is found in the array at index " + result);
        }
    }
}