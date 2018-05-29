package ph.eapesa.studies.week3;

import edu.princeton.cs.algs4.StdRandom;
public class Quick {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;
        int pi = partition(a, lo, hi);
        sort(a, lo, pi - 1);
        sort(a, pi + 1, hi);
    }

    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo - 1;
        Comparable pivot = a[hi];
        for (int j = lo; j < hi; j++) {
            if (a[j].compareTo(pivot) == -1) {
                i++;
                Comparable swap = a[j];
                a[j] = a[i];
                a[i] = swap;
            }
        }
        int pi = i + 1;
        a[hi] = a[pi];
        a[pi] = pivot;
        return pi;
    }

    public static void main(String[] args) {
        Comparable<Integer>[] a = new Comparable[]{15, 1, 3, 5, 20, 2, 7, 19, 18, 12, 6, 4, 13, 1, 16};
//        Comparable<String>[] a = new Comparable[]{"m", "z", "v", "a", "x", "c", "n", "o", "d", "l"};
        Quick.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println("a[" + i + "] >>> " + a[i]);
        }
    }
}
