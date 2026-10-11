public class QuickSortDemo {
    public static void main(String[] args) {

        int[] array = {7, 3, 5, 2, 4, 1, 8, 6, 0, 10, 9};

        System.out.print("Unsorted array : ");

        for (int j : array) System.out.print(j + " ");
        System.out.println();

        DivideAndConquerQuickSort divideAndConquerQuickSort = new DivideAndConquerQuickSort();
        divideAndConquerQuickSort.QuickSort(array, 0, array.length - 1);
        System.out.print("Sorted array using Quick Sort : ");

        for (int j : array) System.out.print(j + " ");
    }
}
