package com.ga.examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortedDemo {

    public static void main(String[] args) {
        List<Person> personList = createPersonList();

        // sorted: sort the person list alphabetically
        List<Person> sortedList =
                personList.stream()
                        .sorted((person1, person2) -> person1.getName().compareTo(person2.getName()))
                        .collect(Collectors.toList());

        sortedList.forEach(person -> System.out.println(person));
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
