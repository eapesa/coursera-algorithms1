package ph.eapesa.studies.week2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Invalid arguments");
            return;
        }

        RandomizedQueue<String> inputs = new RandomizedQueue<>();
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            inputs.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(inputs.dequeue());
        }
    }
}
