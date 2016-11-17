package fi.tamk.shoppinglist;

import fi.tamk.shoppinglist.gui.MainWindow;
import fi.tamk.shoppinglist.utils.MyLinkedList;

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
     * GUI that interacts with the shopping list
     */
    private MainWindow window;

    /**
     * Initializes empty shopping list.
     */
    public ShoppingList() {
        list = new MyLinkedList<>();
    }

    /**
     * Appends items from the given list to the end of shopping list.
     *
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

        updateTable();
    }

    /**
     * Appends one item to the end of shopping list.
     *
     * If item with same name already exists in the shopping list,
     * quantity of the existing item is increased.
     *
     * @param appendItem Item to append to the list
     */
    public void append(ShoppingListItem appendItem) {
        MyLinkedList<ShoppingListItem> tmpList = new MyLinkedList<>();
        tmpList.add(appendItem);
        append(tmpList);
    }

    /**
     * Replaces current shopping list with a new list.
     *
     * @param newList List of items to replace the current shopping list with
     */
    public void replace(MyLinkedList<ShoppingListItem> newList) {
        list = newList;

        updateTable();
    }

    /**
     * Gets current shopping list.
     *
     * @return Current shopping list
     */
    public MyLinkedList<ShoppingListItem> getList() {
        return list;
    }

    /**
     * Sets shopping list to interact with GUI.
     *
     * @param window GUI to interact with
     */
    public void setWindow(MainWindow window) {
        this.window = window;
    }

    /**
     * Changes items name in the shopping list.
     *
     * @param index Index of the item
     * @param name  New name for the item
     */
    public void updateItemName(int index, String name) {
        list.get(index).setName(name);

        updateTable();
    }

    /**
     * Changes items quantity in the shopping list.
     *
     * @param index     Index of the item
     * @param quantity  New quantity for the item
     */
    public void updateItemQuantity(int index, int quantity) {
        list.get(index).setQuantity(quantity);

        updateTable();
    }

    /**
     * Deletes specified items from the shopping list.
     *
     * @param indexes Indexes of all the items to remove from the shopping list.
     */
    public void deleteItems(int[] indexes) {
        ShoppingListItem[] removeItems = new ShoppingListItem[indexes.length];

        for (int i = 0; i < indexes.length; i++) {
            removeItems[i] = list.get(indexes[i]);
        }

        for (ShoppingListItem removeItem : removeItems) {
            list.remove(removeItem);
        }

        updateTable();
    }

    /**
     * Updates GUI.
     */
    public void updateTable() {
        if (window != null) {
            window.dataChanged();
        }
    }
}
