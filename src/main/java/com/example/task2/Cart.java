package com.example.task2;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(
                    "Количество должно быть больше нуля"
            );
        }

        products.merge(product, quantity, Integer::sum);
    }

    public int getProductCount() {
        int totalCount = 0;

        for (int quantity : products.values()) {
            totalCount += quantity;
        }

        return totalCount;
    }

    public double getTotalPrice() {
        double totalPrice = 0;

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            totalPrice += product.getPrice() * quantity;
        }

        return totalPrice;
    }
}