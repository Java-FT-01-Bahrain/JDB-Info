package com.ga.examples;

import java.util.Objects;

public class AnotherPerson {

    private String name;
    private String gender;
    private int age;
    private int salary;

    public AnotherPerson(String name, String gender, int age, int salary) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Person)) return false;

        AnotherPerson anotherPerson = (AnotherPerson) o;

        return age == anotherPerson.age &&
                salary == anotherPerson.salary &&
                Objects.equals(name, anotherPerson.name) &&
                Objects.equals(gender, anotherPerson.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, age, salary);
    }

    @Override
    public String toString() {
        return "Person {" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
