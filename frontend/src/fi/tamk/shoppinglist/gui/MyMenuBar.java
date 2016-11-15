package fi.tamk.shoppinglist.gui;

import fi.tamk.shoppinglist.ShoppingList;
import fi.tamk.shoppinglist.ShoppingListItem;
import fi.tamk.shoppinglist.utils.FileHandler;
import fi.tamk.shoppinglist.utils.MyLinkedList;
import fi.tamk.shoppinglist.utils.Tools;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * Created by vilik on 15.11.2016.
 */
public class MyMenuBar extends JMenuBar {
    private ShoppingList sl;

    public MyMenuBar(ShoppingList sl) {
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
            int action = fc.showOpenDialog(this);

            if (action == JFileChooser.APPROVE_OPTION) {
                String rawContent = FileHandler.read(fc.getSelectedFile());

                sl.replace(Tools.strToList(rawContent));
            }
        });

        fileMenu.add(open);

        JMenuItem save = new JMenuItem("Save list to a file",
                KeyEvent.VK_S);

        save.addActionListener((e) -> {
            int action = fc.showSaveDialog(this);

            if (action == JFileChooser.APPROVE_OPTION) {
                String content = Tools.listToStr(sl.getList());
                FileHandler.write(fc.getSelectedFile(), content);
            }
        });

        fileMenu.add(save);

        JMenuItem combine = new JMenuItem("Combine with list from a file",
                KeyEvent.VK_C);

        combine.addActionListener((e) -> {
            int action = fc.showOpenDialog(this);

            if (action == JFileChooser.APPROVE_OPTION) {
                String rawContent = FileHandler.read(fc.getSelectedFile());

                sl.append(Tools.strToList(rawContent));
            }
        });

        fileMenu.add(combine);

        fileMenu.addSeparator();
    }
}
