package com.ga.examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IndependentPractice1Completed {

    public static void main(String[] args) {
        List<Person> personList = createPersonList();

        //TODO: Create a List of Person Objects whose name starts with the letter M
        List<Person> personsThatNameStartWithM = personList.stream()
                .filter(p -> p.getName().startsWith("M"))
                .collect(Collectors.toList());

        personsThatNameStartWithM.stream().forEach(person -> System.out.println(person));

        //TODO: Create a List of Strings containing the names of the Persons over the age of 40
        List<String> namesOfPersonsOverAgeOfForty = personList.stream()
                .filter(p -> p.getAge() > 40)
                .map(p -> p.getName())
                .collect(Collectors.toList());

        namesOfPersonsOverAgeOfForty.stream().forEach(name -> System.out.println(name));

        //TODO: Create a List of Person Objects whose name starts with the letter J and are under the age of 47
        List<Person> personsWithJNameAndUnderFortySeven= personList.stream()
                .filter(p -> p.getName().startsWith("J") && p.getAge() < 47)
                .collect(Collectors.toList());

        personsWithJNameAndUnderFortySeven.stream().forEach(person -> System.out.println(person));
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
