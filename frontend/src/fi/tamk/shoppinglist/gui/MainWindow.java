package fi.tamk.shoppinglist.gui;

import fi.tamk.shoppinglist.ShoppingList;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Implements main window of GUI.
 *
 * @author Vili Kinnunen
 * @version 2016.1117
 * @since 1.8
 */
public class MainWindow extends JFrame {

    /**
     * Shopping list to interact with
     */
    private ShoppingList sl;

    /**
     * Model for the JTable
     */
    private AbstractTableModel model;

    /**
     * Table that displays the shopping list
     */
    private JTable table;

    /**
     * Creates main window.
     *
     * @param sl Shopping list to interact with
     */
    public MainWindow(ShoppingList sl) {
        this.sl = sl;

        Dimension windowSize = new Dimension(500, 400);

        setSize(windowSize);

        setLocationRelativeTo(null);
        setTitle("Shopping list");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        /* ------------- ADD MAIN MENU ------------------------------------ */

        MyMenuBar menu = new MyMenuBar(sl);

        setJMenuBar(menu);


        /* ------------- ADD TABLE WITH SHOPPING LIST CONTENT ------------- */
        TableModel dataModel = new MyTableModel(sl);

        table = new JTable(dataModel);

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);

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

        quantityInput.setText("1");

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

        InputMap panelInputMap = inputPanel.getInputMap(
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        ActionMap panelActionMap = inputPanel.getActionMap();

        panelInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                "add_item");

        panelActionMap.put("add_item", new InputListener(nameInput,
                quantityInput, sl));


        add(inputPanel, BorderLayout.SOUTH);
        pack();

        setVisible(true);

        nameInput.getInputField().requestFocus();
    }

    /**
     * Updates tables contents from the shopping list.
     */
    public void dataChanged() {
        table.updateUI();
        table.getRowSorter().allRowsChanged();
    }
}
