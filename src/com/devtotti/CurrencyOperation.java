package com.devtotti;

import java.time.LocalDateTime;

public class CurrencyOperation {
    private LocalDateTime datetime;
    private CurrencyPair currencyPair;
    private double exchangeRate;
    private double fromAmount;
    private double toAmount;

    public CurrencyOperation(CurrencyPair currencyPair, double fromAmount) {
        this.datetime = LocalDateTime.now();
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
        return "%s | %.2f %s | %.2f %s | %s: %.2f".formatted(datetime, fromAmount, currencyPair.fromCurrency(), toAmount, currencyPair.toCurrency(), currencyPair, exchangeRate);
    }
}

