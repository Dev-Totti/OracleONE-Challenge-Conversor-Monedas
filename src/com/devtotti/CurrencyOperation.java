package com.devtotti;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrencyOperation {
    private final String datetime;
    private final CurrencyPair currencyPair;
    private final double exchangeRate;
    private final double fromAmount;
    private final double toAmount;

    public CurrencyOperation(CurrencyPair currencyPair, double fromAmount) {
        this.datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        this.currencyPair = currencyPair;
        this.exchangeRate = ExchangeRatesAPI.getExchangeRate(currencyPair);
        if (exchangeRate == -1) {
            throw new RuntimeException("No se pudo obtener el tipo de cambio");
        }
        this.fromAmount = fromAmount;
        this.toAmount = fromAmount * exchangeRate;
    }

    @Override
    public String toString() {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(datetime, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String datetime = localDateTime.format(outputFormatter);

        return "%s | %s: %f | %.2f %s = %.2f %s".formatted(datetime, currencyPair, exchangeRate, fromAmount, currencyPair.fromCurrency(), toAmount, currencyPair.toCurrency());
    }
}

