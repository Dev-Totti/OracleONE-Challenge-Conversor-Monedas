package com.devtotti;

import java.time.LocalDateTime;

public class CurrencyOperation {
    private final String datetime;
    private final CurrencyPair currencyPair;
    private final double exchangeRate;
    private final double fromAmount;
    private final double toAmount;

    public CurrencyOperation(CurrencyPair currencyPair, double fromAmount) {
        this.datetime = LocalDateTime.now().toString();
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
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//        String datetime = this.datetime.format(formatter);
        return "%s | %s: %f | %.2f %s = %.2f %s".formatted(datetime, currencyPair, exchangeRate, fromAmount, currencyPair.fromCurrency(), toAmount, currencyPair.toCurrency());
    }
}

