
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * generic double-linked list implements iterable interface
 * uses head and tail nodes
 * @ Leul Belay
 * @param <T> the type of elements in the list
 */
public class BasicDoubleLinkedList<T> implements Iterable<T> {

    /**
     * node class stores data and points to next/prev nodes
     */
    protected class Node {
        protected T data;
        protected Node prev;
        protected Node next;

        /**
         * creates a new node
         *
         * @param data the value of this node
         */
        protected Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    protected Node head, tail;
    protected int size;

    /**
     * initializes the list with head/tail as null and size 0
     */
    public BasicDoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * adds an element to the front of the list
     *
     * @param data the value to add to the front
     */
    public void addToFront(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            // empty list
            head = newNode;
            tail = newNode;
        } else {
            // list has items
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    /**
     * adds an element to the end of the list
     *
     * @param data the value to add at the end
     */
    public void addToEnd(T data) {
        Node newNode = new Node(data);
        if (tail == null) {
            // empty list
            head = newNode;
            tail = newNode;
        } else {
            // list has items
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * returns but does not remove the first element
     *
     * @return first element or null if list is empty
     */
    public T getFirst() {
        if (head == null) return null;
        return head.data;
    }

    /**
     * returns but does not remove the last element
     *
     * @return last element or null if list is empty
     */
    public T getLast() {
        if (tail == null) return null;
        return tail.data;
    }

    /**
     * removes and returns the first element in the list
     *
     * @return first element or null if list is empty
     */
    public T retrieveFirstElement() {
        if (head == null) return null;
        T data = head.data;
        if (head == tail) {
            // one element in the list
            head = null;
            tail = null;
        } else {
            // more than one element
            head = head.next;
            head.prev = null;
        }
        size--;
        return data;
    }

    /**
     * removes and returns the last element in the list
     *
     * @return last element or null if list is empty
     */
    public T retrieveLastElement() {
        if (tail == null) return null;
        T data = tail.data;
        if (head == tail) {
            // one element in the list
            head = null;
            tail = null;
        } else {
            // more than one element
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return data;
    }

    /**
     * removes the first occurrence of the specified element using the comparator
     *
     * @param targetData the element to remove
     * @param comparator comparator for comparing elements
     * @return the node that was removed or null if not found
     */
    public Node remove(T targetData, Comparator<T> comparator) {
        Node current = head;
        while (current != null) {
            if (comparator.compare(current.data, targetData) == 0) {
                // found the element to remove
                if (current == head) {
                    // removing head
                    head = current.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        tail = null; // list is now empty
                    }
                } else if (current == tail) {
                    // removing tail
                    tail = current.prev;
                    if (tail != null) {
                        tail.next = null;
                    } else {
                        head = null; // list is now empty
                    }
                } else {
                    // removing from the middle
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--;
                return current;
            }
            current = current.next;
        }
        return null; // element not found
    }

    /**
     * returns the number of elements in the list
     *
     * @return the size of the list
     */
    public int getSize() {
        return size;
    }

    /**
     * converts the list into an arraylist
     *
     * @return an arraylist of the list’s elements
     */
    public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>();
        Node current = head;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }

    /**
     * iterator for the linked list
     */
    protected class DoubleLinkedListIterator implements ListIterator<T> {
        private Node nextNode;
        private Node lastReturned;

        /**
         * starts the iterator at the head of the list
         */
        public DoubleLinkedListIterator() {
            nextNode = head;
            lastReturned = null;
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public T next() {
            if (nextNode == null) {
                throw new NoSuchElementException();
            }
            lastReturned = nextNode;
            T data = nextNode.data;
            nextNode = nextNode.next;
            return data;
        }

        @Override
        public boolean hasPrevious() {
            if (nextNode == null) {
                // at the end can go back if the list isn’t empty
                return tail != null;
            } else {
                return nextNode.prev != null;
            }
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (nextNode == null) {
                // at the end move to the last node
                nextNode = tail;
            } else {
                nextNode = nextNode.prev;
            }
            lastReturned = nextNode;
            return nextNode.data;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(T e) {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * returns a new iterator for the list
     *
     * @return a listiterator for the list
     */
    public ListIterator<T> iterator() {
        return new DoubleLinkedListIterator();
    }
}
