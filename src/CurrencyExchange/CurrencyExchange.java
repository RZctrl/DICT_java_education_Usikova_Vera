package CurrencyExchange;

import java.util.*;

public class CurrencyExchange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RatesClient client = new RatesClient();
        Map<String, Currency> cache = new HashMap<>();

        System.out.print("Enter currency code \n> ");
        String baseCurrency = scanner.nextLine().trim().toLowerCase();

        Currency[] initialRates = client.getRates(baseCurrency);
        if (initialRates != null) {
            if (initialRates[0] != null) cache.put("USD", initialRates[0]);
            if (initialRates[1] != null) cache.put("EUR", initialRates[1]);
        }

        while (true) {
            System.out.print("Enter currency code for conversion \n> ");
            String targetCurrencyInput = scanner.nextLine().trim().toLowerCase();

            if (targetCurrencyInput.isEmpty()) {
                break;
            }

            System.out.print("Enter amount to convert \n>");
            double amount;
            try {
                amount = scanner.nextDouble();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input");
                scanner.nextLine();
                continue;
            }

            System.out.println("Checking the cache...");

            Currency targetRate = cache.get(targetCurrencyInput);

            if (targetRate != null) {
                System.out.println("It is in the cache!");
            } else {
                System.out.println("Sorry, but it is not in the cache!");
                Currency newRate = client.getRateForCurrency(baseCurrency, targetCurrencyInput);
                if (newRate != null) {
                    cache.put(targetCurrencyInput, newRate);
                    targetRate = newRate;
                } else {
                    System.out.println("Failed to get exchange rate");
                    continue;
                }
            }

            double convertedAmount = client.convertCurrency(amount, targetRate.getRate());
            System.out.printf("You received %.2f %s.\n", convertedAmount, targetCurrencyInput);
        }

        scanner.close();
    }
}