package fi.tamk.shoppinglist;

import fi.tamk.shoppinglist.gui.MainWindow;
import fi.tamk.shoppinglist.utils.MyLinkedList;
import fi.tamk.shoppinglist.utils.RemoteConnector;
import fi.tamk.shoppinglist.utils.Tools;

import javax.swing.*;
import java.rmi.Remote;

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

    private String remoteUrl;

    private boolean allowRemoteUpdates;

    /**
     * Initializes empty shopping list.
     */
    public ShoppingList() {
        list = new MyLinkedList<>();
    }

    /**
     * Appends items from the given list to the end of shopping list.
     * <p>
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

        update();
    }

    /**
     * Appends one item to the end of shopping list.
     * <p>
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

        update();
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

        update();
    }

    /**
     * Changes items quantity in the shopping list.
     *
     * @param index    Index of the item
     * @param quantity New quantity for the item
     */
    public void updateItemQuantity(int index, int quantity) {
        list.get(index).setQuantity(quantity);

        update();
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

        update();
    }

    /**
     * Sets remote URL that the list is synced with.
     *
     * @param url Remote URL or null to disable syncing
     */
    public void setRemoteUrl(String url) {
        remoteUrl = url;

        if (remoteUrl != null) {
            allowRemoteUpdates = true;
            new Thread(new RemoteUpdater(this)).start();
        }
    }

    /**
     * Gets remote URL that the list is synced with.
     *
     * @return Remote URL or null if sync is disabled
     */
    public String getRemoteUrl() {
        return remoteUrl;
    }

    /**
     * Syncs local list with the remote list.
     */
    public void getListFromServer() {
        if (remoteUrl != null && allowRemoteUpdates) {
            list = RemoteConnector.getItems(remoteUrl);
        }

        if (window != null) {
            window.dataChanged();
        }
    }

    /**
     * Updates GUI and remote list when available.
     */
    public void update() {
        if (window != null) {
            window.dataChanged();
        }

        if (remoteUrl != null) {
            new Thread(() -> {

                allowRemoteUpdates = false;
                boolean r = RemoteConnector.removeItems(remoteUrl);
                boolean a = true;



                for (int i = 0; i < list.size(); i++) {
                    if (!RemoteConnector.appendItem(remoteUrl,
                            Tools.itemToXML(list.get(i)))) {
                        a = false;
                    }
                }

                if (!r || !a) {
                    JOptionPane.showMessageDialog(null,
                            "Server error occured!");
                }

                allowRemoteUpdates = true;
            }).start();
        }
    }
}

/**
 * Implements thread that keeps syncing local list with the remote one.
 */
class RemoteUpdater implements Runnable {

    /**
     * Local shopping list.
     */
    private ShoppingList sl;

    /**
     * Initializes remote updater with the local shopping list.
     *
     * @param sl Local shopping list.
     */
    public RemoteUpdater(ShoppingList sl) {
        this.sl = sl;
    }

    /**
     * Keeps syncing local list with the remote one.
     */
    public void run() {
        while (sl.getRemoteUrl() != null) {
            sl.getListFromServer();

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}