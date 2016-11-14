package fi.tamk.shoppinglist.gui;

import fi.tamk.shoppinglist.ShoppingList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

/**
 * Created by vilik on 14.11.2016.
 */
public class MainWindow extends JFrame {
    private ShoppingList sl;
    private AbstractTableModel model;
    private JTable table;

    public MainWindow(ShoppingList sl) {
        this.sl = sl;

        Dimension windowSize = new Dimension(500, 400);

        setSize(windowSize);

        setLocationRelativeTo(null);
        setTitle("Shopping list");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        AbstractTableModel dataModel = new MyTableModel(sl);

        table = new JTable(dataModel);
        JScrollPane scrollpane = new JScrollPane(table);

        scrollpane.setPreferredSize(windowSize);

        add(scrollpane);

        pack();

        setVisible(true);
    }

    public void dataChanged() {
        table.updateUI();
    }
}
