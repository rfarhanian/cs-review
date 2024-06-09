package com.farhanian.cs.unionfind;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Please check the time and space complexity of the following implementation. Please check the leetcode implementation
 * with array data structure as parent and rank as well.
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/number-of-islands">The Problem</a>
 * @see <a href="https://leetcode.com/problems/number-of-islands/?source=submission-ac">Leetcode Problem</a>
 */
public class NumberOfIslands {

    public static void main(String args[]) {
        // Example grid
        List<List<Character>> grid1 = Arrays.asList(
                Arrays.asList('1', '1', '1'),
                Arrays.asList('0', '1', '0'),
                Arrays.asList('1', '0', '0'),
                Arrays.asList('1', '0', '1')
        );

        List<List<Character>> grid2 = Arrays.asList(
                Arrays.asList('1', '1', '1', '1', '0'),
                Arrays.asList('1', '0', '0', '0', '1'),
                Arrays.asList('1', '0', '0', '1', '1'),
                Arrays.asList('0', '1', '0', '1', '0'),
                Arrays.asList('1', '1', '0', '1', '1')
        );

        List<List<Character>> grid3 = Arrays.asList(
                Arrays.asList('1', '1', '1', '1', '0'),
                Arrays.asList('1', '0', '0', '0', '1'),
                Arrays.asList('1', '1', '1', '1', '1'),
                Arrays.asList('0', '1', '0', '1', '0'),
                Arrays.asList('1', '1', '0', '1', '1')
        );

        List<List<Character>> grid4 = Arrays.asList(
                Arrays.asList('1', '0', '1', '0', '1'),
                Arrays.asList('0', '1', '0', '1', '0'),
                Arrays.asList('1', '0', '1', '0', '1'),
                Arrays.asList('0', '1', '0', '1', '0'),
                Arrays.asList('1', '0', '1', '0', '1')
        );

        List<List<Character>> grid5 = Arrays.asList(
                Arrays.asList('1', '0', '1'),
                Arrays.asList('0', '0', '0'),
                Arrays.asList('1', '0', '1')
        );

        List<List<List<Character>>> inputs = Arrays.asList(grid1, grid2, grid3, grid4, grid5);

        for (int i = 0; i < inputs.size(); i++) {
            System.out.println((i + 1) + ".\t Grid: ");
            System.out.println(inputs.get(i));
            System.out.println("\n\t Output: " + numIslands(inputs.get(i)));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    public static int numIslands(List<List<Character>> grid) {
        DisjointSet ds = new DisjointSet(grid);
        int rows = grid.size();
        for (int row = 0; row < rows; row++) {
            List<Character> currentRow = grid.get(row);
            int cols = currentRow.size();
            for (int col = 0; col < cols; col++) {
                int id = (row * cols) + col;
                int currentValue = currentRow.get(col) == '1' ? 1 : 0;
                if (currentValue == 1) {
                    currentRow.set(col, '0');
                    int rightId = id + 1;

                    if (col + 1 < cols) {
                        int rightValue = currentRow.get(col + 1) == '1' ? 1 : 0;
                        if (rightValue == 1) {
                            ds.union(id, rightId);
                        }
                    }

                    int belowId = id + cols;
                    if (row + 1 < rows) {
                        int belowValue = grid.get(row + 1).get(col) == '1' ? 1 : 0;
                        if (belowValue == 1) {
                            ds.union(id, belowId);
                        }
                    }
                }
            }
        }
        return ds.getCount();
    }

    private static class DisjointSet {
        private Map<Integer, Integer> parents = new HashMap<>();
        private Map<Integer, Integer> ranks = new HashMap<>();
        private int count = 0;

        public DisjointSet(List<List<Character>> grid) {
            int rows = grid.size();
            for (int row = 0; row < rows; row++) {
                List<Character> currentRow = grid.get(row);
                int cols = currentRow.size();
                for (int col = 0; col < cols; col++) {
                    int id = (row * cols) + col;
                    int currentValue = currentRow.get(col) == '1' ? 1 : 0;
                    if (currentValue == 1) {
                        parents.put(id, id);
                        count++;
                    }
                    ranks.put(id, 0);
                }
            }
        }

        public int find(int x) {
            if (x != parents.get(x)) {
                parents.put(x, find(parents.get(x)));
            }
            return parents.get(x);
        }

        public void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            if (parentX != parentY) {
                int rankX = ranks.get(x);
                int rankY = ranks.get(y);
                if (rankY > rankX) {
                    parents.put(parentX, parentY);
                } else if (rankX > rankY) {
                    parents.put(parentY, parentX);
                } else {
                    parents.put(parentX, parentY);
                    ranks.put(parentY, rankY + 1);
                }
                count--;
            }
        }

        public int getCount() {
            return this.count;
        }
    }
}
