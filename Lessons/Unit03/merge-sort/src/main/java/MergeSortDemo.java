public class MergeSortDemo {
    public static void main(String[] args) {

        int[] array = {7, 3, 5, 2, 4, 1, 8, 6};
        //int[] aux_array = new int[array.length];

        System.out.print("Unsorted array: ");

        for (int j : array) System.out.print(j + " ");
        System.out.println();

        DivideAndConquerMergeSort divideAndConquerMergeSort = new DivideAndConquerMergeSort(array.length);
        divideAndConquerMergeSort.MergeSort(array, 0, array.length - 1);
        System.out.print("Sorted array using Merge-Sort: ");

        for (int j : array) System.out.print(j + " ");
    }
}
