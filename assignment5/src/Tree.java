import java.util.LinkedList;
import java.util.List;

/**
 * Tree for keeping elements and getting them in the order
 * @param <T> -- type of elements
 */
public class Tree <T> {

    private Node root;

    Tree() {
        root = new Root();
    }

    /**
     * Get list of all elements in order
     * @return -- list of elements
     */
    public List<T> createListInOrder() {
        return root.createListInOrder();
    }

    /**
     * Add elements, where val1 is before val2
     * @param val1
     * @param val2
     */
    public void addElements(T val1, T val2) {
        Node node1 = find(val1);
        Node node2 = find(val2);

        if (node1 == null && node2 == null) {
            Node newNode = new Node(val1, root);
            newNode.children.add(new Node(val2, root));
            root.children.add(newNode);
        } else if (node2 == null) {
            node1.children.add(new Node(val2, node1));
        } else if (node1 == null) {
            Node parentOrRoot = node2.parent == null ? root : node2.parent;
            int posOfSecond = parentOrRoot.children.indexOf(node2);
            parentOrRoot.children.add(posOfSecond, new Node(val1, parentOrRoot));
        } else if (!node1.hasChildren(node2)) {
            Node branch = node2.parent.getBranchWithNode(node2);
            node1.children.add(branch);
            node2.parent.children.remove(branch);
        }
    }

    /**
     * Add element without order if it doesn't exist
     * @param val
     */
    public void addElement(T val) {
        if (find(val) == null) {
            root.children.add(new Node(val, root));
        }
    }

    /**
     * Find element on the tree
     * @param val
     * @return -- node with this value or null if doesn't exist
     */
    private Node find(T val) {
        return root.find(val);
    }

    /**
     * Class of node of tree
     */
    private class Node {
        private T value;
        private List<Node> children;
        private Node parent;

        Node(T value, Node parent) {
            this.value = value;
            this.parent = parent;
            children = new LinkedList<>();
        }

        Node() {
            children = new LinkedList<>();
        }

        /**
         * Create list in order
         * @return
         */
        List<T> createListInOrder() {
            List<T> result = new LinkedList<>();
            result.add(value);
            for (Node child : children) {
                result.addAll(child.createListInOrder());
            }
            return result;
        }

        Node find(T val) {
            if (val.equals(value)) {
                return this;
            }
            for (Node child : children) {
                Node res = child.find(val);
                if (res != null) {
                    return res;
                }
            }

            return null;
        }

        boolean hasChildren(Node node) {
            return find(node.value) != null;
        }

        /**
         * Get first children witch has node as child
         * @param node
         * @return -- node or null if doesn't exist
         */
        Node getBranchWithNode(Node node) {
            for (Node child : children) {
                if (child.hasChildren(node)) {
                    return  child;
                }
            }

            return null;
        }
    }

    /**
     * Class for root of tree. Root doesn't has the value,
     * so it on the method createListInOrder we need to skip it.
     */
    private class Root extends Node {
        public List<T> createListInOrder() {
            List<T> result = new LinkedList<>();
            for (Node child : super.children) {
                result.addAll(child.createListInOrder());
            }
            return result;
        }
    }
}
