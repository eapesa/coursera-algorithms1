package ph.eapesa.studies.week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private LineSegment[] lineSegments;
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.IllegalArgumentException();
        }
        Point[] copy = Arrays.copyOf(points, points.length);
        Arrays.sort(copy);
        ArrayList<LineSegment> collinears = new ArrayList<>();
        for (int p = 0; p < copy.length - 3; p++) {
            for (int q = p + 1; q < copy.length - 2; q++) {
                for (int r = q + 1; r < copy.length - 1; r++) {
                    for (int s = r + 1; s < copy.length; s++) {
                        Point pp = copy[p];
                        Point pq = copy[q];
                        Point pr = copy[r];
                        Point ps = copy[s];
                        if (pp.slopeTo(pq) == pp.slopeTo(pr) && pp.slopeTo(pq) == pp.slopeTo(ps)) {
                            collinears.add(new LineSegment(pp, ps));
                        }
                    }
                }
            }
        }
        lineSegments = collinears.toArray(new LineSegment[collinears.size()]);
    }

    public int numberOfSegments() {
        return lineSegments.length;
    }

    public LineSegment[] segments() {
        return lineSegments;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        System.out.println("SEGMENTS: " + collinear.numberOfSegments());
        for (int i = 0; i < collinear.numberOfSegments(); i++) {
            System.out.println(collinear.lineSegments[i].toString());
        }
    }
}
