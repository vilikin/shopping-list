package fi.tamk.shoppinglist.gui;

import fi.tamk.shoppinglist.ShoppingList;
import fi.tamk.shoppinglist.ShoppingListItem;
import fi.tamk.shoppinglist.utils.Tools;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

/**
 * Implements action listener that handles adding items in GUI.
 *
 * @author Vili Kinnunen
 * @version 2016.1117
 * @since 1.8
 */
public class InputListener extends AbstractAction implements ActionListener {

    /**
     * Input field that contains name for the item.
     */
    private LabelledInput name;

    /**
     * Input field that contains quantity for the item.
     */
    private LabelledInput quantity;

    /**
     * Shopping list to interact with.
     */
    private ShoppingList sl;

    /**
     * Initializes action listener with input fields and shopping list.
     *
     * @param name      Input field that contains name for the item
     * @param quantity  Input field that contains quantity for the item
     * @param sl        Shopping list to interact with
     */
    public InputListener(LabelledInput name, LabelledInput quantity,
                         ShoppingList sl) {

        this.name = name;
        this.quantity = quantity;
        this.sl = sl;
    }

    /**
     * Adds new item to the shopping list if input is valid.
     *
     * If input is not valid, focuses the field that contained invalid input.
     *
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!name.getText().trim().isEmpty() &&
                Tools.isQuantity(quantity.getText())) {
            sl.append(new ShoppingListItem(name.getText(),
                    Integer.parseInt(quantity.getText())));

            name.setText("");
            quantity.setText("1");

            name.getInputField().requestFocus();
        } else if (!Tools.isQuantity(quantity.getText())) {
            quantity.getInputField().requestFocus();
            quantity.getInputField().selectAll();
        } else {
            name.getInputField().requestFocus();
        }
    }
}
