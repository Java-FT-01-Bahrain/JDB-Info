public class DivideAndConquerQuickSort {
    int Partition(int[] array, int start, int end) {
        int pivot = array[end];
        int j = start;
        // Keep placing elements less than the pivot element to the left
        for (int i = start; i < end; i++) {
            if (array[i] < pivot) {
                // Swap array[i] with array[j]
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                j++;
            }
        }

        // Place the pivot i.e array[end] at its final position and return the pivot index
        // for partitioning the array
        int tmp = array[j];
        array[j] = array[end];
        array[end] = tmp;

        return j;
    }

    void QuickSort(int[] array, int start, int end) {
        int p;

        if (start < end) {
            p = Partition(array, start, end);
            QuickSort(array, start, p - 1);
            QuickSort(array, p + 1, end);
        }
    }
}
