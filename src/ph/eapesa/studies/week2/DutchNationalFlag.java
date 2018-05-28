package ph.eapesa.studies.week2;

public class DutchNationalFlag {
    private static class Bucket {
        private String pebColor;
        public Bucket(String color) {
            this.pebColor = color;
        }
        public String color() {
            return pebColor;
        }
        public void changeColor(String newColor) {
            pebColor = newColor;
        }
    }

    public static void swap(Bucket a, Bucket b) {
        String swapColor = a.color();
        a.changeColor(b.color());
        b.changeColor(swapColor);
    }

    public static void main(String[] args) {
        Bucket[] pebbleBucket = new Bucket[]{ new Bucket("red"), new Bucket("blue"), new Bucket("red"), new Bucket("white")};
        // order red, blue, white

        int red = 0;
        int white = 0;
        int blue = 0;
        
    }
}
