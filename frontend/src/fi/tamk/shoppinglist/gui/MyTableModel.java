package fi.tamk.shoppinglist.gui;

import fi.tamk.shoppinglist.ShoppingList;
import fi.tamk.shoppinglist.ShoppingListItem;
import fi.tamk.shoppinglist.utils.Tools;

import javax.swing.table.AbstractTableModel;

/**
 * Implements table model of the shopping list.
 *
 * @author Vili Kinnunen
 * @version 2016.1117
 * @since 1.8
 */
public class MyTableModel extends AbstractTableModel {

    /**
     * Shopping list that the model is based on
     */
    ShoppingList sl;

    /**
     * Initializes model to interact with shopping list.
     *
     * @param sl Shopping list to interact with
     */
    public MyTableModel(ShoppingList sl) {
        this.sl = sl;
    }

    /**
     * Gets column name based on column index.
     *
     * @param col   Column index
     * @return      Column name
     */
    public String getColumnName(int col) {
        return col == 0 ? "Item" : "Quantity";
    }

    /**
     * Checks if specified cell is editable.
     *
     * @param row   Row
     * @param col   Column
     * @return      If cell is editable or not
     */
    public boolean isCellEditable(int row, int col) {
        return true;
    }

    /**
     * Updates shopping list if cell is edited.
     *
     * @param o     Value of the cell
     * @param row   Row
     * @param col   Column
     */
    public void setValueAt(Object o, int row, int col) {
        switch (col) {
            case 0:
                sl.updateItemName(row, (String)o);
                break;
            case 1:
                if (Tools.isQuantity((String)o)) {
                    sl.updateItemQuantity(row, Integer.parseInt((String)o));
                }
                break;
        }
    }

    /**
     * Gets column count for the table.
     *
     * @return  Amount of columns in the table
     */
    public int getColumnCount() {
        return 2;
    }

    /**
     * Gets row count for the table.
     * @return  Amount of items in the shopping list
     */
    public int getRowCount() {
        return sl.getList().size();
    }

    /**
     * Gets value for specified cell of the table.
     *
     * @param row   Row
     * @param col   Column
     * @return      Item name or quantity
     */
    public Object getValueAt(int row, int col) {
        ShoppingListItem item = sl.getList().get(row);
        switch (col) {
            case 0:
                return item.getName();

            case 1:
                return item.getQuantity();

            default:
                return "error";
        }
    }
}
