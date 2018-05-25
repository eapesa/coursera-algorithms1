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
        size++;
        resize();
        queue[size] = item;
    }

//    public Item dequeue() {
//        if (size == 0) {
//            throw new NoSuchElementException();
//        }
//        int index = StdRandom.uniform(0, size);
//        Item item = queue[index];
//        --size;
//        transfer(index, capacity);
//        return item;
//    }

//    public Item sample() {
//
//    }
//
//    @Override
//    public Iterator<Item> iterator() {
//        return null;
//    }

    private void resize() {
        if (size >= capacity) {
            capacity = capacity * 2;
            transfer(0, capacity);
        }

        if (size <= (capacity / 4)) {
            capacity = capacity / 2;
            transfer(0, capacity);
        }

        return;
    }

    private void transfer(int index, int capacity) {
        Item[] newQueue = (Item[]) new Object[capacity];
        for (int i = index; i < size; i++) {
            newQueue[i] = queue[i];
        }
        queue = newQueue;
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(10);
        rq.enqueue(20);
        rq.enqueue(30);
        rq.enqueue(40);
        System.out.println("DEQ: " + rq.dequeue());
        System.out.println("DEQ: " + rq.dequeue());
        System.out.println("DEQ: " + rq.dequeue());
    }
}
