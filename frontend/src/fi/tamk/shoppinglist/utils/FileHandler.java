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
 * Created by vilik on 5.11.2016.
 */
public class FileHandler {

    private String filename;

    public FileHandler(String filename) {
        this.filename = filename;
    }

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

    public void write(String content) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)));) {
            writer.print(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
