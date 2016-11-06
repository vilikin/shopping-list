package fi.tamk.shoppinglist;

/**
 * Implements client side of the shopping list application.
 *
 * @author Vili Kinnunen
 * @version 2016.1105
 * @since 1.8
 */
public class Main {

    /**
     * Initializes shopping list and CLI.
     *
     * @param args Command-line parameters (not used)
     */
    public static void main(String[] args) {
        ShoppingList shoppingList = new ShoppingList();

        System.out.println("SHOPPING LIST\n" +
                "Tampere University of Applied Sciences");

        CommandListener cliListener = new CommandListener(shoppingList);
    }
}
