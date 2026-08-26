package com.ga;

import java.util.Optional;

public class OptionalIntroDemo {

    public static void main(String[] args) {


        Optional<String> emptyOptional = Optional.empty();
        System.out.println(emptyOptional.isPresent());

        String name = "Mark";
        Optional<String> optionalName = Optional.of(name);
        System.out.println(optionalName.isPresent());

        try {
            String nullValue = null;
            Optional.of(nullValue);
        } catch(NullPointerException npe) {
            System.out.println(npe);
        }

        String stringValue = null;
        Optional<String> optionalValue = Optional.ofNullable(stringValue);
        System.out.println(optionalValue.isPresent());
    }
}
