package CurrencyExchange;

import java.util.*;

public class CurrencyExchange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RatesClient client = new RatesClient();

        System.out.print("Enter your currency code \n> ");
        String baseCurrency = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter the amount to convert \n> ");
        double amount = scanner.nextDouble();


        Currency[] rates = client.getRates(baseCurrency);

        if (rates != null && rates[0] != null && rates[1] != null) {

            System.out.println("\nExchange rates:");
            System.out.println(rates[0]);
            System.out.println(rates[1]);


            System.out.println("\nConversion results:");
            double convertToUSD = client.convertCurrency(amount, rates[0].getRate());
            double convertToEUR = client.convertCurrency(amount, rates[1].getRate());

            System.out.printf("%.2f %s = %.2f USD\n", amount, baseCurrency, convertToUSD);
            System.out.printf("%.2f %s = %.2f EUR\n", amount, baseCurrency, convertToEUR);
        } else {
            System.out.println("Failed to fetch exchange rates for currency: " + baseCurrency);
        }

        scanner.close();
    }
}