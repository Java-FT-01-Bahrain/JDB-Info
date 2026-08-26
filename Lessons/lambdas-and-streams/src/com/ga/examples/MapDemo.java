package com.ga.examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapDemo {

    public static void main(String[] args) {

        List<String> numbersList = Arrays.asList("1", "2", "3", "4", "5");

        Stream<Integer> numberListStream =
            numbersList.stream()
                .map(number -> {
                    return Integer.valueOf(number);
                });

        List<Integer> newNumbersList =
            numbersList.stream()
                .map(number -> {
                    return Integer.valueOf(number);
                })
                .collect(Collectors.toList());

    }
}
