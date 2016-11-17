package fi.tamk.shoppinglist.utils;

import com.dropbox.core.*;
import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * Handles connection to Dropbox.
 *
 * @author Vili Kinnunen
 * @version 2016.1117
 * @since 1.8
 */
public class DropboxConnector {

    /**
     * Dropbox app info
     */
    private DbxAppInfo appInfo;

    /**
     * Config for Dropbox requests
     */
    private DbxRequestConfig config;

    /**
     * Dropbox web auth
     */
    private DbxWebAuthNoRedirect webAuth;

    /**
     * Dropbox client
     */
    private DbxClient client;

    /**
     * Access token for Dropbox
     */
    private String accessToken;

    /**
     * Initializes Dropbox connector.
     *
     * Tries to read access token from file. If token exists, initializes
     * client. If not, creates empty file for the token.
     */
    public DropboxConnector() {
        String APP_KEY = "narweyk4dkhonm9";
        String APP_SECRET = "h0vbueln4oknyg7";

        appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);
        config = new DbxRequestConfig(
                "ShoppingList/1.0", Locale.getDefault().toString());

        boolean gotToken = true;

        try {
            accessToken = FileHandler.read(new File("dbauth.txt"));
            client = new DbxClient(config, accessToken);
        } catch (Exception e) {
            gotToken = false;
        }

        if (!gotToken) {
            try {
                FileHandler.write(new File("dbauth.txt"), "");
            } catch (Exception e) {

            }
        }
    }

    /**
     * Opens authentication page and prompts user for key.
     *
     * @return If process started without errors
     */
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

    /**
     * Finishes connection process.
     *
     * @param key   Key from the authentication page
     * @return      If authentication was completed
     */
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

    /**
     * Lists all files in apps Dropbox folder.
     *
     * @return  Array of filenames in apps Dropbox folder
     */
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

    /**
     * Reads content of specified file from Dropbox.
     *
     * @param filename  File to read
     * @return          Content of the file
     */
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

    /**
     * Saves file to Dropbox.
     *
     * @param filename      Name for the file
     * @param fileContent   Content of the file
     * @return              If file was saved
     */
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

    /**
     * Gets status of the connection.
     *
     * @return  If app is connected to Dropbox
     */
    public boolean isConnected() {
        return client != null && !client.getAccessToken().equals("");
    }
}
