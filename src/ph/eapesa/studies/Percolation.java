package ph.eapesa.studies;

import edu.princeton.cs.algs4.QuickUnionUF;

import java.util.Arrays;
import java.util.Random;

public class Percolation {

    private QuickUnionUF grid;
    private boolean[] map;
    private int totalBlocks;
    private int openBlocks;
    private int N;

    private static final double PSTAR = 0.593;

    public Percolation(int n) {
        N = n;
        totalBlocks = n * n;
        openBlocks = 0;
        grid = new QuickUnionUF(totalBlocks);
        map = new boolean[totalBlocks];
        Arrays.fill(map, false);
    }

    public void open(int row, int col) {
        if (row < 0 || col < 0) {
            throw new IllegalArgumentException();
        }

        if (row > (N - 1) || col > (N - 1)) {
            throw new IllegalArgumentException();
        }

        int index = (N * row) + col;
        // Check top, bottom, left and right of index if open; connect if they are open
        if (!isEdge(row - 1)) {
            int topIndex = (N * (row - 1)) + col;
            openSite(index, topIndex);
        }

        if (!isEdge(row + 1)) {
            int bottomIndex = (N * (row + 1)) + col;
            openSite(index, bottomIndex);
        }

        if (!isEdge(col - 1)) {
            int leftIndex = (N * row) + (col - 1);
            openSite(index, leftIndex);
        }

        if (!isEdge(col + 1)) {
            int rightIndex = (N * row) + (col + 1);
            openSite(index, rightIndex);
        }

        map[index] = true;
        openBlocks++;
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0) {
            throw new IllegalArgumentException();
        }

        if (row > (N - 1) || col > (N - 1)) {
            throw new IllegalArgumentException();
        }

        int index = (N * row) + col;
        return map[index];
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0) {
            throw new IllegalArgumentException();
        }

        if (row > (N - 1) || col > (N - 1)) {
            throw new IllegalArgumentException();
        }

        return (openBlocks == totalBlocks);
    }

    public int numberOfOpenSites() {
        return openBlocks;
    }

    public boolean percolates() {
        double p = (double) openBlocks / (double) totalBlocks;
        System.out.println("PERCOLATION PERCENTAGE: " + p);
        return (p >= PSTAR);
    }

    private boolean isEdge(int edge) {
        if (edge > 0 || edge < (N - 1)) {
            return true;
        }

        return false;
    }

    private void openSite(int p, int q) {
        if (grid.connected(p, q)) {
            return;
        }

        if (map[p]) {
            return;
        }

        grid.union(p, q);
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int min = 0, max = 999;
        int row = rand.nextInt((max - min) + 1) + min;
        int col = rand.nextInt((max - min) + 1) + min;

        Percolation p = new Percolation(max + 1);
        while (!p.percolates()) {
            row = rand.nextInt((max - min) + 1) + min;
            col = rand.nextInt((max - min) + 1) + min;
            if (!p.isOpen(row, col)) {
                p.open(row, col);
            }

            if (p.isFull(row, col)) {
                break;
            }
        }
    }
}
