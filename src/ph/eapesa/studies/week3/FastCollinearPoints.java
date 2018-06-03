package ph.eapesa.studies.week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class FastCollinearPoints {
    private LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.IllegalArgumentException();
        }

        Point[] copy = Arrays.copyOf(points, points.length);
        ArrayList<LineSegment> collinears = new ArrayList<>();
        for (Point index : points) {
            Arrays.sort(copy, index.slopeOrder());

            double prevSlope = Double.NEGATIVE_INFINITY;
            double currSlope = 0;
            ArrayList<Point> sameSlopePoints = new ArrayList<>();

            for (int j = 1; j < copy.length; j++) {
                currSlope = index.slopeTo(copy[j]);
                if (currSlope != prevSlope) {
                    if (sameSlopePoints.size() >= 3) {
                        sameSlopePoints.add(index);
                        Collections.sort(sameSlopePoints);
                        collinears.add(new LineSegment(sameSlopePoints.get(0),
                                sameSlopePoints.get(sameSlopePoints.size() - 1)));
                    }
                    sameSlopePoints.clear();
                }
                sameSlopePoints.add(copy[j]);
                prevSlope = currSlope;
            }

            if (sameSlopePoints.size() >= 3) {
                sameSlopePoints.add(index);
                Collections.sort(sameSlopePoints);
                collinears.add(new LineSegment(sameSlopePoints.get(0),
                        sameSlopePoints.get(sameSlopePoints.size() - 1)));
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
    }
}
