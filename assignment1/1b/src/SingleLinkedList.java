/**
 * Single Linked list for getting k to last element.
 * **/
class SingleLinkedList<T> {
    private Node head;
    private int size = 0;

    /**
     * Add the element to the tail
     * @param el -- element to add
     */
    void add(T el) {
        size++;
        Node newNode = new Node(el);
        if (head == null) {
            head = newNode;
            return;
        }

        Node t = head;
        while (t.next != null) {
            t = t.next;
        }
        t.next = newNode;
    }

    /**
     * Get the element with number
     * @param i -- number of the element
     * @return -- element
     */
    T getElement(int i) {
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }
        Node t = head;
        int k = 0;
        while(t.next != null && k < i) {
            t = t.next;
            k++;
        }

        if (k == i) {
            return t.element;
        } else{
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Get k to last element
     * @param k -- number of the element from end of list
     * @return -- element
     */
    T getKToLast(int k) {
        return getElement(size - k - 1);
    }

    /**
     * Class for nodes of list
     */
    private class Node {
        T element;
        Node next;

        Node(T el, Node n) {
            element = el;
            next = n;
        }

        Node(T el) {
            this(el, null);
        }
    }
}
