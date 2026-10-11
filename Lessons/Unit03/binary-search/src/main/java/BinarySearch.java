public class BinarySearch {
    /**
     * Performs a binary search on a sorted array to find a specific element.
     *
     * @param array - Sorted array of integers.
     * @param x     - Target number to search for.
     * @param low   - Starting index to search from.
     * @param high  - Ending index to search to.
     * @return The index of the target element if found, otherwise -1.
     */
    int binarySearch(int[] array, int x, int low, int high) {
        if (high >= low) {
            int mid = low + (high - low) / 2;  // This prevents potential overflow.
            if (array[mid] == x) {
                return mid;
            } else if (x < array[mid]) {
                return binarySearch(array, x, low, mid - 1);
            } else {
                return binarySearch(array, x, mid + 1, high);
            }
        }
        return -1;
    }
}