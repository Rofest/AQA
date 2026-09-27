package com.example;

import com.example.task1.Person;
import com.example.task2.*;

public class Main {

    public static void main(String[] args) {

        BankAccount bankAccount1 = new BankAccount("Nikita");
        BankAccount bankAccount2 = new BankAccount("Nikolay");

        bankAccount1.setBalance(100);
        System.out.println(bankAccount1.getBalance());

        try {
            bankAccount2.setBalance(-500);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }

        Person person1 = new Person("Nikolay", "Baskov", 25);
        Person person2 = new Person("Nikita", "Pal", 43);
        Person person3 = new Person("Masha", "Bob", 32);

        person1.introduce();
        person2.introduce();
        person3.introduce();

        System.out.println(MathHelper.sum(34, 54));
        System.out.println(MathHelper.max(122, 43));
        System.out.println(MathHelper.isEven(34));

        new Visitor();
        System.out.println(Visitor.getTotalVisitors());

        new Visitor();
        System.out.println(Visitor.getTotalVisitors());

        new Visitor();
        System.out.println(Visitor.getTotalVisitors());

        new Visitor();
        System.out.println(Visitor.getTotalVisitors());

        new Visitor();
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
    }
}