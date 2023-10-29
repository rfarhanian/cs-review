package com.mnaseri.cs.homework.problem.leetcode;

/**
 * @see <a href="https://leetcode.com/problems/unique-paths-iii/">The problem</a>
 */
public class UniquePathIII {

    public static void main(String[] args) {
        int[][] input = new int[][]{
                {1, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 2, -1}
        };
        UniquePathIII uniquePathIII = new UniquePathIII();
        int output = uniquePathIII.uniquePathsIII(input);
        System.out.println("output = " + output);

    }

    public int uniquePathsIII(int[][] grid) {
        int row = 0;
        int col = 0;
        int rest = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    row = i;
                    col = j;
                }
                if (grid[i][j] >= 0) {
                    rest++;
                }
            }
        }
        return uniquePaths(grid, row, col, rest);
    }

    private int uniquePaths(int[][] grid, int row, int col, int rest) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] < 0) {
            return 0;
        }
        if (grid[row][col] == 2 && rest == 1) {
            return 1;
        }

        int temp = grid[row][col];
        grid[row][col] = -4;
        int total = 0;

        if (isInRange(grid, row, col + 1) && (grid[row][col + 1] >= 0)) { //right
            total += uniquePaths(grid, row, col + 1, rest - 1);
        }
        if (isInRange(grid, row + 1, col) && (grid[row + 1][col] >= 0)) { //down
            total += uniquePaths(grid, row + 1, col, rest - 1);
        }

        if (isInRange(grid, row - 1, col) && (grid[row - 1][col] >= 0)) { //up
            total += uniquePaths(grid, row - 1, col, rest - 1);
        }

        if (isInRange(grid, row, col - 1) && (grid[row][col - 1] >= 0)) { //left
            total += uniquePaths(grid, row, col - 1, rest - 1);
        }
        grid[row][col] = temp;
        return total;
    }

    private boolean isInRange(int[][] grid, int row, int col) {
        return row >= 0 && col >= 0 && row < grid.length && col < grid[row].length;
    }
}

