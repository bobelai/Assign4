import java.util.Comparator;
import java.util.ListIterator;

/**
 * a sorted double-linked list that extends basicdoublelinkedlist
 * elements are added in sorted order using a comparator
 * @ Leul Belay
 * @param <T> the type of elements in the list
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

    private Comparator<T> comparator;

    /**
     * constructor that creates a sorted list with a given comparator
     *
     * @param comparator the comparator used to sort elements
     */
    public SortedDoubleLinkedList(Comparator<T> comparator) {
        super();
        this.comparator = comparator;
    }

    /**
     * adds an element to the sorted list in the correct spot
     *
     * @param data the value to add
     */
    public void add(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            // list is empty
            head = newNode;
            tail = newNode;
        } else {
            Node current = head;
            while (current != null && comparator.compare(current.data, data) < 0) {
                // keep going to find the right spot
                current = current.next;
            }
            if (current == head) {
                // insert at the beginning
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            } else if (current == null) {
                // insert at the end
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            } else {
                // insert in the middle
                newNode.prev = current.prev;
                newNode.next = current;
                current.prev.next = newNode;
                current.prev = newNode;
            }
        }
        size++;
    }

    /**
     * throws an exception when trying to add to the front of the list
     *
     * @param data the value to add
     * @throws UnsupportedOperationException
     */
    @Override
    public void addToFront(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    /**
     * throws an exception when trying to add to the end of the list
     *
     * @param data the value to add
     * @throws UnsupportedOperationException
     */
    @Override
    public void addToEnd(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    /**
     * returns an iterator for the sorted list
     *
     * @return a listiterator for the list
     */
    @Override
    public ListIterator<T> iterator() {
        return super.iterator(); // uses basicdoublelinkedlist iterator
    }

    /**
     * removes a specific element using the comparator
     *
     * @param targetData the element to remove
     * @param comparator comparator to compare elements
     * @return the node that was removed or null if not found
     */
    @Override
    public Node remove(T targetData, Comparator<T> comparator) {
        return super.remove(targetData, comparator);
    }
}
