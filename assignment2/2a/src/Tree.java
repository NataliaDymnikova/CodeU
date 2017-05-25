import java.util.LinkedList;
import java.util.List;

/**
 * Tree for getting ancestors
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
     * @param newValue -- new element
     * @param parentVal -- value of parent
     * @param isLeft -- is left or right child
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
            return (left != null && left.add(newValue, parentVal, isLeft))
                    || (right != null && right.add(newValue, parentVal, isLeft));
        }
    }

    /**
     * Get list of ancestors for element. If element doesn't exist, throw exception
     * @param element -- value of element
     * @return -- list of ancestors
     * @throws TreeException.ElementDoesntFound -- if element isn't in tree
     */
    List<T> getAncestors(T element) throws TreeException.ElementDoesntFound {
        List<T> parents = getParents(element);
        if (parents.isEmpty() && !value.equals(element)) {
            throw new TreeException.ElementDoesntFound("element " + element.toString() + " doesn't found");
        } else {
            return parents;
        }
    }

    /**
     * Get list of parents for element. If element doesn't exist, return empty list
     * @param element -- value of element
     * @return -- list of ancestors
     */
    private List<T> getParents(T element) {
        if (value.equals(element)) {
            return getParents();
        } else {
            List<T> leftRes = left == null ? new LinkedList<T>() : left.getParents(element);
            List<T> rightRes = right == null ? new LinkedList<T>() : right.getParents(element);
            if (leftRes.isEmpty()) {
                return rightRes;
            } else {
                return leftRes;
            }
        }
    }

    /**
     * Get parents for current element
     * @return list of ancestors
     */
    private List<T> getParents() {
        if (parent == null) {
            return new LinkedList<T>();
        } else {
            List<T> res = parent.getParents();
            res.add(0, parent.value);
            return res;
        }
    }

}
