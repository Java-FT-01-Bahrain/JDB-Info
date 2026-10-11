public class DivideAndConquerMergeSort {
    private final int[] aux_array;

    // Allocate space to auxiliary array which is to be used for merging the sorted elements
    DivideAndConquerMergeSort(int sz) {
        aux_array = new int[sz];
    }

    // Function to merge the sorted sub-arrays into a bigger array.
    //void Merge (int[] aux_array, int[] array, int low, int mid, int high) {
    void Merge(int[] array, int low, int mid, int high) {

        int left_index = low;
        int right_index = mid + 1;
        int aux_index = low;

        /* Merge elements from array[low : mid] and array[mid+1 : high]
                                      ^                     ^
                                      |                     |
                                  left_index         right_index */

        // Pick the smaller number between the left part and the right part
        while (left_index <= mid && right_index <= high) {
            if (array[left_index] <= array[right_index]) {
                aux_array[aux_index++] = array[left_index++];
            } else {
                aux_array[aux_index++] = array[right_index++];
            }
        }

        if (left_index <= mid) {
            // There are still some unpicked numbers in the left part
            for (int i = left_index; i <= mid; i++) {
                aux_array[aux_index++] = array[i];
            }
        } else {
            // There are still some unpicked numbers in the right part
            for (int i = right_index; i <= high; i++) {
                aux_array[aux_index++] = array[i];
            }
        }

        // Copy numbers from the sorted auxiliary array into the original array
        if (high + 1 - low >= 0) System.arraycopy(aux_array, low, array, low, high + 1 - low);
    }

    // MergeSort function splits each sub-array till only a single element is available in the sub-array
    // and then proceeds with the merge of the sub-arrays into a bigger auxiliary array.
    //void MergeSort (int[] aux_array, int[] array, int low, int high) {
    void MergeSort(int[] array, int low, int high) {

        int mid;
        if (low < high) {
            mid = (low + high) / 2;
            MergeSort(array, low, mid); // Recursively splits left half of the array
            MergeSort(array, mid + 1, high); // Recursively splits right half of the array
            Merge(array, low, mid, high); // Merge left and right half of the array keeping it sorted.
        }
    }
}
