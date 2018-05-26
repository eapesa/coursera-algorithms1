package ph.eapesa.studies.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> {
    private int size, capacity;
    private Item[] queue;

    public RandomizedQueue() {
        size = 0;
        capacity = 1;
        queue = (Item[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        queue[size] = item;
        size++;
        resize();
    }

    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        int index = StdRandom.uniform(0, size);
        Item item = queue[index];
        --size;
        queue[index] = queue[size];
        queue[size] = null;
        resize();
        return item;
    }

    public Item sample() {
        return queue[StdRandom.uniform(0, size)];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private void resize() {
        if (size >= capacity) {
            capacity = capacity * 2;
            transfer(capacity);
        }

        if (size <= (capacity / 4)) {
            capacity = capacity / 2;
            transfer(capacity);
        }

        return;
    }

    private void transfer(int capacity) {
        Item[] newQueue = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[i];
        }
        queue = newQueue;
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int current;

        public RandomizedQueueIterator() {
            current = 0;
        }

        @Override
        public boolean hasNext() {
            return current != size;
        }

        @Override
        public Item next() {
            Item item = queue[current];
            current++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(10);
        rq.enqueue(20);
        rq.enqueue(30);
        rq.enqueue(40);

        Iterator rqiter = rq.iterator();
        System.out.println("DEQ: " + rq.dequeue() + "\n===");
        System.out.println("DEQ: " + rq.dequeue() + "\n===");
        while (rqiter.hasNext()) {
            System.out.println("Q: " + rqiter.next());
        }
        System.out.println("DEQ: " + rq.dequeue() + "\n===");
        System.out.println("DEQ: " + rq.dequeue() + "\n===");
    }
}
