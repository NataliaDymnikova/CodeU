/**
 * Created by Natasha on 17-May-17.
 */

class SingleLinkedList<T> {
    private Node head;
    private int size = 0;

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

    int getSize() {
        return size;
    }

    T getElement(int i) {
        if (head == null) {
            return null;
        }
        Node t = head;
        int k = 0;
        while(t.next != null && k < i) {
            t = t.next;
            k++;
        }

        return k == i ? t.element : null;
    }

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
