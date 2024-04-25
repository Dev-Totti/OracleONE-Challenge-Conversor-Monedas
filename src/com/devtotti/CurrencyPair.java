package com.devtotti;

public record CurrencyPair(String fromCurrency, String toCurrency) {
    @Override
    public String toString() {
        return "%s -> %s".formatted(fromCurrency, toCurrency);
    }
}
