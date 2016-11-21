import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements list of items.
 *
 * @author Vili Kinnunen vili.kinnunen@cs.tamk.fi
 * @version 2016.1121
 * @since 1.8
 */
@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class Items {

    /**
     * List of items in the shopping list.
     */
    @XmlElement(name = "item")
    public List<Item> items;

    /**
     * Constructs object.
     */
    public Items() {

    }

    /**
     * Sets list of items.
     *
     * @param items List of shopping list items
     */
    public Items(List<Item> items) {
        this.items = items;
    }
}
