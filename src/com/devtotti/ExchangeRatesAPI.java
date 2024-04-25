package com.devtotti;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRatesAPI {
    public static double getExchangeRate(CurrencyPair currencyPair) {
        String api_key = "3df16ef40728e033fb92c5ac";
        String fromCurrency = currencyPair.fromCurrency();
        String toCurrency = currencyPair.toCurrency();

        try {
            String url = "https://v6.exchangerate-api.com/v6/" + api_key + "/pair/" + fromCurrency + "/" + toCurrency;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Gson gson = new Gson();
                ExchangeRate exchangeRate = gson.fromJson(response.body(), ExchangeRate.class);
                return exchangeRate.conversion_rate();

            } else {
                System.out.println("Response Error: " + response.statusCode());
                return -1;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
}
