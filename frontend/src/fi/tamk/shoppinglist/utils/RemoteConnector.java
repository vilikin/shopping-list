package fi.tamk.shoppinglist.utils;

import fi.tamk.shoppinglist.ShoppingListItem;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

/**
 * Implements utilities to connect with remote shopping list server.
 *
 * @author Vili Kinnunen
 * @version 2016.1125
 * @since 1.8
 */
public class RemoteConnector {

    /**
     * Checks if specified URL can be connected to.
     *
     * @param url   URL to connect
     * @return      If connection was successful.
     */
    public static boolean tryConnect(String url) {
        // If URL has slash at the end, remove it.
        if (url.charAt(url.length() - 1) == '/') {
            url = url.substring(0, url.length() - 2);
        }

        try {
            Content content = Request.Get(url + "/items")
                    .execute().returnContent();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Gets all the items from remote list.
     *
     * @param url   Remote list URL
     * @return      Items
     */
    public static MyLinkedList<ShoppingListItem> getItems(String url) {
        try {
            Content content = Request.Get(url + "/items")
                    .execute().returnContent();

            return Tools.xmlToList(content.asString());
        } catch (Exception e) {
            return new MyLinkedList<>();
        }
    }

    /**
     * Removes items from remote list.
     *
     * @param url   Remote list URL
     * @return      If operation was successful
     */
    public static boolean removeItems(String url) {
        try {
            Request.Delete(url + "/items").execute();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Appends items to the remote list.
     *
     * @param url       Remote list URL
     * @param itemXML   XML representation of items to append
     * @return          If operation was successful
     */
    public static boolean appendItem(String url, String itemXML) {
        try {
            Request.Post(url + "/items").bodyString(itemXML,
                    ContentType.APPLICATION_XML).execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
