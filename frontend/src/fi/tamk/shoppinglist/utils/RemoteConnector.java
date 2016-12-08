package fi.tamk.shoppinglist.utils;

import fi.tamk.shoppinglist.ShoppingListItem;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 * Implements utilities to connect with remote shopping list server.
 *
 * @author Vili Kinnunen
 * @version 2016.1125
 * @since 1.8
 */
public class RemoteConnector {
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

    public static MyLinkedList<ShoppingListItem> getItems(String url) {
        try {
            Content content = Request.Get(url + "/items")
                    .execute().returnContent();

            return Tools.XMLToList(content.asString());
        } catch (Exception e) {
            return new MyLinkedList<>();
        }
    }

    public static boolean removeItems(String url) {
        try {
            Request.Delete(url + "/items").execute();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

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
