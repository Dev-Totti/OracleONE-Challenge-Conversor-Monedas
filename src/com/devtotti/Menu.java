package com.devtotti;

import java.util.Scanner;

public class Menu {
    public static History history = new History("operations_history.json");

    public static CurrencyCodes currencyCodes = new CurrencyCodes();

    public static void displayMainMenu() {
        System.out.println("\n=== CONVERSOR DE MONEDAS ===\n");
        System.out.println("[1] Convertir USD a COP");
        System.out.println("[2] Convertir COP a USD");
        System.out.println("[3] Convertir USD a BRL");
        System.out.println("[4] Convertir BRL a USD");
        System.out.println("[5] Convertir USD a ARS");
        System.out.println("[6] Convertir ARS a USD");
        System.out.println("\n[7] Convertir otras monedas");
        System.out.println("[8] Ver historial de operaciones");
        System.out.println("[0] Salir");
    }

    public static void displayOpMenu(CurrencyPair currencyPair) {
        System.out.printf(
                "\nCONVERSION SELECCIONADA: %s (%s) -> %s (%s)%n",
                currencyPair.fromCurrency(),
                currencyCodes.getCurrencyName(currencyPair.fromCurrency()),
                currencyPair.toCurrency(),
                currencyCodes.getCurrencyName(currencyPair.toCurrency())
        );
    }

    public static void waitForEnter() {
        System.out.print("\nPresione Enter para continuar");
        try {
            System.in.read();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static int getUserChoice() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("\nIngrese una opción: ");
            return scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Opción no válida");
            return getUserChoice();
        }
    }

    public static double getUserAmount() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
            System.out.println("Para regresar al menú principal, escriba '0'");
                System.out.print("Ingrese la cantidad a convertir : ");
                double amount = scanner.nextDouble();

                if (amount < 0) {
                    System.out.println("La cantidad no puede ser negativa");
                } else {
                    return amount;
                }
            } catch (Exception e) {
                System.out.println("Cantidad no válida");
                return getUserAmount();
            }
        }
    }

    public static String getCurrency(String text) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nPara ver los códigos de moneda, escriba 'CODES' | Para regresar al menú principal, escriba '0'");
//            System.out.println("Para regresar al menú principal, escriba '0'");
            System.out.print("Ingrese el codigo de la moneda " + text + ": ");

            String currency = scanner.nextLine().toUpperCase();
            if (currencyCodes.isValidCurrencyCode(currency)) {
                System.out.printf("\nMoneda Seleccionada: %s (%s)\n", currency, currencyCodes.getCurrencyName(currency));
                System.out.println("-------------------------------------------------");
                return currency;
            } else if (currency.equals("0")) {
                return null;
            } else if (currency.equals("CODES")) {
               currencyCodes.displayCurrencyCodes();
            } else {
                System.out.println("Moneda no válida. Intente de nuevo");
            }
        }
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
                String fromCurrency = getCurrency("Origen");
                if (fromCurrency == null) {
                    return null;
                }
                String toCurrency = getCurrency("Destino");
                if (toCurrency == null) {
                    return null;
                }
                return new CurrencyPair(fromCurrency, toCurrency);
            default:
                return null;

        }
    }

    public static void run() {
        while (true) {
            displayMainMenu();
            int option = getUserChoice();

            if (option == 8) {
                System.out.println(history);
                waitForEnter();
                continue;

            } else if (option == 0) {
                return;
            }

            CurrencyPair currencyPair = processUserInput(option);

            if (currencyPair == null) {
                continue;
            }

            while (true) {
                displayOpMenu(currencyPair);
                double fromAmount = getUserAmount();

                if (fromAmount == 0) {
                    break;
                }

                try {
                    CurrencyOperation currencyOp = new CurrencyOperation(currencyPair, fromAmount);
                    System.out.println("\n" + currencyOp);
                    history.add(currencyOp);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                } finally {
                    waitForEnter();
                }
            }
        }
    }
}