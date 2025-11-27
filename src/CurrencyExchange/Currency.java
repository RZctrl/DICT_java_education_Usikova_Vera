package CurrencyExchange;

public class Currency {
    private final String code;
    private final String name;
    private final double rate;
    private final String date;

    public Currency(String code, String name, double rate, String date) {
        this.code = code;
        this.name = name;
        this.rate = rate;
        this.date = date;
    }


    public String getCode() { return code; }
    public String getName() { return name; }
    public double getRate() { return rate; }
    public String getDate() { return date; }

    @Override
    public String toString() {
        return String.format("%s (%s): %.6f (date: %s)", code, name, rate, date);
    }
}