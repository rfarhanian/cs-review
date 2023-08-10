package com.mnaseri.cs.homework.ch04.s2;

import java.util.Arrays;

public class SimpleMatrixMultiplier {

    public static void main(String[] args) {
        int[][] first = {
                {1, 1},
                {1, 1}
        };
        int[][] second = {
                {1, 1},
                {1, 1}
        };
        SimpleMatrixMultiplier multiplier = new SimpleMatrixMultiplier();
        int[][] result = multiplier.multiply(first, second);
        for (int i = 0; i < first.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }

        int r1 = 2, c1 = 3;
        int r2 = 3, c2 = 2;
        int[][] firstMatrix = {{3, -2, 5}, {3, 0, 4}};
        int[][] secondMatrix = {{2, 3}, {-9, 0}, {0, 4}};
        result = multiplier.multiply(firstMatrix, secondMatrix);
        for (int i = 0; i < firstMatrix.length; i++) {
            System.out.println(Arrays.toString(result[i]));
        }

    }

    /**
     * @see <a href="https://www.programiz.com/java-programming/examples/multiply-matrix-function">M*N
     * Matrix Multiplication</a>
     * @see <a href="https://i.pinimg.com/originals/85/e9/e4/85e9e463382d967fbc8078a9dfa2a829.png>M*N Simple Matrix multiplication</a>
     */
    public int[][] multiply(int[][] first, int[][] second) {

        validate(first, second);

        int m = first.length;
        int r = first[0].length;
        int n = second[0].length;
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < r; k++) {
                    result[i][j] += first[i][k] * second[k][j];
                }
            }
        }
        return result;
    }

    private void validate(int[][] first, int[][] second) {
        if (first == null || second == null || first.length == 0 || second.length == 0) {
            throw new IllegalArgumentException("Matrices cannot be null or empty");
        }
        if (first.length != second[0].length) {
            throw new IllegalStateException("Matrix multiplication is not valid");
        }
    }
}
