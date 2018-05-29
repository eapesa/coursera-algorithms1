package ph.eapesa.studies.week3;

public class Merge {
    public static void sort(Comparable[] a, int left, int right) {
        if (left > right) return;
        int mid = (left + right) / 2;
        sort(a, left, mid);
        sort(a, mid + 1, right);
        merge(a, left, mid, right);
    }

    public static void merge(Comparable[] a, int left, int mid, int right) {

    }

    public static void main(String[] args) {
        Comparable<Integer>[] a = new Comparable[]{15, 1, 3, 5, 20, 2, 7, 19, 18, 12, 6, 4, 13, 1, 16};
//        Comparable<String>[] a = new Comparable[]{"m", "z", "v", "a", "x", "c", "n", "o", "d", "l"};
        Merge.sort(a, 0, a.length);
        for (int i = 0; i < a.length; i++) {
            System.out.println("a[" + i + "] >>> " + a[i]);
        }
    }
}
