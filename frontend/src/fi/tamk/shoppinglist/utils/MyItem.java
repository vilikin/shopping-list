package fi.tamk.shoppinglist.utils;

/**
 * Implements an item that is part of MyLinkedList.
 *
 * @author Vili Kinnunen
 * @version 2016.1011
 * @since 1.8
 */
public class MyItem<T> {

    /**
     * Element of the item.
     */
    private T element;

    /**
     * Next item.
     */
    private MyItem<T> next;

    /**
     * Initializes item with element. Sets next item as null.
     *
     * @param element   Element of this item
     */
    public MyItem(T element) {
        this.element = element;
        next = null;
    }

    /**
     * Gets element of this item.
     *
     * @return Element of this item
     */
    public T getElement() {
        return element;
    }

    /**
     * Returns next element.
     *
     * @return Next element
     */
    public MyItem<T> getNext() {
        return next;
    }

    /**
     * Sets next item in the list.
     *
     * @param next  Next item
     */
    public void setNext(MyItem<T> next) {
        this.next = next;
    }
}
