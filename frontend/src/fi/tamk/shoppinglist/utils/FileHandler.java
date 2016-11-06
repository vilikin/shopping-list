package fi.tamk.shoppinglist.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Implements simplified methods to handle a file.
 *
 * @author Vili Kinnunen
 * @version 2016.1105
 * @since 1.8
 */
public class FileHandler {

    /**
     * Name of the file
     */
    private String filename;

    /**
     * Initializes FileHandler to interact with specified file.
     *
     * @param filename Name of the file to interact with
     */
    public FileHandler(String filename) {
        this.filename = filename;
    }

    /**
     * Reads contents of the file.
     *
     * @return All lines of the file in one single string
     */
    public String read() {
        Path filepath = Paths.get(filename);
        String content = "";

        try {
            List<String> lines = Files.readAllLines(filepath);
            for (String line : lines) {
                content += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    /**
     * Replaces file content with the given string.
     *
     * @param content New content of the file
     */
    public void write(String content) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)));) {
            writer.print(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
