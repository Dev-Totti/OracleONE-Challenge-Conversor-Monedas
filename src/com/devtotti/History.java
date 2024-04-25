package com.devtotti;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class History  {
    private String jsonFilePath;
    private List<CurrencyOperation> history;

    public History(String jsonFilePath){
        this.jsonFilePath = jsonFilePath;
        this.history = loadHistory();
    }

    public List<CurrencyOperation> loadHistory() {
        Gson gson = new Gson();
        try {
            FileReader myReader = new FileReader(jsonFilePath);
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

    public void saveHistory() {
        Gson gson = new Gson();
        try {
            FileWriter myWriter = new FileWriter(jsonFilePath);
            myWriter.write(gson.toJson(history));
            myWriter.close();
        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo");
        }
    }

    public void add(CurrencyOperation operation) {
        history.add(operation);
        saveHistory();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (CurrencyOperation operation : history) {
            sb.append(operation.toString()).append("\n");
        }
        return sb.toString();
    }
}



