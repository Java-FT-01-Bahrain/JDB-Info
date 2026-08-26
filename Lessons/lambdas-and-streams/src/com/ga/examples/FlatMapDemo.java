package com.ga.examples;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapDemo {

    public static void main(String[] args) {
        List<Person> personList1 =
                Arrays.asList(
                        new Person("Tom", 30),
                        new Person("Jeff", 70));

        List<Person> personList2 =
                Arrays.asList(
                        new Person("Jane", 45),
                        new Person("Alice", 38));

        // Create a list of lists, containing both of our Person lists above.
        List<List<Person>> listOfPersonLists = Arrays.asList(personList1, personList2);

        // flatMap(): Flatten the list of person lists.
        List<Person> flatPersonList =
                listOfPersonLists.stream()
                        .flatMap(Collection::stream) // Convert each list into a stream, then flatten them all into a single stream.
                        .collect(Collectors.toList());

        flatPersonList.forEach(person -> System.out.println(person));
    }
}
