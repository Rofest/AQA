package com.example.Task_2;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product, int quantity) {
        if (products.containsKey(product)) {
            int currentQuantity = products.get(product);
            products.put(product, currentQuantity + quantity);
        } else {
            products.put(product, quantity);
        }
    }

    public int getProductCount() {
        int totalCount = 0;

        for (int quantity : products.values()) {
            totalCount = totalCount + quantity;
        }

        return totalCount;
    }

    public double getTotalPrice() {
        double totalPrice = 0;

        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            totalPrice = totalPrice + product.getPrice() * quantity;
        }

        return totalPrice;
    }
}