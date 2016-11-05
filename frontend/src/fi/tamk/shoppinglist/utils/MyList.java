package fi.tamk.shoppinglist.utils;

/**
 * Implements an interface for superior lists.
 * @param <T>   Type of objects the list contains
 */
public interface MyList<T> {

    /**
     * Appends the specified element to the end of this list.
     *
     * @param e Element to be added to the list
     */
    void add(T e);

    /**
     * Removes all elements from the list.
     */
    void clear();

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index     Position of the element
     * @return          Element that was found at the specified position
     */
    T get(int index);

    /**
     * Returns true if this list contains no elements.
     *
     * @return  If list is empty or not
     */
    boolean isEmpty();

    /**
     * Removes the element at the specified position in this list. Returns the removed element.
     *
     * @param index     Position of the element to be removed
     * @return          Removed element
     */
    T remove(int index);

    /**
     * Removes the first occurrence of the specified element from this list.
     *
     * @param o     Element to be removed from the list
     * @return      If the element was found from the list
     */
    boolean remove(T o);

    /**
     * Returns the number of elements in this list.
     *
     * @return  Number of elements in this list
     */
    int size();
}
