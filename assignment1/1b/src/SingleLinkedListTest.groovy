import org.junit.Before
import org.junit.Test

import static org.junit.Assert.assertEquals

class SingleLinkedListTest {
    private SingleLinkedList<Integer> list;

    @Before
    public void setUp() {
        list = new SingleLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

    @Test
    public void TestInBound0() {
        int k = 0;
        int result = list.getKToLast(k);
        assertEquals(result, 9);
    }

    @Test
    public void TestInBound9() {
        int k = 9;
        int result = list.getKToLast(k);
        assertEquals(result, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestOutOfBoundMore() {
        int k = 10;
        int result = list.getKToLast(k);
        assertEquals(result, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void TestOutOfBoundLess() {
        int k = -1;
        int result = list.getKToLast(k);
        assertEquals(result, 0);
    }
}
