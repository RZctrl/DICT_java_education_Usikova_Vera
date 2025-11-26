package CurrencyExchange;

import java.util.*;

public class ExchangeCalculator {
    private final Scanner scanner;
    private final Map<String, Currency> currencies;

    public ExchangeCalculator() {
        this.scanner = new Scanner(System.in);
        this.currencies = initCurrencies();
    }

    private Map<String, Currency> initCurrencies() {
        Map<String, Currency> currencyMap = new LinkedHashMap<>();

        currencyMap.put("ARS", new Currency("ARS", "Argentine Peso", 0.82));
        currencyMap.put("HNL", new Currency("HNL", "Honduran Lempira", 0.17));
        currencyMap.put("AUD", new Currency("AUD", "Australian Dollar", 1.9622));
        currencyMap.put("MAD", new Currency("MAD", "Moroccan Dirham", 0.208));

        return currencyMap;
    }

    public void executeConversion() {
        System.out.print("Please, enter the number of mycoins you have: ");
        double mycoins = scanner.nextDouble();

        for (Currency currency : currencies.values()) {
            double convertedAmount = currency.convertMyCoins(mycoins);
            System.out.printf("I will get %.2f %s from the sale of %.1f mycoins.%n",
                    convertedAmount, currency.getCode(), mycoins);
        }
    }
}