package fi.tamk.shoppinglist.gui;

import fi.tamk.shoppinglist.ShoppingList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

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

        setLayout(new BorderLayout());


        /* ------------- ADD TABLE WITH SHOPPING LIST CONTENT ------------- */
        AbstractTableModel dataModel = new MyTableModel(sl);

        table = new JTable(dataModel);
        InputMap inputMap = table.getInputMap(
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        ActionMap actionMap = table.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0),
                "delete_row");

        actionMap.put("delete_row", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (!table.isEditing()) {
                    sl.deleteItems(table.getSelectedRows());
                }
            }
        });

        JScrollPane scrollpane = new JScrollPane(table);

        scrollpane.setPreferredSize(windowSize);

        add(scrollpane, BorderLayout.NORTH);

        /* ------------- ADD INPUT BAR ------------------------------------ */

        JPanel inputPanel = new JPanel(new FlowLayout());

        int inputWidth = (int)(windowSize.width * 0.4);
        LabelledInput nameInput = new LabelledInput("Item", inputWidth, 30);
        LabelledInput quantityInput = new LabelledInput("Quantity",
                inputWidth, 30);

        JPanel btnPanel = new JPanel(new BorderLayout());

        JLabel emptyLabel = new JLabel();
        emptyLabel.setPreferredSize(new Dimension(40, 15));

        JButton inputBtn = new JButton("Add");
        inputBtn.setPreferredSize(new Dimension(60, 29));

        inputBtn.addActionListener(new InputListener(nameInput, quantityInput, sl));

        btnPanel.add(emptyLabel, BorderLayout.NORTH);
        btnPanel.add(inputBtn, BorderLayout.SOUTH);


        inputPanel.add(nameInput);
        inputPanel.add(quantityInput);
        inputPanel.add(btnPanel);

        add(inputPanel, BorderLayout.SOUTH);
        pack();

        setVisible(true);
    }

    public void dataChanged() {
        table.updateUI();
    }
}
