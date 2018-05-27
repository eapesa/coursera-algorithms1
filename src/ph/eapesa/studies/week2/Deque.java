package ph.eapesa.studies.week2;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node first, last;

    private class Node {
        private Item item;
        private Node next;
        private Node previous;
    }

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
            throw new java.lang.IllegalArgumentException();
        }

        if ((first == null) && (last == null)) {
            first = new Node();
            first.item = item;
            first.next = null;
            first.previous = null;
            last = first;
        }
        else {
            Node newFirst = new Node();
            newFirst.item = item;
            newFirst.next = first;
            newFirst.previous = last;
            first.previous = newFirst;
            first = newFirst;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException();
        }

        if ((first == null) && (last == null)) {
            last = new Node();
            last.item = item;
            last.next = null;
            last.previous = null;
            first = last;
        }
        else {
            Node newLast = new Node();
            newLast.item = item;
            newLast.next = null;
            newLast.previous = last;
            last.next = newLast;
            last = newLast;
        }
        size++;
    }

    public Item removeFirst() {
        if (first == null) {
            throw new java.util.NoSuchElementException();
        }
        Item item = first.item;
        if (first.next != null) {
            first = first.next;
            first.previous = null;
        }
        else {
            first = null;
        }
        --size;
        return item;
    }

    public Item removeLast() {
        if (last == null) {
            throw new java.util.NoSuchElementException();
        }
        Item item = last.item;
        if (last.previous != null) {
            last = last.previous;
            last.next = null;
        }
        else {
            last = null;
        }
        --size;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(0);
        deque.addLast(2);
        deque.addFirst(-1);
        deque.addLast(3);
        deque.addFirst(-2);
        deque.addFirst(-3);
        deque.addLast(3);

        Iterator<Integer> d = deque.iterator();
        while (d.hasNext()) {
            System.out.printf("ITEM: %d\n", d.next());
        }

        System.out.println("==");
        deque.removeFirst();
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        deque.removeFirst();

        d = deque.iterator();
        while (d.hasNext()) {
            System.out.printf("ITEM: %d\n", d.next());
        }
    }
}

