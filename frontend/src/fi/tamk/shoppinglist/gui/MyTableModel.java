package fi.tamk.shoppinglist.gui;

import fi.tamk.shoppinglist.ShoppingList;
import fi.tamk.shoppinglist.utils.Tools;

import javax.swing.table.AbstractTableModel;

/**
 * Created by vilik on 14.11.2016.
 */
public class MyTableModel extends AbstractTableModel {

    ShoppingList sl;

    public MyTableModel(ShoppingList sl) {
        this.sl = sl;
    }
    public String getColumnName(int col) {
        return col == 0 ? "Item" : "Quantity";
    }

    public boolean isCellEditable(int row, int col) {
        return true;
    }

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

    public int getColumnCount() {
        return 2;
    }

    public int getRowCount() {
        return sl.getList().size();
    }

    public Object getValueAt(int row, int col) {
        switch (col) {
            case 0:
                return sl.getList().get(row).getName();
            case 1:
                return sl.getList().get(row).getQuantity();
            default:
                return "error";
        }
    }
}
