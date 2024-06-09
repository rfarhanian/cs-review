package com.farhanian.cs.unionfind;

/**
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/regions-cut-by-slashes">The problem</a>
 * @see <a href="https://leetcode.com/problems/regions-cut-by-slashes/">Leetcode problem</a>
 */
public class RegionBySlashesFinder {

    public int regionsBySlashes(String[] grid) {
        int size = 4 * grid.length * grid.length;
        DisjointSet ds = new DisjointSet(size);
        int cols = grid.length;
        int rows = grid.length;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                char value = grid[row].charAt(col);
                //System.out.println("value:" + value);
                int rootId = 4 * ((row * cols) + col); // rootId is the north Id
                //System.out.println("rootId:" + rootId);
                int westId = rootId + 1;
                int eastId = rootId + 2;
                int southId = rootId + 3;
                if (value == '/' || value == ' ') {
                    ds.union(rootId, westId);
                    ds.union(southId, eastId);
                }
                if (value == '\\' || value == ' ') {
                    ds.union(rootId, eastId);
                    ds.union(westId, southId);
                }

                if (row + 1 < rows) { // southId union with the north node of the southern neighbor
                    ds.union(southId, rootId + (4 * cols));
                }
                if (row - 1 >= 0) { // rootId union with the southern node of the northern neighbor
                    ds.union(rootId, rootId - (4 * cols) + 3);
                }

                if (col - 1 >= 0) { // westId (left) union with east node of the left(western) neighbor
                    ds.union(westId, rootId - 4 + 2);
                }

                if (col + 1 < cols) { // eastId (right) union with the west node of the right(eastern) neighbor
                    ds.union(eastId, rootId + 4 + 1);
                }
            }
        }
        return ds.getCount();
    }

    private static class DisjointSet {
        private int[] parents;
        private int[] ranks;
        private int count;

        public DisjointSet(int size) {
            parents = new int[size];
            ranks = new int[size];
            count = 0;
            for (int i = 0; i < size; i++) {
                parents[i] = i;
                ranks[i] = i;
                count++;
            }
        }

        public int find(int x) {
            if (parents[x] != x) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }

        public void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX != parentY) {
                int parentXRank = ranks[parentX];
                int parentYRank = ranks[parentY];
                if (parentXRank > parentYRank) {
                    parents[parentY] = parentX;
                    ranks[parentX] += parentYRank;
                } else {
                    parents[parentX] = parentY;
                    ranks[parentY] += parentXRank;
                }
                count--;
            }
        }

        public int getCount() {
            return this.count;
        }
    }
}
