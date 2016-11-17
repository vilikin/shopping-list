package fi.tamk.shoppinglist.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Implements JPanel that is combination of input field and its label.
 *
 * @author Vili Kinnunen
 * @version 2016.1117
 * @since 1.8
 */
public class LabelledInput extends JPanel {

    /**
     * Input field of the component.
     */
    private JTextField inputField;

    /**
     * Initializes component.
     *
     * @param label     Label for the text field
     * @param width     Width of the component
     * @param height    Height of the component
     */
    public LabelledInput(String label, int width, int height) {
        setLayout(new BorderLayout());

        JLabel inputLabel = new JLabel(label);

        inputLabel.setPreferredSize(new Dimension(width, 15));
        inputField = new JTextField();

        inputField.setPreferredSize(new Dimension(width, height));

        add(inputLabel, BorderLayout.NORTH);
        add(inputField, BorderLayout.SOUTH);
    }

    /**
     * Gets input text.
     *
     * @return Text of the input field
     */
    public String getText() {
        return inputField.getText();
    }

    /**
     * Sets input text.
     *
     * @param text  Text for the input field
     */
    public void setText(String text) {
        inputField.setText(text);
    }

    /**
     * Gets input field of the component.
     *
     * @return  Input field
     */
    public JTextField getInputField() {
        return inputField;
    }
}
