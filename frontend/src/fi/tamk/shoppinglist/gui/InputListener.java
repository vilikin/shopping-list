package fi.tamk.shoppinglist.gui;

import fi.tamk.shoppinglist.ShoppingList;
import fi.tamk.shoppinglist.ShoppingListItem;
import fi.tamk.shoppinglist.utils.Tools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

/**
 * Created by vilik on 14.11.2016.
 */
public class InputListener extends AbstractAction implements ActionListener {

    private LabelledInput name;
    private LabelledInput quantity;
    private ShoppingList sl;

    public InputListener(LabelledInput name, LabelledInput quantity,
                         ShoppingList sl) {

        this.name = name;
        this.quantity = quantity;
        this.sl = sl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!name.getText().trim().isEmpty() &&
                Tools.isQuantity(quantity.getText())) {
            sl.append(new ShoppingListItem(name.getText(),
                    Integer.parseInt(quantity.getText())));

            name.setText("");
            quantity.setText("");

            name.getInputField().requestFocus();
        } else if (!Tools.isQuantity(quantity.getText())) {
            quantity.getInputField().requestFocus();
            quantity.getInputField().selectAll();
        } else {
            name.getInputField().requestFocus();
        }
    }
}
