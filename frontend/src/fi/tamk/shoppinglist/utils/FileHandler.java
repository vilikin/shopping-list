package fi.tamk.shoppinglist.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Implements simple methods to handle a file.
 *
 * @author Vili Kinnunen
 * @version 2016.1105
 * @since 1.8
 */
public class FileHandler {

    /**
     * Reads contents of the file.
     *
     * @return All lines of the file in one single string
     */
    public static String read(File file) throws IOException {
        String content = "";

        List<String> lines = Files.readAllLines(file.toPath());
        for (String line : lines) {
            content += line;
        }

        return content;
    }

    /**
     * Replaces file content with the given string.
     *
     * @param content New content of the file
     */
    public static void write(File file, String content) throws IOException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            writer.print(content);
        }
    }
}
