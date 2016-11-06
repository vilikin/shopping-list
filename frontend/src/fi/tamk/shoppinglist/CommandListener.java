package fi.tamk.shoppinglist;

import fi.tamk.shoppinglist.utils.FileHandler;
import fi.tamk.shoppinglist.utils.Tools;

import java.util.Scanner;

/**
 * Implements a CLI listener that registers commands given to the application.
 *
 * @author Vili Kinnunen
 * @version 2016.1105
 * @since 1.8
 */
public class CommandListener {

    /**
     * Shopping list to interact with
     */
    private ShoppingList shoppingList;

    /**
     * Scanner listening to CLI input
     */
    private Scanner sc;

    /**
     * FileHandler responsible for saving and opening content
     */
    private FileHandler file;

    /**
     * If user input is processed or not
     */
    private boolean processCommands;

    /**
     * Initializes command listener to interact with shopping list.
     *
     * @param shoppingList Shopping list to interact with
     */
    public CommandListener(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
        this.sc = new Scanner(System.in);
        file = new FileHandler("shoppinglist.txt");

        processCommands = true;

        startLoop();
    }

    /**
     * Starts asking user for input.
     */
    private void startLoop() {
        while (processCommands) {
            System.out.println("Give shopping list " +
                    "(example: 1 milk;2 tomato;3 carrot;)");

            process(sc.nextLine());
        }
    }

    /**
     * Processes given commands and executes required operations.
     *
     * @param command Input given by user
     */
    private void process(String command) {
        command = command.trim();

        if (command.equals("exit")) {
            System.exit(0);
        } else if (command.equals("save")) {
            file.write(Tools.listToStr(shoppingList.getList()));
        } else if (command.equals("open")) {
            shoppingList.replace(Tools.strToList(file.read()));
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
