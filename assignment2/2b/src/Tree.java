import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.util.Arrays.asList;

/**
 * Tree for getting common ancestors
 */
public class Tree<T> {
    private T value;
    private Tree<T> parent;
    private Tree<T> left;
    private Tree<T> right;

    Tree(T value) {
        this(value, null, null, null);
    }

    Tree(T value, Tree<T> parent, Tree<T> left, Tree<T> right) {
        this.value = value;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    /**
     * Add new element
     *
     * @param newValue  -- new element
     * @param parentVal -- value of parent
     * @param isLeft    -- is left or right child
     * @return true if added
     */
    boolean add(T newValue, T parentVal, boolean isLeft) {
        if (parentVal.equals(value)) {
            if (isLeft) {
                left = new Tree<T>(newValue, this, left, null);
                if (left.left != null) {
                    left.left.parent = left;
                }
            } else {
                right = new Tree<T>(newValue, this, null, right);
                if (right.right != null) {
                    right.right.parent = right;
                }
            }
            return true;
        } else {
            boolean res = false;
            if (left != null) {
                res = left.add(newValue, parentVal, isLeft);
            } if (!res && right != null) {
               res = right.add(newValue, parentVal, isLeft);
            }

            return res;
        }
    }

    /**
     * Get common ancestor
     *
     * @param element1 -- first element
     * @param element2 -- second element
     * @return -- common ancestors for elements
     * @throws TreeException.ElementDoesntFound -- if element isn't in tree
     */
    T getCommonAncestor(T element1, T element2)
            throws TreeException.ElementDoesntFound {
        Entry<Tree<T>, Integer> el1 = findWithDepth(element1, 0);
        Entry<Tree<T>, Integer> el2 = findWithDepth(element2, 0);
        if (el1 == null) {
            throw new TreeException.ElementDoesntFound("element " + element1.toString() + " doesn't found");
        } else if (el2 == null) {
            throw new TreeException.ElementDoesntFound("element " + element2.toString() + " doesn't found");
        } else {
            return getCommonAncestor(el1, el2);
        }
    }

    /**
     * Get common ancestor for two elements in the tree with known depth.
     * @param el1 -- first element with depth.
     * @param el2 -- second element with depth.
     * @return -- value of common ancestor
     */
    private T getCommonAncestor(Entry<Tree<T>, Integer> el1, Entry<Tree<T>, Integer> el2) {
        while (el2.getValue() > el1.getValue()) {
            el2 = new SimpleEntry<>(el2.getKey().parent, el2.getValue() - 1);
        }
        while (el1.getValue() > el2.getValue()) {
            el1 = new SimpleEntry<>(el1.getKey().parent, el1.getValue() - 1);
        }
        while (!el1.getKey().value.equals(el2.getKey().value)) {
            el1 = new SimpleEntry<>(el1.getKey().parent, el1.getValue() - 1);
            el2 = new SimpleEntry<>(el2.getKey().parent, el2.getValue() - 1);
        }
        return el1.getKey().value;
    }

    /**
     * Find element with depth
     * @param element -- element to find
     * @param depth -- current depth. 0 in the beginning
     * @return -- element and depth
     */
    private Entry<Tree<T>, Integer> findWithDepth(T element, int depth) {
        if (element.equals(value)) {
            return new SimpleEntry<>(this, depth);
        }
        Entry<Tree<T>, Integer> rght = right == null ? null : right.findWithDepth(element, depth + 1);
        Entry<Tree<T>, Integer> lft = left == null ? null : left.findWithDepth(element, depth + 1);

        return rght == null ? lft : rght;
    }
}
