package com.mnaseri.cs.homework.problem.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Sudoku {
    public static void main(String[] args) {
        char[][] input = new char[][]{
                {'.', '3', '4', '6', '7', '8', '9', '1', '.'},
                {'.', '7', '2', '1', '9', '.', '3', '4', '.'},
                {'1', '9', '8', '3', '4', '.', '5', '6', '.'},
                {'8', '5', '9', '7', '6', '1', '4', '2', '.'},
                {'.', '2', '6', '8', '.', '3', '.', '9', '.'},
                {'7', '1', '3', '9', '.', '4', '8', '5', '.'},
                {'.', '6', '1', '5', '3', '7', '2', '.', '.'},
                {'2', '8', '7', '4', '1', '9', '6', '3', '.'},
                {'.', '4', '5', '2', '8', '6', '1', '7', '.'}
        };
        Sudoku sudoku = new Sudoku();
        sudoku.solveSudoku(input);
        for (char[] row : input) {
            System.out.println(Arrays.toString(row));
        }
    }

    public void solveSudoku(char[][] board) {
        Point first = resolveFirstEmptyCell(board);
        if (first != null) {
            int nextRow = first.getStart();
            int nextCol = first.getEnd();
            solveSudoku(board, nextRow, nextCol);
        }
    }

    private boolean solveSudoku(char[][] board, int row, int col) {
        if (row > 8 && col > 8) {
            return true;
        }
        Point next = resolveNextEmptyCell(board, row, col);

        int nextRow = next != null ? next.getStart() : 9;
        int nextCol = next != null ? next.getEnd() : 9;

        if (!Character.isDigit(board[row][col])) {
            for (int i = 1; i < 10; i++) {
                board[row][col] = Character.forDigit(i, 10);
                if (validateRow(board, row) && validateColumn(board, col) && validateBox(board, row, col)) {
                    boolean moveIsCorrect = solveSudoku(board, nextRow, nextCol);
                    if (moveIsCorrect) {
                        return solveSudoku(board, nextRow, nextCol);
                    } else {
                        board[row][col] = '.';
                    }
                } else {
                    board[row][col] = '.';
                }
            }
        } else {
            return solveSudoku(board, nextRow, nextCol);
        }
        return true;
    }

    public Point resolveNextEmptyCell(char[][] board, int startingRow, int startingCol) {

        for (int col = startingCol + 1; col < 9; col++) {
            if (!Character.isDigit(board[startingRow][col])) {
                return new Point(startingRow, col);
            }
        }
        for (int row = startingRow + 1; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (!Character.isDigit(board[row][col])) {
                    return new Point(row, col);
                }
            }
        }
        return null;
    }

    public Point resolveFirstEmptyCell(char[][] board) {

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (!Character.isDigit(board[row][col])) {
                    return new Point(row, col);
                }
            }
        }
        return null;
    }

    public boolean isValidSudoku(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (Character.isDigit(board[row][col])) {
                    boolean result = validateNonBox(board, row, col) && validateBox(board, row, col);
                    if (!result) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean validateNonBox(char[][] board, int row, int col) {
        Set<Integer> horizontal = new HashSet<>();
        Set<Integer> vertical = new HashSet<>();
        for (int index = 0; index < 9; index++) {
            char currentHorizontal = board[row][index];
            char currentVertical = board[index][col];
            if (Character.isDigit(currentHorizontal)) {
                int currentHorizontalInt = currentHorizontal - '0';
                if (horizontal.contains(currentHorizontalInt)) {
                    return false;
                }
                horizontal.add(currentHorizontalInt);

            }
            if (Character.isDigit(currentVertical)) {
                int currentVerticalInt = currentVertical - '0';
                if (vertical.contains(currentVerticalInt)) {
                    return false;
                }
                vertical.add(currentVerticalInt);
            }
        }
        return true;

    }

    private boolean validateRow(char[][] board, int row) {
        Set<Integer> numbers = new HashSet<>();
        for (int col = 0; col < 9; col++) {
            char current = board[row][col];
            if (Character.isDigit(current)) {
                int currentInt = current - '0';
                if (numbers.contains(currentInt)) {
                    return false;
                } else {
                    numbers.add(currentInt);
                }
            }
        }
        return true;
    }

    private boolean validateColumn(char[][] board, int col) {
        Set<Integer> numbers = new HashSet<>();
        for (int row = 0; row < 9; row++) {
            char current = board[row][col];
            if (Character.isDigit(current)) {
                int currentInt = current - '0';
                if (numbers.contains(currentInt)) {
                    return false;
                } else {
                    numbers.add(currentInt);
                }
            }
        }
        return true;
    }

    private boolean validateBox(char[][] board, int boxRow, int boxCol) {
        Point columnRange = resolveSegment(boxCol);
        Point rowRange = resolveSegment(boxRow);
        Set<Integer> numbers = new HashSet<>();
        for (int row = rowRange.getStart(); row < rowRange.getEnd(); row++) {
            for (int col = columnRange.getStart(); col < columnRange.getEnd(); col++) {
                char current = board[row][col];
                if (Character.isDigit(current)) {
                    int currentInt = current - '0';
                    if (numbers.contains(currentInt)) {
                        return false;
                    } else {
                        numbers.add(currentInt);
                    }
                }
            }
        }
        return true;
    }

    private Point resolveSegment(int index) {
        if (index >= 0 && index < 3) {
            return new Point(0, 3);
        } else if (index >= 3 && index < 6) {
            return new Point(3, 6);
        } else {
            return new Point(6, 9);
        }
    }

    private static class Point {
        private int start, end;

        public Point(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getEnd() {
            return this.end;
        }

        public int getStart() {
            return this.start;
        }
    }
}
