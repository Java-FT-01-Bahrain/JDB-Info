public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        int noOfPasses = arr.length - 1;
        //run the 'for' loop 'noOfPasses' times.
        boolean isSwapped;
        for (int i = 0; i < noOfPasses; i++) {
            //no of comparisons for each pass
            isSwapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    //swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSwapped = true;
                }
            }
            if (!isSwapped) {
                break;
            }
        }
    }

    public static void printArray(int[] arr) {
        for (int j : arr) {
            System.out.print("\t" + j);
        }
    }
}