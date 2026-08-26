package com.ga;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalOperationsDemo {

    public static class Person {

        private int age;

        public Person(int age) {
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static class PersonWithOptional {

        private Optional<Integer> age;

        public PersonWithOptional(Integer age) {
            this.age = Optional.ofNullable(age);
        }

        public Optional<Integer> getAge() {
            return age;
        }

        public void setAge(Optional<Integer> age) {
            this.age = age;
        }
    }

    public static void main(String[] args) {

        //filter
        Optional<Integer> ageOptional = Optional.ofNullable(25);
        boolean canBuyAlcohol = ageOptional.filter(age -> age >= 21).isPresent();
        System.out.println(canBuyAlcohol);

        ageOptional = Optional.ofNullable(20);
        canBuyAlcohol = ageOptional.filter(age -> age >= 21).isPresent();
        System.out.println(canBuyAlcohol);

        //map
        Person person = new Person(25);
        boolean canAlsoBuyAlchohol = Optional.ofNullable(person)
                .map(Person::getAge)
                .filter(age -> age >= 21)
                .isPresent();
        System.out.println(canAlsoBuyAlchohol);


        PersonWithOptional personWithOptional = new PersonWithOptional(25);
        boolean ableToBuyAlcohol = Optional.ofNullable(personWithOptional)
                .flatMap(PersonWithOptional::getAge)
                .filter(age -> age >= 21)
                .isPresent();
        System.out.println(ableToBuyAlcohol);
    }
}
