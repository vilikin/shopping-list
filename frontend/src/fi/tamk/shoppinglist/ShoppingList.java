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
    private MyLinkedList<ShoppingListItem> list;

    public ShoppingList() {
        list = new MyLinkedList<>();
    }

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

    public void replace(MyLinkedList<ShoppingListItem> newList) {
        list = newList;
    }

    public MyLinkedList<ShoppingListItem> getList() {
        return list;
    }
}
