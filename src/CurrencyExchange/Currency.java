package CurrencyExchange;

public class Currency {
    private final String code;
    private final String name;
    private final double exchangeRate;

    public Currency(String code, String name, double exchangeRate) {
        this.code = code;
        this.name = name;
        this.exchangeRate = exchangeRate;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public double convertMyCoins(double myCoins) {
        return myCoins * exchangeRate;
    }
}
