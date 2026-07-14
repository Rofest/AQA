package com.example.Task_1;

import java.util.ArrayList;
import java.util.List;


public class BudgetCalculator {

    public void run() {
        List<Double> prices = new ArrayList<>();

        prices.add(199.99);
        prices.add(249.99);
        prices.add(399.99);
        prices.add(200.00);
        prices.add(150.50);

        double budget = 1000.00;
        double totalPrice = 0.0;

        for (double price : prices) {
            totalPrice = totalPrice + price;
        }

        System.out.printf("Бюджет: %.2f%n",budget);
        System.out.printf("Общая сумма товаров: %.2f%n",totalPrice);

        if (budget >= totalPrice) {
            double remainingMoney = budget - totalPrice;

            System.out.printf("Бюджета хватает. Остаток: %.2f%n",remainingMoney);
        } else {
            double missingMoney = totalPrice - budget;

            System.out.printf("Бюджета не хватает. Не хватает: %.2f%n",missingMoney);
        }
    }
}