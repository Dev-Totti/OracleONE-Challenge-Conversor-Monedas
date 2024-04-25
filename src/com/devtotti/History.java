package com.devtotti;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class History {
    public static List<CurrencyOperation> loadHistory() {
        Gson gson = new Gson();
        try {
            FileReader myReader = new FileReader("operations_history.json");
            CurrencyOperation[] history = gson.fromJson(myReader, CurrencyOperation[].class);
            return new ArrayList<>(Arrays.asList(history));
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo");
            return new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Error al leer el archivo");
            return new ArrayList<>();
        }
    }

    public static void saveHistory(List<CurrencyOperation> history) {
        Gson gson = new Gson();
        try {
            FileWriter myWriter = new FileWriter("operations_history.json");
            myWriter.write(gson.toJson(history));
            myWriter.close();
        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo");
        }
    }

    public static void displayHistory(List<CurrencyOperation> history) {
        System.out.println("\nHistorial de operaciones: \n");
        for (CurrencyOperation operation : history) {
            System.out.println(operation);
        }
    }
}



