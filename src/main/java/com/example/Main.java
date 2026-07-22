package com.example;

import com.example.Task_1.BudgetCalculator;
import com.example.Task_1.BudgetPurchase;
import com.example.Task_1.FruitsList;
import com.example.Task_1.Person;

public class Main {

    public static void main(String[] args) {

        System.out.println("=========================================================");
        System.out.println("Задача 1:");
        System.out.println("=========================================================");

        Person person = new Person("Nikolay", "Baskov", 25);
        person.introduce();

        System.out.println();
        System.out.println();

        System.out.println("=========================================================");
        System.out.println("Задача 2:");
        System.out.println("=========================================================");

        FruitsList fruitsList = new FruitsList();
        fruitsList.run();

        System.out.println();
        System.out.println();

        System.out.println("=========================================================");
        System.out.println("Задача 3:");
        System.out.println("=========================================================");

        BudgetCalculator budgetCalculator = new BudgetCalculator();
        budgetCalculator.run();

        System.out.println();
        System.out.println();

        System.out.println("=========================================================");
        System.out.println("Задача 4:");
        System.out.println("=========================================================");

        BudgetPurchase budgetPurchase = new BudgetPurchase();
        budgetPurchase.run();
    }
}