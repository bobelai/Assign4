
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

public class SortedDoubleLinkedList_STUDENT_Test {
    SortedDoubleLinkedList<String> sortedLinkedString;
    StringComparator comparator;

    @Before
    public void setUp() throws Exception {
        comparator = new StringComparator();
        sortedLinkedString = new SortedDoubleLinkedList<String>(comparator);
    }

    @Test
    public void testAdd() {
        sortedLinkedString.add("Tesla");
        sortedLinkedString.add("Ford");
        sortedLinkedString.add("BMW");
        assertEquals("BMW", sortedLinkedString.getFirst());
        assertEquals("Tesla", sortedLinkedString.getLast());
    }

    @Test
    public void testIteratorHasPrevious() {
        sortedLinkedString.add("Audi");
        sortedLinkedString.add("Chevrolet");
        ListIterator<String> iterator = sortedLinkedString.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Audi", iterator.next());
        assertEquals("Chevrolet", iterator.next());
        assertTrue(iterator.hasPrevious());
        assertEquals("Chevrolet", iterator.previous());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddToFrontException() {
        sortedLinkedString.addToFront("FrontNode");  // Should throw UnsupportedOperationException
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNextException() {
        sortedLinkedString.add("Toyota");
        ListIterator<String> iterator = sortedLinkedString.iterator();
        iterator.next();
        iterator.next();  // Should throw NoSuchElementException
    }

    // Comparator for strings
    private class StringComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }
}
