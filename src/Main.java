import com.devtotti.CurrencyOperation;
import com.devtotti.CurrencyPair;

import static com.devtotti.Menu.*;

public class Main {
    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int option = getUserChoice();
            if (option == 9) {
                break;
            }

            CurrencyPair currencyPair = processUserInput(option);

            if (currencyPair == null) {
                System.out.println("Opción inválida");
                continue;
            }

            double fromAmount = getUserAmount();

            try {
                CurrencyOperation currencyOp = new CurrencyOperation(currencyPair, fromAmount);
                System.out.println(currencyOp);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            System.out.println("\nPresione Enter para continuar");
            try {
                System.in.read();
            } catch (Exception e) {
                e.getMessage();
            }
        }
    }
}