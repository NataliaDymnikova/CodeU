import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

public class TreeTest {
    Tree<Integer> tree;

    @Before
    public void initialize() {
        // tree:
        //      -2
        //     0  -1
        //    2     1
        //   4       3
        //  6         5
        // 8           7
        //              9

        tree = new Tree<>(-2);
        tree.add(-1, -2, false);

        for (int i = 0; i < 10; i++) {
            boolean isLeft = i % 2 == 0;
            tree.add(i, i - 2, isLeft);
        }
    }

    @Test
    public void testForSingleParent() throws TreeException.ElementDoesntFound {
        int i = 0;
        assertEquals(tree.getAncestors(i), asList(-2));
    }

    @Test
    public void testForSeveralParent() throws TreeException.ElementDoesntFound {
        int i = 9;
        assertEquals(tree.getAncestors(i), asList(7, 5, 3, 1, -1, -2));
    }

    @Test
    public void testForNoParent() throws TreeException.ElementDoesntFound {
        int i = -2;
        assertEquals(tree.getAncestors(i), emptyList());
    }

    @Test(expected = TreeException.ElementDoesntFound.class)
    public void testForNoElement() throws TreeException.ElementDoesntFound {
        int i = 10;
        tree.getAncestors(i);
    }
}