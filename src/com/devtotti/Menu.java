package com.devtotti;

import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void displayMenu() {
        System.out.println("1. Convertir USD a COP");
        System.out.println("2. Convertir COP a USD");
        System.out.println("3. Convertir USD a BRL");
        System.out.println("4. Convertir BRL a USD");
        System.out.println("5. Convertir USD a ARS");
        System.out.println("6. Convertir ARS a USD");
        System.out.println("7. Convertir otras monedas");
        System.out.println("9. Salir");
    }

    public static int getUserChoice() {
        System.out.println("Ingrese una opción:");
        return scanner.nextInt();
    }

    public static double getUserAmount() {
        System.out.println("Ingrese la cantidad a convertir:");
        return scanner.nextDouble();
    }

    public static String getFromCurrency() {
        System.out.println("Ingrese la moneda de origen:");
        return scanner.nextLine();
    }

    public static String getToCurrency() {
        System.out.println("Ingrese la moneda de destino:");
        return scanner.nextLine();
    }

    public static CurrencyPair processUserInput(int option) {
        switch (option) {
            case 1:
                return new CurrencyPair("USD", "COP");
            case 2:
                return new CurrencyPair("COP", "USD");
            case 3:
                return new CurrencyPair("USD", "BRL");
            case 4:
                return new CurrencyPair("BRL", "USD");
            case 5:
                return new CurrencyPair("USD", "ARS");
            case 6:
                return new CurrencyPair("ARS", "USD");
            case 7:
                return new CurrencyPair(getFromCurrency(), getToCurrency());
            default:
                return null;
        }
    }
}
