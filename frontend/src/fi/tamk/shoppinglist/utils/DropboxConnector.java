package fi.tamk.shoppinglist.utils;

import com.dropbox.core.*;
import fi.tamk.shoppinglist.ShoppingList;

import java.io.File;
import java.net.URI;
import java.util.Locale;

/**
 * Created by vilik on 15.11.2016.
 */
public class DropboxConnector {
    final String APP_KEY = "narweyk4dkhonm9";
    final String APP_SECRET = "h0vbueln4oknyg7";

    private ShoppingList sl;

    private DbxAppInfo appInfo;
    private DbxRequestConfig config;
    private DbxWebAuthNoRedirect webAuth;

    private DbxClient client;

    private String accessToken;

    public DropboxConnector(ShoppingList sl) {
        this.sl = sl;
        appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
        config = new DbxRequestConfig(
                "ShoppingList/1.0", Locale.getDefault().toString());

        try {
            accessToken = FileHandler.read(new File("dbauth.txt"));
            client = new DbxClient(config, accessToken);
        } catch (Exception e) {
            FileHandler.write(new File("dbauth.txt"), "");
        }
    }

    public boolean startConnect() {
        try {
            webAuth = new DbxWebAuthNoRedirect(config,
                    appInfo);

            String authorizeUrl = webAuth.start();
            java.awt.Desktop.getDesktop().browse(new URI(authorizeUrl));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean finishConnect(String key) {
        try {
            DbxAuthFinish authFinish = webAuth.finish(key);
            accessToken = authFinish.accessToken;
            client = new DbxClient(config, accessToken);
            FileHandler.write(new File("dbauth.txt"), accessToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isConnected() {
        return client != null;
    }
}
