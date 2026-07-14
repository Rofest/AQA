package com.example.Task_1;

import java.util.ArrayList;
import java.util.List;

public class FruitsList {

    public void run() {
        List<String> fruits = new ArrayList<>();

        fruits.add("Яблоко");
        fruits.add("Банан");
        fruits.add("Апельсин");
        fruits.add("Груша");
        fruits.add("Манго");

        printFruits(fruits);
    }

    private void printFruits(List<String> fruits) {
        for (int i = 0; i < fruits.size(); i++) {
            System.out.println((i + 1)+ ". "+ fruits.get(i));
        }
    }
}