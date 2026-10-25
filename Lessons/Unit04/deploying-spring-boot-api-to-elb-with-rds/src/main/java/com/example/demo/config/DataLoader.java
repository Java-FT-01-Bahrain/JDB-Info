package com.example.demo.config;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists to avoid duplicates
        if (studentRepository.count() == 0) {
            loadSeedData();
        }
    }

    private void loadSeedData() {
        System.out.println("Loading seed data...");

        // Create sample students
        Student[] students = {
                new Student("John", "Doe"),
                new Student("Jane", "Smith"),
                new Student("Michael", "Johnson"),
                new Student("Emily", "Davis"),
                new Student("David", "Wilson"),
                new Student("Sarah", "Brown"),
                new Student("Robert", "Jones"),
                new Student("Lisa", "Garcia"),
                new Student("James", "Miller"),
                new Student("Amanda", "Moore"),
                new Student("Christopher", "Taylor"),
                new Student("Jessica", "Anderson"),
                new Student("Matthew", "Thomas"),
                new Student("Ashley", "Jackson"),
                new Student("Joshua", "White"),
                new Student("Stephanie", "Harris"),
                new Student("Daniel", "Martin"),
                new Student("Rachel", "Thompson"),
                new Student("Andrew", "Garcia"),
                new Student("Megan", "Martinez")
        };

        studentRepository.saveAll(Arrays.asList(students));

        System.out.println("Seed data loaded successfully! Created " + students.length + " students.");
    }
}
