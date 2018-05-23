package ph.eapesa.studies.week2;

public class TwoStackQueues {
    private class Node {
        String item;
        Node next;
    }

    private class Stack {
        private Node first = null;

        public boolean isEmpty() {
            return first == null;
        }

        public void push(String item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
        }

        public String pop() {
            String item = first.item;
            first = first.next;
            return item;
        }
    }

    private Stack inbox, outbox;

    public TwoStackQueues() {
        inbox = new Stack();
        outbox = new Stack();
    }

    public void enqueue(String item) {
        inbox.push(item);
    }

    public String dequeue() {
        if (outbox.isEmpty()) {
            while (!inbox.isEmpty()) {
                outbox.push(inbox.pop());
            }
        }
        return outbox.pop();
    }
}
