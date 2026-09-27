package com.example.task2;

public class BankAccount {

    private String ownerName;
    private double balance;

    public BankAccount(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException(
                    "Баланс не может быть отрицательным: " + balance
            );
        }

        this.balance = balance;
    }
}