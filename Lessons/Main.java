import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

//        Arrays in java are a fixed size
        int[] myNumbers =  new int[6];

        myNumbers[0] = 100;

        myNumbers[1] = 200;

//        second way of declaring
        int[] myNumbers2 = {300,400,500,600};



        int[] myNumbers3 = myNumbers2;

        myNumbers3[0] = 100000;


        System.out.println(Arrays.toString(myNumbers2));
        System.out.println(Arrays.toString(myNumbers3));

        System.out.println(myNumbers2.length);

        int[] myNumbers4 = new int[myNumbers2.length];

        for(int i =0; i<myNumbers2.length;i++){

            myNumbers4[i] = myNumbers2[i];


        }

        int [] myNumbers5 = new int[myNumbers2.length +4];


//
        ArrayList<String> myStudents = new ArrayList<>();

        /*
        Add and remove to the beggining
        add and remove from the end

        add and remove from the middle
        */

//        add method by default adds to the end
        myStudents.add("Ali");
        myStudents.add("Mohammad");

        myStudents.add(0,"Salman");

        myStudents.addFirst("Talal");

        System.out.println(myStudents);

//        clear ArrayList

//        myStudents.clear();

        System.out.println(myStudents);


        for(String student : myStudents){
            System.out.println(student);
        }



        myStudents.set(0,"Maryam");

        System.out.println(myStudents);






        // 1. For the following array get the sum of all elements

        int[] numArr = {1, 2, 100, 300, 50, 12};

        // 2. for the numArr find the smallest value in the array

    }




}