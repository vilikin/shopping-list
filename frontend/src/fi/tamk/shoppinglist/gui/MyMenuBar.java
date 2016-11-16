package fi.tamk.shoppinglist.gui;

import fi.tamk.shoppinglist.ShoppingList;
import fi.tamk.shoppinglist.ShoppingListItem;
import fi.tamk.shoppinglist.utils.DropboxConnector;
import fi.tamk.shoppinglist.utils.FileHandler;
import fi.tamk.shoppinglist.utils.MyLinkedList;
import fi.tamk.shoppinglist.utils.Tools;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.File;

/**
 * Created by vilik on 15.11.2016.
 */
public class MyMenuBar extends JMenuBar {
    public MyMenuBar(ShoppingList sl) {
        DropboxConnector dbx = new DropboxConnector();

        final JFileChooser fc = new JFileChooser();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        add(fileMenu);

        JMenuItem newlist = new JMenuItem("New list");
        newlist.addActionListener((e) -> {
            sl.replace(new MyLinkedList<ShoppingListItem>());
        });

        fileMenu.add(newlist);

        JMenuItem open = new JMenuItem("Open list from a file",
                KeyEvent.VK_O);

        open.addActionListener((e) -> {
            int action = fc.showOpenDialog(this.getParent());

            if (action == JFileChooser.APPROVE_OPTION) {
                String rawContent = FileHandler.read(fc.getSelectedFile());

                sl.replace(Tools.strToList(rawContent));
            }
        });

        fileMenu.add(open);

        JMenuItem save = new JMenuItem("Save list to a file",
                KeyEvent.VK_S);

        save.addActionListener((e) -> {
            int action = fc.showSaveDialog(this.getParent());

            if (action == JFileChooser.APPROVE_OPTION) {
                String content = Tools.listToStr(sl.getList());
                FileHandler.write(fc.getSelectedFile(), content);
            }
        });

        fileMenu.add(save);

        JMenuItem combine = new JMenuItem("Combine with list from a file",
                KeyEvent.VK_C);

        combine.addActionListener((e) -> {
            int action = fc.showOpenDialog(this.getParent());

            if (action == JFileChooser.APPROVE_OPTION) {
                String rawContent = FileHandler.read(fc.getSelectedFile());

                sl.append(Tools.strToList(rawContent));
            }
        });

        fileMenu.add(combine);

        fileMenu.addSeparator();

        JMenuItem dbConnect = new JMenuItem("Connect with DropBox",
                KeyEvent.VK_D);

        dbConnect.addActionListener((e) -> {
            if (dbx.startConnect()) {
                String key = JOptionPane.showInputDialog(this.getParent(), "Enter key:");
                if (dbx.finishConnect(key)) {
                    JOptionPane.showMessageDialog(this.getParent(), "Connected!");
                }
            }
        });

        fileMenu.add(dbConnect);

        JMenuItem dbOpen = new JMenuItem("Open from Dropbox",
                KeyEvent.VK_P);

        dbOpen.addActionListener((e) -> {
            String[] choices = dbx.getFiles();
            if (choices.length > 0) {
                String input = (String) JOptionPane.showInputDialog(
                        this.getParent(),
                        "Select file to open:",
                        "Open from Dropbox", JOptionPane.QUESTION_MESSAGE, null,
                        choices,
                        choices[0]);

                String fileContent = dbx.getContent(input);

                sl.replace(Tools.strToList(fileContent));
            } else {
                JOptionPane.showMessageDialog(this.getParent(),
                        "There are no files in your list directory!",
                        "Open from Dropbox", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        fileMenu.add(dbOpen);

        JMenuItem dbSave = new JMenuItem("Save to Dropbox",
                KeyEvent.VK_T);

        dbSave.addActionListener((e) -> {
            String fileName = JOptionPane.showInputDialog(
                    this.getParent(), "Save file as");

            String fileContent = Tools.listToStr(sl.getList());

            if (dbx.saveFile(fileName, fileContent)) {
                JOptionPane.showMessageDialog(this.getParent(),
                        "File saved succesfully!");
            } else {
                JOptionPane.showMessageDialog(this.getParent(),
                        "There was an error while saving the file!");
            }
        });

        fileMenu.add(dbSave);
    }
}
