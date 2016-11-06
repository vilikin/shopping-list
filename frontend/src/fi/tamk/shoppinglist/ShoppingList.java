package fi.tamk.shoppinglist;

import fi.tamk.shoppinglist.utils.MyLinkedList;
import fi.tamk.shoppinglist.utils.MyList;

/**
 * Implements the core shopping list functions.
 *
 * @author Vili Kinnunen
 * @version 2016.1105
 * @since 1.8
 */
public class ShoppingList {

    /**
     * List containing all of the shopping list items
     */
    private MyLinkedList<ShoppingListItem> list;

    /**
     * Initializes empty shopping list.
     */
    public ShoppingList() {
        list = new MyLinkedList<>();
    }

    /**
     * Appends items from the given list to the end of shopping list.
     * If item with same name already exists in the shopping list,
     * quantity of the existing item is increased.
     *
     * @param appendList List of items to add to the shopping list
     */
    public void append(MyLinkedList<ShoppingListItem> appendList) {

        for (int i = 0; i < appendList.size(); i++) {
            ShoppingListItem appendItem = appendList.get(i);
            boolean addNew = true;

            for (int j = 0; j < list.size(); j++) {
                ShoppingListItem existingItem = list.get(j);
                if (existingItem.getName().equals(appendItem.getName())) {
                    existingItem.setQuantity(existingItem.getQuantity() +
                            appendItem.getQuantity());
                    addNew = false;
                }
            }

            if (addNew) {
                list.add(appendItem);
            }
        }
    }

    /**
     * Replaces current shopping list with a new list.
     *
     * @param newList List of items to replace the current shopping list with
     */
    public void replace(MyLinkedList<ShoppingListItem> newList) {
        list = newList;
    }

    /**
     * Gets current shopping list.
     *
     * @return Current shopping list
     */
    public MyLinkedList<ShoppingListItem> getList() {
        return list;
    }
}
