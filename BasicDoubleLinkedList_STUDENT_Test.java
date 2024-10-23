
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList_STUDENT_Test {
    BasicDoubleLinkedList<String> basicLinkedString;

    @Before
    public void setUp() throws Exception {
        basicLinkedString = new BasicDoubleLinkedList<String>();
    }

    @Test
    public void testAddToFrontAndEnd() {
        basicLinkedString.addToFront("Windows");
        basicLinkedString.addToEnd("Linux");
        assertEquals("Windows", basicLinkedString.getFirst());
        assertEquals("Linux", basicLinkedString.getLast());
    }

    @Test
    public void testRetrieveFirstAndLast() {
        basicLinkedString.addToFront("macOS");
        basicLinkedString.addToEnd("Ubuntu");
        assertEquals("macOS", basicLinkedString.retrieveFirstElement());
        assertEquals("Ubuntu", basicLinkedString.retrieveLastElement());
    }

    @Test
    public void testIteratorHasNext() {
        basicLinkedString.addToFront("Android");
        basicLinkedString.addToEnd("iOS");
        ListIterator<String> iterator = basicLinkedString.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Android", iterator.next());
        assertEquals("iOS", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNextException() {
        basicLinkedString.addToFront("ChromeOS");
        ListIterator<String> iterator = basicLinkedString.iterator();
        iterator.next();
        iterator.next();  // Should throw NoSuchElementException
    }
}
