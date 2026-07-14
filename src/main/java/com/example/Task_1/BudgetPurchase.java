package com.example.Task_1;

import java.util.ArrayList;
import java.util.List;

public class BudgetPurchase {

    public void run() {
        List<Double> prices = new ArrayList<>();

        prices.add(199.99);
        prices.add(249.99);
        prices.add(399.99);
        prices.add(200.00);
        prices.add(150.50);

        double budget = 1000.00;
        calculatePurchases(prices, budget);
    }

    private void calculatePurchases(List<Double> prices, double budget) {
        double spentMoney = 0.0;
        int purchasedCount = 0;

        while (purchasedCount < prices.size()
                && spentMoney + prices.get(purchasedCount) <= budget) {

            spentMoney = spentMoney + prices.get(purchasedCount);
            purchasedCount++;
        }

        double remainingMoney = budget - spentMoney;
        int notPurchasedCount = prices.size() - purchasedCount;

        System.out.printf("Куплено: %d товара на сумму %.2f%n", purchasedCount, spentMoney);
        System.out.printf("Остаток бюджета: %.2f%n", remainingMoney);
        System.out.println("Не куплено: " + notPurchasedCount + " товара");
    }
}