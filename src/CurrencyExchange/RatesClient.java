package CurrencyExchange;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;
import org.json.JSONException;

public class RatesClient {
    private final HttpClient httpClient;

    public RatesClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public Currency[] getRates(String baseCurrency) {
        try {
            if (baseCurrency.equalsIgnoreCase("USD")) {
                Currency usdRate = new Currency("USD", "US Dollar", 1.0, "N/A");
                Currency eurRate = getRateForCurrency(baseCurrency, "eur");
                return new Currency[]{usdRate, eurRate};
            } else if (baseCurrency.equalsIgnoreCase("EUR")) {
                Currency eurRate = new Currency("EUR", "Euro", 1.0, "N/A");
                Currency usdRate = getRateForCurrency(baseCurrency, "usd");
                return new Currency[]{usdRate, eurRate};
            } else {
                Currency usdRate = getRateForCurrency(baseCurrency, "usd");
                Currency eurRate = getRateForCurrency(baseCurrency, "eur");
                return new Currency[]{usdRate, eurRate};
            }
        } catch (Exception e) {
            System.err.println("Error fetching exchange rates");
            return null;
        }
    }

    public Currency getRateForCurrency(String baseCurrency, String targetCurrency) {
        if (baseCurrency.equalsIgnoreCase(targetCurrency)) {
            String name = targetCurrency.equalsIgnoreCase("usd") ? "US Dollar" :
                    targetCurrency.equalsIgnoreCase("eur") ? "Euro" : targetCurrency;
            return new Currency(targetCurrency.toLowerCase(), name, 1.0, "N/A");
        }

        try {
            String url = String.format("http://www.floatrates.com/daily/%s.json",
                    baseCurrency.toLowerCase());
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = httpClient.send(
                    request, HttpResponse.BodyHandlers.ofString());

            JSONObject jsonResponse = new JSONObject(response.body());
            return parseCurrencyRate(jsonResponse, targetCurrency);
        } catch (Exception e) {
            System.err.println("Error fetching rate");
            return null;
        }
    }

    private Currency parseCurrencyRate(JSONObject jsonResponse, String currencyCode) {
        try {
            JSONObject currencyData = jsonResponse.getJSONObject(currencyCode);
            String code = currencyData.getString("code");
            String name = currencyData.getString("name");
            double rate = currencyData.getDouble("rate");
            String date = currencyData.getString("date");

            return new Currency(code, name, rate, date);
        } catch (JSONException e) {
            System.err.println("Error parsing data");
            return null;
        }
    }

    public double convertCurrency(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }
}