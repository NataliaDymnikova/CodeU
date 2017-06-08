import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TreeTest {
    Tree<Integer> tree;

    @Before
    public void initialize() {
        // tree:
        //      -2
        //     0  -1
        //    2     1
        //   4 10    3
        //  6         5
        // 8           7
        //              9

        tree = new Tree<>(-2);
        tree.add(-1, -2, false);

        for (int i = 0; i < 10; i++) {
            boolean isLeft = i % 2 == 0;
            tree.add(i, i - 2, isLeft);
        }
        tree.add(10, 2, false);
    }

    @Test
    public void testWithRootCommonAncestor() throws Exception {
        assertEquals(tree.getCommonAncestor(3, 4).intValue(), -2);
    }

    @Test
    public void testWithOneIsAncestor() throws Exception {
        assertEquals(tree.getCommonAncestor(2, 4).intValue(), 2);
    }

    @Test
    public void testWithCommonAncestor() throws Exception {
        assertEquals(tree.getCommonAncestor(4, 10).intValue(), 2);
    }

    @Test
    public void testForFirstRootElement() throws Exception {
        assertEquals(tree.getCommonAncestor(-2, 10).intValue(), -2);
    }

    @Test
    public void testForSecondRootElement() throws Exception {
        assertEquals(tree.getCommonAncestor(0, -2).intValue(), -2);
    }

    @Test(expected = TreeException.ElementDoesntFound.class)
    public void testForNotExistedElement() throws Exception {
        tree.getCommonAncestor(2, 20);
    }

}