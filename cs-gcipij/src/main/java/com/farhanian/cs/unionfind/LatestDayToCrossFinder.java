package com.farhanian.cs.unionfind;

/**
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/last-day-where-you-can-still-cross">The problem</a>
 * @see <a href="https://leetcode.com/problems/last-day-where-you-can-still-cross/">Leetcode problem</a>
 */
public class LatestDayToCrossFinder {

    public int latestDayToCross(int row, int col, int[][] cells) {
        int[][] matrix = new int[row][col];
        int[][] convertedCells = new int[cells.length][2];
        for (int i = 0; i < cells.length; i++) {
            convertedCells[i] = new int[]{cells[i][0] - 1, cells[i][1] - 1};
        }
        int size = row * col + 2;
        int[][] directions = new int[][]{
                {0, -1}, //left
                {0, 1}, //right
                {-1, 0},   //up
                {1, 0}, //down
                {-1, -1}, //northWest
                {-1, 1}, //northEast
                {1, -1}, //southWest
                {1, 1} //southEast
        };
        int leftNode = 0;
        int rightNode = row * col + 1;
        DisjointSet ds = new DisjointSet(size);
        for (int day = 0; day < convertedCells.length; day++) {
            int[] current = convertedCells[day];
            int currentRow = current[0];
            int currentCol = current[1];
            int id = (currentRow * col) + currentCol + 1;
            matrix[currentRow][currentCol] = 1;
            for (int[] direction : directions) {
                int candidateRow = currentRow + direction[0];
                int candidateCol = currentCol + direction[1];
                int neighborId = (candidateRow * col) + candidateCol + 1;
                //System.out.println("candidate row: " + candidateRow);
                //System.out.println("candidate col: " + candidateCol);
                //System.out.println("matrix: " + Arrays.deepToString(matrix));
                if (isInRange(candidateRow, candidateCol, row, col) && matrix[candidateRow][candidateCol] == 1) {
                    ds.union(id, neighborId);
                }
                if (currentCol == 0) {
                    ds.union(id, leftNode);
                }
                if (currentCol == col - 1) {
                    ds.union(id, rightNode);
                }
            }
            if (ds.find(leftNode) == ds.find(rightNode)) {
                return day;
            }
        }

        return -1;
    }

    private boolean isInRange(int currentRow, int currentCol, int rows, int cols) {
        //System.out.println(">>>cols: " + cols);
        //System.out.println(">>>rows: " + rows);
        return currentRow >= 0 && currentRow < rows && currentCol >= 0 && currentCol < cols;
    }

    private static class DisjointSet {
        private int[] parents;
        private int[] ranks;
        private int count;

        public DisjointSet(int size) {
            parents = new int[size];
            ranks = new int[size];
            for (int i = 0; i < size; i++) {
                parents[i] = i;
                ranks[i] = 1;
                count++;
            }
        }

        public int find(int x) {
            if (x != parents[x]) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }

        public void union(int x, int y) {
            System.out.println("Union: " + x + " and " + y);
            int parentX = find(x);
            int parentY = find(y);
            System.out.println("Parent Union: " + parentX + " and " + parentY);
            if (parentX != parentY) {
                int xParentRank = ranks[parentX];
                int yParentRank = ranks[parentY];
                //System.out.println("xParentRank :" + xParentRank);
                //System.out.println("yParentRank :" + yParentRank);
                if (xParentRank > yParentRank) {
                    parents[parentY] = parentX;
                    ranks[parentX] += yParentRank;
                    //System.out.println("ranks[parentX] :" + ranks[parentX]);
                } else {
                    parents[parentX] = parentY;
                    ranks[parentY] += xParentRank;
                }
                count--;
            }
        }
    }
}
