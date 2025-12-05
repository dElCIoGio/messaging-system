package main.java.messaging.factory;
import main.java.messaging.money.Money;
import java.util.Currency;

public class MoneyFactory {

    public static Money createEuro(double amount){
        return new Money(amount, Currency.getInstance("EUR"));
    }

    public static Money createDollar(double amount){
        return new Money(amount, Currency.getInstance("USD"));
    }

    public static Money createGBP(double amount){
        return new Money(amount, Currency.getInstance("GBP"));
    }

}
