package ru.mirea.task13.beans;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

@Component
public class Student {
    @Value("${student.name}")
    String name;

    @Value("${student.last_name}")
    String lastName;

    @Value("${student.group}")
    String group;

    @PostConstruct
    public void init() {
        System.out.println("Name: " + name + "\nLast name: " + lastName + "\nGroup: " + group);
    }
}
