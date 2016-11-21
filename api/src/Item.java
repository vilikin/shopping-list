import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Implements shopping list item.
 *
 * @author Vili Kinnunen vili.kinnunen@cs.tamk.fi
 * @version 2016.1121
 * @since 1.8
 */
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {

    /**
     * Name of the item.
     */
    private String name;

    /**
     * Quantity of the item.
     */
    private int quantity;

    /**
     * Constructs object.
     */
    public Item() {

    }

    /**
     * Initializes item with name and quantity.
     *
     * @param name      Name of the item
     * @param quantity  Quantity of the item
     */
    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    /**
     * Checks that quantity is larger than 0 and name isn't empty.
     *
     * @return  True if valid, false if not
     */
    public boolean isValid() {
        return name != null && !name.trim().isEmpty() && quantity > 0;
    }

    /**
     * Gets name of the item.
     *
     * @return  Name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name for the item.
     *
     * @param name  Name for the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets quantity of the item.
     *
     * @return  Quantity of the item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity for the item.
     *
     * @param quantity  Quantity for the item
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
