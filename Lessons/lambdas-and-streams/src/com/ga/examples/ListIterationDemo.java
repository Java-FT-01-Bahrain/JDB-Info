package com.ga.examples;

import java.util.Arrays;
import java.util.List;

public class ListIterationDemo {

    public static void main(String[] args) {

        List<String> stringList = Arrays.asList("Hello", "World");

        stringList.stream().forEach(s -> {
            System.out.println(s);
        });
    }
}
