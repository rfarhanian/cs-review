package com.farhanian.cs.matrix;

/**
 * @see <a href="https://leetcode.com/problems/spiral-matrix-ii/">Leetcode problem</a>
 */
public class SpiralMatrixII {
    public static int[][] generateMatrix(int n) {
        int size = n * n;
        int[][] matrix = new int[n][n];
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        int offset = 0;
        int current = 1;
        while (current <= size) {
            //Horizontal toward right
            for (int index = left; index <= right; index++) {
                matrix[top][index] = current;
                current++;
            }
            //Vertical toward bottom
            for (int index = top + 1; index < bottom; index++) {
                matrix[index][right] = current;
                current++;

            }

            //Horizontal toward left
            if (top != bottom) {
                for (int index = right; index >= left; index--) {
                    matrix[bottom][index] = current;
                    current++;
                }
            }

            //Vertical toward top
            if (left != right) {
                for (int index = bottom - 1; index > top; index--) {
                    matrix[index][left] = current;
                    current++;
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return matrix;
    }
}
