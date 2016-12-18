package fi.tamk.shoppinglist.cli;

import fi.tamk.shoppinglist.ShoppingList;
import fi.tamk.shoppinglist.ShoppingListItem;
import fi.tamk.shoppinglist.utils.Tools;

/**
 * Implements a CLI listener that registers commands given to the application.
 *
 * @author Vili Kinnunen
 * @version 2016.1105
 * @since 1.8
 */
public class CommandListener {

    /**
     * Shopping list to interact with.
     */
    private ShoppingList shoppingList;

    /**
     * If user input is processed or not.
     */
    public boolean processCommands;

    /**
     * Initializes command listener to interact with shopping list.
     *
     * @param shoppingList Shopping list to interact with
     */
    public CommandListener(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;

        processCommands = true;

        Thread loop = new Thread(new Loop(this));
        loop.start();
    }

    /**
     * Processes given commands and executes required operations.
     *
     * @param command Input given by user
     */
    public void process(String command) {
        command = command.trim();

        if (command.equals("exit")) {
            System.exit(0);
        } else {
            shoppingList.append(Tools.strToList(command));

            ShoppingListItem[] listItems = Tools.listToArray(
                    shoppingList.getList());

            System.out.println("Your Shopping List now:");

            for (ShoppingListItem listItem : listItems) {
                System.out.println("  " + listItem.getQuantity() + " " +
                        listItem.getName());
            }

            System.out.println();
        }
    }
}
