package fi.tamk.shoppinglist.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by vilik on 14.11.2016.
 */
public class LabelledInput extends JPanel {

    private JTextField inputField;

    public LabelledInput(String label, int width, int height) {
        setLayout(new BorderLayout());

        JLabel inputLabel = new JLabel(label);

        inputLabel.setPreferredSize(new Dimension(width, 15));
        inputField = new JTextField();

        inputField.setPreferredSize(new Dimension(width, height));

        add(inputLabel, BorderLayout.NORTH);
        add(inputField, BorderLayout.SOUTH);
    }

    public String getText() {
        return inputField.getText();
    }

    public void setText(String text) {
        inputField.setText(text);
    }

    public JTextField getInputField() {
        return inputField;
    }
}
