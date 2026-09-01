import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class MainTest {

    @Test
    void findLargestAndSmallest() {
        Assertions.assertArrayEquals(new int[]{1,7},Main.findLargestAndSmallest(new int[]{1,5,7,2,3}));
        Assertions.assertArrayEquals(new int[]{3,3},Main.findLargestAndSmallest(new int[]{3,3}));
        Assertions.assertArrayEquals(new int[]{1,5},Main.findLargestAndSmallest(new int[]{1,2,3,4,5}));
        Assertions.assertArrayEquals(new int[]{7,7},Main.findLargestAndSmallest(new int[]{7,7,7,7,7,7}));
        Assertions.assertArrayEquals(new int[]{3,5},Main.findLargestAndSmallest(new int[]{5,4,3}));
        Assertions.assertArrayEquals(new int[]{-5,5},Main.findLargestAndSmallest(new int[]{5,4,-5,3}));
    }

    @Test
    void removeDuplicatesFromArray() {
        Object[] arr1 = Main.removeDuplicatesFromArray(new int[]{1,4,3,2,1});
        Arrays.sort(arr1);
        Assertions.assertArrayEquals(new Object[]{1,2,3,4},arr1);

        Object[] arr2 = Main.removeDuplicatesFromArray(new int[]{4,3,2,1});
        Arrays.sort(arr2);
        Assertions.assertArrayEquals(new Object[]{1,2,3,4},arr2);

        Object[] arr3 = Main.removeDuplicatesFromArray(new int[]{1,1,1,1});
        Arrays.sort(arr3);
        Assertions.assertArrayEquals(new Object[]{1},arr3);
    }

    @Test
    void sumOfTwoLargest() {
        Assertions.assertEquals(0,Main.sumOfTwoLargest(new int[]{}));
        Assertions.assertEquals(5,Main.sumOfTwoLargest(new int[]{5}));
        Assertions.assertEquals(9,Main.sumOfTwoLargest(new int[]{5,4}));
        Assertions.assertEquals(15,Main.sumOfTwoLargest(new int[]{1,7,5,2,-1,8}));
        Assertions.assertEquals(-2,Main.sumOfTwoLargest(new int[]{-1,-7,-5,-2,-1,-8}));
    }
}