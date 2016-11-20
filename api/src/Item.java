import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by vilik on 20.11.2016.
 */
@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
    private String name;
    private int quantity;

    public Item() {

    }

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public boolean isValid() {
        return name != null && !name.trim().isEmpty() && quantity > 0;
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
