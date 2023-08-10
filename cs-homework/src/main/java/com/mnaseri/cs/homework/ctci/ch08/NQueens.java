package com.mnaseri.cs.homework.ctci.ch08;

import java.util.ArrayList;
import java.util.List;

/**
 * From leetcode:
 * https://leetcode.com/problems/n-queens/submissions/
 */
public class NQueens {

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        List<List<String>> lists = nQueens.solveNQueens(8);
        for (List<String> list : lists) {
            System.out.println(list);
        }
    }

    public List<List<String>> solveNQueens(int n) {
        Integer[] columns = new Integer[n];
        List<List<String>> result = new ArrayList<>();
        placeQueen(0, columns, result);
        return result;
    }

    public void placeQueen(int row, Integer[] columns, List<List<String>> result) {
        if (row == columns.length) {
            result.add(reportLines(columns));
        } else {
            for (int col = 0; col < columns.length; col++) {
                if (isValid(columns, row, col)) {
                    columns[row] = col;
                    placeQueen(row + 1, columns, result);
                }
            }
        }
    }

    private boolean isValid(Integer[] columns, int row, int col) {
        for (int rowIndex = 0; rowIndex < row; rowIndex++) { //col check
            if (columns[rowIndex] == col) {
                return false;
            }

            int value = columns[rowIndex];
            if (row - rowIndex == Math.abs(value - col)) {
                return false;
            }

        }
        return true;
    }

    private List<String> reportLines(Integer[] columns) {
        List<String> result = new ArrayList<>();
        for (int row = 0; row < columns.length; row++) {
            result.add(reportLine(columns, row));
        }
        return result;
    }

    private String reportLine(Integer[] columns, int row) {
        int q = columns[row];
        StringBuilder b = new StringBuilder();
        for (int col = 0; col < columns.length; col++) {
            b.append(q == col ? "Q" : ".");
        }
        return b.toString();
    }
}

