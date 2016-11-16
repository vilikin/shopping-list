package fi.tamk.shoppinglist.utils;

import com.dropbox.core.*;
import fi.tamk.shoppinglist.ShoppingList;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * Created by vilik on 15.11.2016.
 */
public class DropboxConnector {

    private DbxAppInfo appInfo;
    private DbxRequestConfig config;
    private DbxWebAuthNoRedirect webAuth;

    private DbxClient client;

    private String accessToken;

    public DropboxConnector() {
        String APP_KEY = "narweyk4dkhonm9";
        String APP_SECRET = "h0vbueln4oknyg7";

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

    public String[] getFiles() {
        if (isConnected()) {
            try {
                DbxEntry.WithChildren listing =
                        client.getMetadataWithChildren("/");

                String[] files = new String[listing.children.size()];

                for (int i = 0; i < listing.children.size(); i++) {
                    files[i] = listing.children.get(i).name;
                }

                return files;
            } catch (Exception e) {
                return new String[0];
            }
        } else {
            return new String[0];
        }
    }

    public String getContent(String filename) {
        if (isConnected()) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                try {
                    DbxEntry.File downloadedFile = client.getFile(
                            "/" + filename, null, baos);
                    return baos.toString();
                } finally {
                    baos.close();
                }
            } catch (Exception e) {
                return "";
            }
        } else {
            return "";
        }
    }

    public boolean saveFile(String filename, String fileContent) {
        if (isConnected()) {
            try (InputStream stream = new ByteArrayInputStream(
                    fileContent.getBytes(StandardCharsets.UTF_8))) {
                client.uploadFile("/" + filename, DbxWriteMode.force(),
                        fileContent.getBytes(StandardCharsets.UTF_8).length,
                        stream);

                stream.close();

                return true;
            } catch (Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean isConnected() {
        return !client.getAccessToken().equals("");
    }
}
