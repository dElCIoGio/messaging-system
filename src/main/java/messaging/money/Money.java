package main.java.messaging.money;

import java.util.Currency;

public class Money {

    private double amount;
    private final Currency currency;

    public Money(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void add(Money money) {
        if (money.getCurrency().equals(currency)){
            this.amount += money.getAmount();
        }
    }

    public void subtract(Money money) {
        if (money.getCurrency().equals(currency)){
            this.amount -= money.getAmount();
        }
    }

}
