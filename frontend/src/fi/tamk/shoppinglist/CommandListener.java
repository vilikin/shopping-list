package fi.tamk.shoppinglist;

import java.util.Scanner;

/**
 * Implements a CLI listener that registers commands given to the application.
 *
 * @author Vili Kinnunen
 * @version 2016.11.5
 * @since 1.8
 */
public class CommandListener {
    ShoppingList shoppingList;
    Scanner sc;

    public CommandListener(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
        this.sc = new Scanner(System.in);
    }

    public void start() {
        System.out.println("SHOPPING LIST\n" +
                "Tampere University of Applied Sciences");
    }
}
