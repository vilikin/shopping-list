package fi.tamk.shoppinglist.utils;

import fi.tamk.shoppinglist.ShoppingListItem;

/**
 * Implements collection of tools to use with the shopping list application.
 *
 * @author Vili Kinnunen
 * @version 2016.1105
 * @since 1.8
 */
public class Tools {

    /**
     * Converts string representation of shopping list to a list of items.
     *
     * @param listStr String representation of a shopping list
     * @return List of items
     */
    public static MyLinkedList<ShoppingListItem> strToList(String listStr) {
        MyLinkedList<ShoppingListItem> returnList = new MyLinkedList<>();

        String[] items = listStr.split(";");

        for (String item : items) {
            String[] itemParts = item.trim().split(" ");
            if (itemParts.length == 2) {
                if (isQuantity(itemParts[0])) {
                    String name = itemParts[1];
                    int quantity = Integer.parseInt(itemParts[0]);

                    returnList.add(new ShoppingListItem(name, quantity));
                }
            }
        }

        return returnList;
    }

    /**
     * Converts list of items to a string representation of shopping list.
     *
     * @param list of items
     * @return String representation of a shopping list
     */
    public static String listToStr(MyLinkedList<ShoppingListItem> list) {
        String listAsString = "";

        for (int i = 0; i < list.size(); i++) {
            ShoppingListItem item = list.get(i);
            listAsString += item.getQuantity() + " " + item.getName() + ";";
        }

        return listAsString;
    }

    /**
     * Coverts list of items to an array of items.
     *
     * @param list of items
     * @return Array of items
     */
    public static ShoppingListItem[] listToArray(
            MyLinkedList<ShoppingListItem> list) {
        ShoppingListItem[] array = new ShoppingListItem[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        return array;
    }

    /**
     * Checks if given string can be converted to an integer and
     * is larger than 0.
     *
     * @param integerString String to check
     * @return If string contains valid quantity or not
     */
    private static boolean isQuantity(String integerString) {
        int quantity = 0;
        boolean valid = true;

        try {
            quantity = Integer.parseInt(integerString);

            if (quantity < 1) {
                valid = false;
            }
        } catch (Exception e) {
            valid = false;
        }

        return valid;
    }
}
