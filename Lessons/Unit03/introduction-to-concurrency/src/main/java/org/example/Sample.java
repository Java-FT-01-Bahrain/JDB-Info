package org.example;

public class Sample{
    public static String getHelloWorld(){
        return "Hello World!";
    }

    public static void main(String[] args) {
        Sample sample = new Sample();
        // sample.getHelloWorld();
        Sample.getHelloWorld();
    }
}