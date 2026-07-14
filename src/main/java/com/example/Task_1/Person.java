package com.example.Task_1;

public class Person {

    public String firstName;
    public String lastName;
    public int age;

    public void run() {
        firstName = "Nikolay";
        lastName = "Baskov";
        age = 25;

        introduce();
    }

    public void introduce() {
        System.out.println(
                "Привет, меня зовут " + firstName + " " + lastName + ". Мне " + age + " лет.");
    }
}