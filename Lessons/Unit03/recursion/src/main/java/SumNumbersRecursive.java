import java.util.ArrayList;
import java.util.List;

public class SumNumbersRecursive {
    // Method to calculate sum recursively
    public static int sumOfArray(Integer[] a, int n) {
        if (n == 0)
            // base case
            return a[n];
        else
            // recursive case
            return a[n] + sumOfArray(a, n - 1);
    }

    // Method- main()
    public static void main(String[] args) {
        // Creating a List of Integer type
        // Declaring an object- 'al'
        List<Integer> al = new ArrayList<Integer>();

        // Adding elements to the List
        // Custom inputs
        al.add(1);
        al.add(2);
        al.add(3);
        al.add(4);
        al.add(5);

        // Converting above List to array
        // using toArray() method
        Integer a[] = new Integer[al.size()];
        al.toArray(a);

        // Display message
        System.out.print("Elements in List : ");

        // Printing array of objects
        // using for each loop
        for (Integer obj : a) {
            System.out.print(obj + " ");
        }

        // Recursion math to calculate sum snd
        // storing sum in a variable
        int sum = sumOfArray(a, a.length - 1);

        // Next line
        System.out.println();

        // Print the sum returned above
        System.out.println("Sum of elements  : " + sum);
    }
}