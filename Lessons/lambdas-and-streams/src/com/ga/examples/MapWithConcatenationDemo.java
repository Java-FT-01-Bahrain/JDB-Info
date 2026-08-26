package com.ga.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapWithConcatenationDemo {

    public static void main(String[] args) {

        List<String> stringList = Arrays.asList("My name is ", "My friends call me ", "My mother calls me ");

        List<String> concatenatedList =
            stringList.stream()
                .map(s -> apply(s))
                .collect(Collectors.toList());

        //Now output the values of the concatenatedList using streams.
        concatenatedList.stream().forEach(s -> {
            System.out.println(s);
        });

        //Now output the values of the original stringList using streams to show that the list is unaltered.
        stringList.stream().forEach(s -> {
            System.out.println(s);
        });
    }

    private static String apply(String s) {
        return s + "Jim";
    }
}
