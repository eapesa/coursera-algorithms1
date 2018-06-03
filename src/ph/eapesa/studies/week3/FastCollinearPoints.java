package ph.eapesa.studies.week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    LineSegment[] lineSegments;
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.IllegalArgumentException();
        }
        Point[] copy = Arrays.copyOf(points, points.length);
        ArrayList<LineSegment> collinears = new ArrayList<>();
        for (int i = 0; i < copy.length - 1; i++) {
            Arrays.sort(copy, copy[i].slopeOrder());
            double prevSlope = Double.NEGATIVE_INFINITY;
            int count = 0;
            for (int j = i + 1; j < copy.length; j++) {
                double currSlope = copy[i].slopeTo(copy[j]);
                if (currSlope == prevSlope) {
                    count++;
                } else {
                    count = 0;
                }

                if (count == 4) {
                    collinears.add(new LineSegment(copy[i], copy[j]));
                    count = 0;
                }
            }
        }
        lineSegments = collinears.toArray(new LineSegment[collinears.size()]);
    }

    private void printPoints(Point[] points) {
        for (Point p : points) {
            System.out.println(p.toString());
        }
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
        System.out.println("SEGMENTS: " + collinear.numberOfSegments());
    }
}
