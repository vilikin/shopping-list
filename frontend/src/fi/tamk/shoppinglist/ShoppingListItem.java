package fi.tamk.shoppinglist;

/**
 * Implements shopping list item.
 *
 * @author Vili Kinnunen
 * @version 2016.1105
 * @since 1.8
 */
public class ShoppingListItem {

    /**
     * Name of the item.
     */
    private String name;

    /**
     * Quantity of the item.
     */
    private int quantity;

    /**
     * Initializes shopping list item with name and quantity.
     *
     * @param name Name for the item
     * @param quantity Quantity for the item
     */
    public ShoppingListItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    /**
     * Gets name of the item.
     *
     * @return Name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of the item.
     *
     * @param name Name for the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets quantity of the item.
     *
     * @return Quantity of the item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity of the item.
     *
     * @param quantity Quantity for the item
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
