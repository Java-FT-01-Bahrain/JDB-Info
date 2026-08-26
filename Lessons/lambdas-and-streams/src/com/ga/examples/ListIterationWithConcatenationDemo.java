package com.ga.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListIterationWithConcatenationDemo {

    public static void main(String[] args) {

        List<String> stringList = Arrays.asList("My name is ", "My friends call me ", "My mother calls me ");
        List<String> concatenatedList = new ArrayList<>();

        stringList.stream().forEach(stringValue -> concatenatedList.add(stringValue + "Jim"));

        //Now output the values of the concatenatedList using streams.
        concatenatedList.forEach(stringValue -> System.out.println(stringValue));

        //Now output the values of the original stringList using streams to show that the list is unaltered.
        stringList.stream().forEach(stringValue -> System.out.println(stringValue));
    }
}
