package fi.tamk.shoppinglist.utils;

import fi.tamk.shoppinglist.ShoppingListItem;

/**
 * Created by vilik on 5.11.2016.
 */
public class Tools {
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

    public static String listToStr(MyLinkedList<ShoppingListItem> list) {
        String listAsString = "";

        for (int i = 0; i < list.size(); i++) {
            ShoppingListItem item = list.get(i);
            listAsString += item.getQuantity() + " " + item.getName() + ";";
        }

        return listAsString;
    }

    public static ShoppingListItem[] listToArray(
            MyLinkedList<ShoppingListItem> list) {
        ShoppingListItem[] array = new ShoppingListItem[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        return array;
    }

    private static boolean isQuantity(String integerString) {
        int quantity = 0;
        boolean valid = true;

        try {
            quantity = Integer.parseInt(integerString);

            if (quantity < 0) {
                valid = false;
            }
        } catch (Exception e) {
            valid = false;
        }

        return valid;
    }
}
