package fi.tamk.shoppinglist;

/**
 * Implements shopping list item.
 *
 * @author Vili Kinnunen
 * @version 2016.11.5
 * @since 1.8
 */
public class ShoppingListItem {
    private String name;
    private int quantity;

    public ShoppingListItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
