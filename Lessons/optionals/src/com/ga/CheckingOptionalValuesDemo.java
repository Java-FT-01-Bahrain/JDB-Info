package com.ga;

import java.util.Optional;

public class CheckingOptionalValuesDemo {

    public static void main(String[] args) {

        String name = "Mark";
        Optional<String> optionalName = Optional.of(name);
        System.out.println(optionalName.isPresent());

        Optional<String> optionalValue = Optional.ofNullable(null);
        System.out.println(optionalValue.isEmpty());

        Integer age = 20;
        if(age != null) {
            System.out.println("My age is " + age);
        }

        Optional<Integer> ageOptional = Optional.ofNullable(age);
        ageOptional.ifPresent(myAge -> System.out.println("My age is " + myAge));
    }
}
