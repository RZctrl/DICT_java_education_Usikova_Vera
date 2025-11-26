package CurrencyExchange;

import java.util.*;

public class ExchangeCalculator {
    private final Scanner scanner;

    public ExchangeCalculator() {
        this.scanner = new Scanner(System.in);
    }

    public void executeConversion() {
        System.out.print("Please, enter the number of mycoins you have: ");
        double mycoins = scanner.nextDouble();

        System.out.print("Please, enter the exchange rate: ");
        double exchangeRate = scanner.nextDouble();

        double dollars = calculateDollars(mycoins, exchangeRate);
        System.out.printf("The total amount of dollars: %.2f\n", dollars);
    }

    private double calculateDollars(double mycoins, double rate) {
        return mycoins * rate;
    }
}
