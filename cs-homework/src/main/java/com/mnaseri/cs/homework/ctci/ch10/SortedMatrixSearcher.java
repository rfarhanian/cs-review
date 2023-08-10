package com.mnaseri.cs.homework.ctci.ch10;

/**
 * Problem 9
 **/
public class SortedMatrixSearcher {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{10, 20, 30}, {35, 37, 40}, {40, 45, 50}, {50, 55, 59}};
        int input = 59;
        Point result = search(input, matrix);
        System.out.println("result = " + result);
        matrix = new int[][]{{15, 20, 70, 85}, {20, 35, 80, 95}, {30, 55, 95, 105}, {40, 80, 100, 120}};
        input = 30;
        result = search(input, matrix);
        System.out.println("result = " + result);
    }

    public static Point search(int input, int[][] matrix) {

        Integer row = null;
        for (int i = 0; i < matrix.length; i++) {
            int firstColVal = matrix[i][0];
            Integer nextColVal = ((i + 1) < matrix.length) ? matrix[i + 1][0] : null;
            if (firstColVal == input) {
                return new Point(i, 0);
            }
            if (nextColVal != null && nextColVal == input) {
                return new Point(i + 1, 0);
            }
            if (firstColVal < input && (nextColVal == null || nextColVal >= input)) {
                row = i;
                break;
            }
        }

        if (row == null) {
            return null;
        }

        Integer col = binarySearch(input, matrix[row], 0, matrix[row].length - 1);
        if (col == null) {
            return null;
        }
        return new Point(row, col);
    }

    private static Integer binarySearch(int input, int[] matrix, int start, int end) {
        if (end < start) {
            return null;
        }
        int mid = (end + start) / 2;
        if (matrix[mid] == input) {
            return mid;
        }
        if (input < matrix[mid]) {
            return binarySearch(input, matrix, start, mid - 1);
        } else {
            return binarySearch(input, matrix, mid + 1, end);
        }
    }

    public static class Point {
        private int row, column;

        public Point(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public int getColumn() {
            return this.column;
        }

        @Override
        public String toString() {
            return "(" + row + ", " + column + ")";
        }
    }
}
