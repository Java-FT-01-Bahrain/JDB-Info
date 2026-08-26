package com.ga.examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IndependentPractice1 {

    public static void main(String[] args) {
        List<Person> personList = createPersonList();

        //TODO: Create a List of Person Objects whose name starts with the letter M

        //TODO: Create a List of Strings containing the names of the Persons over the age of 40

        //TODO: Create a List of Person Objects whose name starts with the letter J and are under the age of 47
    }

    private static List<Person> createPersonList() {

        return Arrays.asList(
                new Person("Mark", 45),
                new Person("Henry", 30),
                new Person("John", 18),
                new Person("Morgan", 6),
                new Person("Amanda", 23),
                new Person("Tiffany", 60),
                new Person("Jim", 50),
                new Person("Janet", 45)
        );
    }
}
