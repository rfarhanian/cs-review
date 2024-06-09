package com.farhanian.cs.matrix;

import java.util.Arrays;

/**
 * You need to be creative to solve this problem in place with space complexity O(1).
 * <ul>1- You need to scan the first row and col initially and figure out if either of first row or column should be set to zero.</ul>
 * <ul>2- Then you should use the first row and first column as a marker and scan the rest of the cells.</ul>
 * <ul>3- After marking the cells in the first row and col to be set to zero, you should set to zero all rows and zeros except for the first ones.</ul>
 * <ul>4- Now we will scan the first row and column if they were marked to be zero at the first step.</ul>
 * <p>
 * Time complexity is O(m*n)
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/set-matrix-zeros">The problem</a>
 * @see <a href="https://leetcode.com/problems/set-matrix-zeroes/">Leetcode problem</a>
 */
public class SetMatrixZeros {

    public static void main(String[] args) {
        int[][][] mat = {
                {{1, 1, 0}, {1, 0, 1}, {1, 1, 1}},
                {{1, 1, 1, 1, 1}, {0, 0, 1, 1, 1}, {1, 1, 1, 1, 0}, {1, 1, 1, 1, 1}},
                {{3, 5, 2, 0}, {1, 0, 4, 6}, {7, 3, 2, 4}},
                {{1, 2, 3, 4}, {4, 5, 6, 7}, {8, 9, 4, 6}},
                {{2, 6, 5, 4, 9, 1}, {7, 2, 0, 0, 5, 4}, {1, 1, 1, 1, 0, 1}, {9, 8, 2, 0, 1, 3}, {7, 8, 6, 5, 4, 3}, {9, 8, 1, 2, 5, 6}},
                {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}} //added from the leetcode problem
        };

        for (int i = 0; i < mat.length; i++) {
            System.out.println((i + 1) + ". \tOriginal Matrix:");
            System.out.println(Arrays.deepToString(mat[i]));
            int[][] result = setMatrixZeros(mat[i]);
            System.out.println("\n\tMatrix with Zeroes:");
            System.out.println(Arrays.deepToString(result));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    public static int[][] setMatrixZeros(int[][] mat) {
        boolean removeFirstRow = false;
        boolean removeFirstColumn = false;
        for (int col = 0; col < mat[0].length; col++) {
            if (mat[0][col] == 0) {
                removeFirstRow = true;
                break;//I initially had forgotten to break.
            }
        }

        for (int row = 0; row < mat.length; row++) {
            if (mat[row][0] == 0) {
                removeFirstColumn = true;
                break;//I initially had forgotten to break.
            }
        }

        for (int row = 1; row < mat.length; row++) {
            for (int col = 1; col < mat[row].length; col++) {
                int current = mat[row][col];
                if (current == 0) {
                    mat[0][col] = 0;
                    mat[row][0] = 0;
                }
            }
        }

        for (int row = 1; row < mat.length; row++) {
            if (mat[row][0] == 0) {
                setRowZero(mat, row);
            }
        }

        for (int col = 1; col < mat[0].length; col++) {
            if (mat[0][col] == 0) {
                setColumnZero(mat, col);
            }
        }

        if (removeFirstRow) {
            setRowZero(mat, 0);
        }
        if (removeFirstColumn) {
            setColumnZero(mat, 0);
        }
        return mat;
    }

    private static void setColumnZero(int[][] input, int col) {
        for (int row = 0; row < input.length; row++) {
            input[row][col] = 0;
        }
    }

    private static void setRowZero(int[][] input, int row) {
        for (int col = 0; col < input[0].length; col++) {
            input[row][col] = 0;
        }
    }
}
