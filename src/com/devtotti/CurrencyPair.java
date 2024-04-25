package com.devtotti;

public record CurrencyPair(String fromCurrency, String toCurrency) {
    @Override
    public String toString() {
        return "%s to %s".formatted(fromCurrency, toCurrency);
    }
}
