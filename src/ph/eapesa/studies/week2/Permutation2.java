package ph.eapesa.studies.week2;

// NOTE: this is for the quiz
public class Permutation2 {
    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; --j) {
                if (arr[j] < arr[j - 1]) {
                    int swap = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = swap;
                }
            }
        }
    }

    public static boolean isPermutation(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) {
            return false;
        }

        sort(arr1);
        sort(arr2);
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Initialize the two integer arrays here
        // Use isPermutation method to compare the two integer arrays here
        // Example below:
        int[] arr1 = new int[]{1, 3, 2, 4, 5};
        int[] arr2 = new int[]{5, 4, 3, 2, 1};
        System.out.println("IS PERMUTATION? " + isPermutation(arr1, arr2));
    }
}
