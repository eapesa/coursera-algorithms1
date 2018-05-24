package ph.eapesa.studies.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;
        Node(Item item) {
            this.item = item;
            next = null;
        }
        Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    private int size;
    private Node first, last;

    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return (first == null) && (last == null);
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (first == null) {
            first = new Node(item, null);
            first = last;
        } else {
            Node newFirst = new Node(item, first);
            first = newFirst;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (first == null) {
            first = new Node(item, null);
            first = last;
        } else {
            last.next = new Node(item, null);
            last = last.next;
        }
        size++;
    }

    public Item removeFirst() {
        if (first == null) {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        first = first.next;
        --size;
        return item;
    }

    public Item removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        last = last.next;
        --size;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        System.out.printf("IS FIRST NULL? " + (first == null));
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(0);
        deque.addLast(2);
        deque.addFirst(-1);
        deque.addLast(3);

        Iterator<Integer> d = deque.iterator();
        System.out.println("Hi! "+d.hasNext());
        while (d.hasNext()) {
            System.out.printf("ITEM: %d\n", d.next());
        }
    }
}
