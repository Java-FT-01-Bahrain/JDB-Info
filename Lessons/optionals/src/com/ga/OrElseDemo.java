package com.ga;

import java.util.Optional;

public class OrElseDemo {

    public static void main(String[] args) {

        String nullValue = null;

        //get
        Optional<String> nameOptional = Optional.ofNullable("Amanda");
        System.out.println(nameOptional.get());

        //orElse
        String orElseName = Optional.ofNullable(nullValue).orElse(getDefaultName());
        System.out.println(orElseName);

        //orElseGet
        String orElseGetName = Optional.ofNullable(nullValue).orElseGet(() -> getDefaultName());
        System.out.println(orElseGetName);

        //Difference between orElse and orElseGet
        System.out.println("orElse with populated Optional");
        String orElseSideEffect = Optional.ofNullable("John").orElse(getDefaultName());
        System.out.println(orElseSideEffect);

        System.out.println("orElseGet with populated Optional");
        String orElseGetName2 = Optional.ofNullable("John").orElseGet(() -> getDefaultName());
        System.out.println(orElseGetName);

        //orElseThrow
        String name = Optional.ofNullable(nullValue).orElseThrow(() -> new IllegalArgumentException("Name is missing"));
    }

    public static String getDefaultName() {
        System.out.println("In getDefaultName method");
        return "World";
    }
}
