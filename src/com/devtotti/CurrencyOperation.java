package com.devtotti;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CurrencyOperation {
    private final LocalDateTime datetime;
    private final CurrencyPair currencyPair;
    private final double exchangeRate;
    private final double fromAmount;
    private final double toAmount;

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

    public void writeToFile() {
        try {
            FileWriter myWriter = new FileWriter("operations_history.txt", true);
            myWriter.write(this.toString() + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo");
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String datetime = this.datetime.format(formatter);
        return "%s | %s: %f | %.2f %s = %.2f %s".formatted(datetime, currencyPair, exchangeRate, fromAmount, currencyPair.fromCurrency(), toAmount, currencyPair.toCurrency());
    }
}

