package fi.tamk.shoppinglist;

/**
 * Implements client side of the shopping list application.
 *
 * @author Vili Kinnunen
 * @version 2016.11.5
 * @since 1.8
 */
public class Main {

    public static void main(String[] args) {
        ShoppingList shoppingList = new ShoppingList();

        CommandListener cliListener = new CommandListener(shoppingList);

        cliListener.start();
    }
}
