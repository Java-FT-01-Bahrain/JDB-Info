import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

// 1. For the following array get the sum of all elements

        int[] numArr = {1, 2, 100, 300, 50, 12};

// 2. for the numArr find the smallest value in the array

// 3. for the numArr create a new arrayList that contains the values in the original array but reversed
//        input: {1,3,5,7,9}
//        output: [9,7,5,3,1]

        ArrayList<Integer> newArrListNums = new ArrayList<>();
        newArrListNums.add(10);

        System.out.println(newArrListNums);


// 4. for the following array of students create a new ArrayList that will only contain the students who's name starts with M

        String[] students = {
                "Ali",
                "Ebtisam",
                "Fadhel",
                "Fatema",
                "Maryam",
                "Mohamed",
                "Muhammad",
                "Salman",
                "Talal",
                "Zahraa"
        };



// 5. for the following hashmap retrieve then System.out.println(); your own name

        HashMap<Integer, String> studentMap = new HashMap<>();

        studentMap.put(123, "Ali");
        studentMap.put(456, "Ebtisam");
        studentMap.put(789, "Fadhel");
        studentMap.put(101, "Fatema");
        studentMap.put(112, "Maryam");
        studentMap.put(131, "Mohamed");
        studentMap.put(415, "Muhammad");
        studentMap.put(161, "Salman");
        studentMap.put(718, "Talal");
        studentMap.put(192, "Zahraa");




//  6. if the following array represents the seating chart in a class
//        a true is a filled in seat and false is an empty seat then count the number of empty seats


//  Bonus: Now lets check if an empty seat is also empty on both the left and right side

        boolean[] seating = {true,true,false,false,false,true,true,false,true,false,false,false,true,false};
    }

}