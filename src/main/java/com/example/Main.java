package com.example;

import com.example.Task_1.BudgetCalculator;
import com.example.Task_1.BudgetPurchase;
import com.example.Task_1.FruitsList;
import com.example.Task_1.Person;
import com.example.Task_2.*;

public class Main {

    public static void main(String[] args) {

        BankAccount bankAccount_1 = new BankAccount();
        BankAccount bankAccount_2 = new BankAccount();

        bankAccount_1.setBalance(100);
        System.out.println(bankAccount_1.getBalance());

        bankAccount_2.setBalance(-500);
        System.out.println(bankAccount_2.getBalance());


        Person person_1 = new Person("Nikolay", "Baskov", 25);
        Person person_2 = new Person("Nikita", "Pal", 43);
        Person person_3 = new Person("Masha", "Bob", 32);

        person_1.introduce();
        person_2.introduce();
        person_3.introduce();

        System.out.println(MathHelper.sum(34,54));
        System.out.println(MathHelper.max(122,43));
        System.out.println(MathHelper.isEven(34));

        Visitor visitor_1 = new Visitor();
        System.out.println(Visitor.getTotalVisitors());

        Visitor visitor_2 = new Visitor();
        System.out.println(Visitor.getTotalVisitors());

        Visitor visitor_3 = new Visitor();
        System.out.println(Visitor.getTotalVisitors());

        Visitor visitor_4 = new Visitor();
        System.out.println(Visitor.getTotalVisitors());

        Visitor visitor_5 = new Visitor();
        System.out.println(Visitor.getTotalVisitors());


        Product milk = new Product("Молоко", 100);
        Product bread = new Product("Хлеб", 80);
        Product cheese = new Product("Сыр", 300);

        Cart cart = new Cart();

        cart.addProduct(milk, 2);
        cart.addProduct(bread, 3);
        cart.addProduct(cheese, 1);

        System.out.println("Количество товаров: " + cart.getProductCount());
        System.out.println("Общая стоимость: " + cart.getTotalPrice());


//        System.out.println("=========================================================");
//        System.out.println("Задача 1:");
//        System.out.println("=========================================================");
//
//        Person person = new Person("Nikolay", "Baskov", 25);
//        person.introduce();
//
//        System.out.println();
//        System.out.println();
//
//        System.out.println("=========================================================");
//        System.out.println("Задача 2:");
//        System.out.println("=========================================================");
//
//        FruitsList fruitsList = new FruitsList();
//        fruitsList.run();
//
//        System.out.println();
//        System.out.println();
//
//        System.out.println("=========================================================");
//        System.out.println("Задача 3:");
//        System.out.println("=========================================================");
//
//        BudgetCalculator budgetCalculator = new BudgetCalculator();
//        budgetCalculator.run();
//
//        System.out.println();
//        System.out.println();
//
//        System.out.println("=========================================================");
//        System.out.println("Задача 4:");
//        System.out.println("=========================================================");
//
//        BudgetPurchase budgetPurchase = new BudgetPurchase();
//        budgetPurchase.run();
    }
}