import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by vilik on 26.10.2016.
 */

@XmlRootElement
public class Item {
    private String name;
    private int quantity;

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
