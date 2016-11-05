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
    private ShoppingList shoppingList;
    private Scanner sc;
    private FileHandler file;
    private boolean processCommands;

    public CommandListener(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
        this.sc = new Scanner(System.in);
        file = new FileHandler("shoppinglist.txt");


        processCommands = true;

        startLoop();
    }

    private void startLoop() {
        while (processCommands) {
            System.out.println("Give shopping list " +
                    "(example: 1 milk;2 tomato;3 carrot;)");

            process(sc.nextLine());
        }
    }

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

            ShoppingListItem[] listItems = Tools.listToArray(shoppingList.getList());

            System.out.println("Your Shopping List now:");

            for (ShoppingListItem listItem : listItems) {
                System.out.println("  " + listItem.getQuantity() + " " +
                        listItem.getName());
            }

            System.out.println();
        }
    }
}
