package com.ga.examples;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterDemo {

    public static void main(String[] args) {

        List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        //We want to create a list of only the even numbers.
        List<Integer> evenNumberList =
            numberList.stream()
                .filter(number -> number % 2 == 0)
                .collect(Collectors.toList());

        evenNumberList.stream().forEach(number -> System.out.println(number));
    }
}
