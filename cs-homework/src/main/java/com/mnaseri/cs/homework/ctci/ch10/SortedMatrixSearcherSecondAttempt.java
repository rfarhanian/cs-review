package com.mnaseri.cs.homework.ctci.ch10;

/**
 * Problem 9
 **/
public class SortedMatrixSearcherSecondAttempt {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{10, 20, 30}, {35, 37, 40}, {40, 45, 50}, {50, 55, 59}};
        int input = 59;
        boolean result = search(input, matrix);
        System.out.println("result = " + result);
        matrix = new int[][]{{15, 20, 70, 85}, {20, 35, 80, 95}, {30, 55, 95, 105}, {40, 80, 100, 120}};
        input = 30;
        result = search(input, matrix);
        System.out.println("result = " + result);
    }

    public static boolean search(int input, int[][] matrix) {
        int col = matrix[0].length - 1, row = 0;
        while (col < matrix[0].length && row < matrix.length) {
            if (matrix[row][col] == input) {
                System.out.println("(" + row + "," + col + ")");
                return true;
            } else if (input > matrix[row][col]) {
                row++;
            } else {
                col--;
            }
        }
        return false;
    }
}
