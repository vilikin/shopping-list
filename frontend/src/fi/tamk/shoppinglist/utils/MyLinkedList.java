package fi.tamk.shoppinglist.utils;

/**
 * Implements a better version of LinkedList.
 *
 * @author Vili Kinnunen
 * @version 2016.1011
 * @since 1.8
 */
public class MyLinkedList<T> implements MyList<T> {

    /**
     * First item of the list
     */
    private MyItem<T> first;

    /**
     * Last item of the list
     */
    private MyItem<T> last;

    /**
     * Number of items in the list
     */
    private int size;

    /**
     * Initializes an empty linked list.
     */
    public MyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * @see MyList#add(Object) add
     */
    @Override
    public void add(T e) {
        if (first == null) {
            first = new MyItem<T>(e);
            last = first;
        } else {
            MyItem<T> newItem = new MyItem<>(e);
            last.setNext(newItem);
            last = newItem;
        }

        size++;
    }

    /**
     * @see MyList#clear() clear
     */
    @Override
    public void clear() {
        first = null;
        size = 0;
    }

    /**
     * @see MyList#get(int) get
     */
    @Override
    public T get(int index) {
        if (size > index && index >= 0) {
            MyItem<T> temp = first;
            for (int i = 1; i <= index; i++) {
                temp = temp.getNext();
            }

            if (temp != null) {
                return temp.getElement();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * @see MyList#isEmpty() isEmpty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @see MyList#remove(int) remove
     */
    @Override
    public T remove(int index) {
        if (size > index && index >= 0) {
            MyItem<T> removed = first;
            MyItem<T> previous = null;

            for (int i = 1; i <= index; i++) {
                previous = removed;
                removed = removed.getNext();
            }

            T removedElement = removed.getElement();

            if (index + 1 < size && previous != null) {
                previous.setNext(removed.getNext());
            } else if (previous == null) {
                if (removed.getNext() != null) {
                    first = removed.getNext();
                } else {
                    first = null;
                }
            } else {
                previous.setNext(null);
            }

            size--;
            return removedElement;
        } else {
            return null;
        }
    }

    /**
     * @see MyList#remove(Object) remove
     */
    @Override
    public boolean remove(T o) {
        int removeIndex = -1;
        MyItem<T> temp = first;
        for (int i = 0; i < size; i++) {
            if (temp.getElement() == o) {
                removeIndex = i;
                break;
            }

            temp = temp.getNext();
        }

        return remove(removeIndex) != null;
    }

    /**
     * @see MyList#size() size
     */
    @Override
    public int size() {
        return size;
    }
}
